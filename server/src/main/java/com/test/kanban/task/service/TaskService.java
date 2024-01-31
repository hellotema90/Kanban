package com.test.kanban.task.service;

import com.test.kanban.task.dto.TaskDto;
import com.test.kanban.task.model.Task;

import java.util.List;

public interface TaskService {
    TaskDto addTask(long ownerId, TaskDto taskDto);
    TaskDto updateTask(Long owner,Long taskId, TaskDto taskDto);
    Task getTaskById(long taskId);
    TaskDto getTaskDtoById(long taskId, long userId);
    void deleteTaskById(long ownerId, long taskId);
    List<TaskDto> getAllTasks(long ownerId, int from, int size);
}
