package com.test.kanban.task.service;

import com.test.kanban.exception.NotFoundException;
import com.test.kanban.task.dto.TaskDto;
import com.test.kanban.task.mapper.TaskMapper;
import com.test.kanban.task.model.Task;
import com.test.kanban.task.model.TaskStatus;
import com.test.kanban.task.repository.TaskRepository;
import com.test.kanban.user.model.User;
import com.test.kanban.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    private User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(String.format("Пользователь с id %d не найден", userId)));
    }

    @Transactional
    public TaskDto addTask(long ownerId, TaskDto taskDto) {
        User owner = getUserById(ownerId);
        Task task = TaskMapper.toTask(taskDto);
        task.setOwner(owner);
        return TaskMapper.toTaskDto(taskRepository.save(task));
    }

    private void existsUserWithId(Long userId) {
        if (!userRepository.existsUserById(userId)) {
            throw new NotFoundException(String.format("Пользователь с id %d не найден", userId));
        }
    }

    private void checkOwnerOfTask(Long ownerId, Task task) {
        User owner = task.getOwner();
        if ((owner == null) || (!owner.getId().equals(ownerId))) {
            throw new NotFoundException(String.format("Пользователь с id:%s не владелец задачи с id: %s", ownerId,
                    task.getId()));
        }
    }

    @Transactional
    public TaskDto updateTask(Long ownerId, Long taskId, TaskDto taskDto) {
        existsUserWithId(ownerId);
        Task task = getTaskById(taskId);
        checkOwnerOfTask(ownerId, task);
        int i = 0;
        if ((taskDto.getName() != null) && (!taskDto.getName().isBlank())) {
            i++;
            task.setName(taskDto.getName());
        }
        if ((taskDto.getDescription() != null) && (!taskDto.getDescription().isBlank())) {
            i++;
            task.setDescription(taskDto.getDescription());
        }
        if ((taskDto.getStatus() != null) && (!taskDto.getStatus().equals(TaskStatus.COMPLETED))) {
            i++;
            task.setStatus(taskDto.getStatus());
        }
        if (i > 0) {
            taskRepository.save(task);
        }
        return TaskMapper.toTaskDto(task);
    }

    @Transactional(readOnly = true)
    public Task getTaskById(long taskId) {
        return taskRepository.findById(taskId)
                .orElseThrow(() -> new NotFoundException(String.format("Пользователь с id %d не найден", taskId)));
    }

    @Transactional(readOnly = true)
    public TaskDto getTaskDtoById(long taskId, long userId) {
        Task task = getTaskById(taskId);
        return TaskMapper.toTaskDto(task);
    }

    public List<TaskDto> getAllTasks(long ownerId, int from, int size) {
        existsUserWithId(ownerId);
        List<Task> tasks = taskRepository.findAllByOwnerId(ownerId, PageRequest.of(from / size, size,
                Sort.by(Sort.Direction.DESC, "name")));
        return TaskMapper.toTaskDtoList(tasks);
    }

    @Transactional
    public void deleteTaskById(long ownerId, long taskId) {
        getUserById(ownerId);
        Task task = getTaskById(taskId);
        checkOwnerOfTask(ownerId, task);
        taskRepository.delete(task);
    }
}
