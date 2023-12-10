package com.test.kanban.kanban.controller;

import com.test.kanban.kanban.dto.KanbanDto;
import com.test.kanban.kanban.service.KanbanService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kanbans")
@RequiredArgsConstructor
@Validated
public class KanbanController {
    private final KanbanService kanbanService;
    private static final String userIdInHeader = "X-Sharer-User-Id";

    @PostMapping
    public KanbanDto create(@RequestHeader(userIdInHeader) long userId,
                            @RequestBody KanbanDto kanbanDto,
                            @PathVariable long taskId) {
        return kanbanService.addKanban(kanbanDto, userId, taskId);
    }

    @PatchMapping("/{kanbanId}")
    public KanbanDto update(@RequestHeader(userIdInHeader) long userId,
                            @PathVariable long kanbanId,
                            @PathVariable long taskId) {
        return kanbanService.updateKanban(kanbanId, userId, taskId);
    }

    @GetMapping("/{kanbanId}")
    public KanbanDto getKanbanDtoById(@PathVariable long kanbanId) {
        return kanbanService.getKanbanDtoById(kanbanId);
    }

    @DeleteMapping("/{kanbanId}")
    public void deleteKanbanById(@PathVariable long kanbanId) {
        kanbanService.deleteKanbanById(kanbanId);
    }

}
