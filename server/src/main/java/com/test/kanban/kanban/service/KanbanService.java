package com.test.kanban.kanban.service;

import com.test.kanban.kanban.dto.KanbanDto;


public interface KanbanService {
    KanbanDto addKanban(KanbanDto kanbanDto, Long userId, Long taskId);

    KanbanDto updateKanban(Long kanbanId, Long userId, Long taskId);

    KanbanDto getKanbanDtoById(Long kanbanId);
    void deleteKanbanById(Long kanbanId);

}
