package com.test.kanban.kanban.mapper;

import com.test.kanban.kanban.dto.KanbanDto;
import com.test.kanban.kanban.model.Kanban;

import java.util.List;
import java.util.stream.Collectors;

public class KanbanMapper {
    public static KanbanDto toKanbanDto(Kanban kanban){
        return KanbanDto.builder()
                .id(kanban.getId())
                .name(kanban.getName())
                .task(kanban.getTask())
                .build();
    }

    public static Kanban toKanban(KanbanDto kanbanDto){
        return Kanban.builder()
                .id(kanbanDto.getId())
                .name(kanbanDto.getName())
                .task(kanbanDto.getTask())
                .owner(kanbanDto.getOwner())
                .build();
    }

    public static List<KanbanDto> toKanbanDtoList(List<Kanban> kanbans){
        return kanbans.stream()
                .map(KanbanMapper::toKanbanDto)
                .collect(Collectors.toList());
    }
}
