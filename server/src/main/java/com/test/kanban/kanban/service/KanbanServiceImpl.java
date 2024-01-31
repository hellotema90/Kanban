package com.test.kanban.kanban.service;

import com.test.kanban.kanban.dto.KanbanDto;
import com.test.kanban.kanban.mapper.KanbanMapper;
import com.test.kanban.kanban.model.Kanban;
import com.test.kanban.kanban.repositiry.KanbanRepository;
import com.test.kanban.task.repository.TaskRepository;
import com.test.kanban.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.test.kanban.exception.*;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class KanbanServiceImpl implements KanbanService {
    private final KanbanRepository kanbanRepository;
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public void existsUser(Long userId) {
        if (!userRepository.existsUserById(userId)) {
            throw new NotFoundException(String.format("Пользователь с id %d не найден", userId));
        }
    }

    public void existsTask(Long taskId) {
        if (!taskRepository.existsById(taskId)) {
            throw new NotFoundException(String.format("Задача с id %d не найдена", taskId));
        }
    }

    @Transactional
    public KanbanDto addKanban(KanbanDto kanbanDto, Long userId, Long taskId) {
        existsUser(userId);
        existsTask(taskId);
        Kanban kanban = KanbanMapper.toKanban(kanbanDto);
        return KanbanMapper.toKanbanDto(kanbanRepository.save(kanban));
    }

    @Transactional
    public KanbanDto updateKanban(Long kanbanId, Long userId, Long taskId) {
        existsUser(userId);
        existsTask(taskId);
        Kanban kanban = getKanbanById(kanbanId);
        return KanbanMapper.toKanbanDto(kanbanRepository.save(kanban));
    }


    public Kanban getKanbanById(Long kanbanId) {
        Kanban kanban = kanbanRepository.findById(kanbanId)
                .orElseThrow(() -> new NotFoundException(String.format("Канбан с id: %d не найден", kanbanId)));
        return kanban;
    }

    @Transactional
    public KanbanDto getKanbanDtoById(Long kanbanId) {
        Kanban kanban = getKanbanById(kanbanId);
        return KanbanMapper.toKanbanDto(kanban);
    }

    @Transactional
    public void deleteKanbanById(Long kanbanId) {
        kanbanRepository.deleteById(kanbanId);
    }
}
