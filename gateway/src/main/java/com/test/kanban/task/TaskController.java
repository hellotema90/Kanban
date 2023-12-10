package com.test.kanban.task;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.test.kanban.task.dto.TaskDto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Controller
@RequestMapping(path = "/tasks")
@RequiredArgsConstructor
@Validated
public class TaskController {
    private static final String userIdInHeader = "X-Sharer-User-Id";
    private final TaskClient taskClient;

    @PostMapping
    public ResponseEntity<Object> create(@RequestHeader(userIdInHeader) @Positive long ownerId,
                                         @RequestBody @Valid @NotNull TaskDto taskDto) {
        return taskClient.createTask(ownerId, taskDto);
    }

    @PatchMapping("/{taskId}")
    public ResponseEntity<Object> update(@RequestHeader(userIdInHeader) @Positive Long userId,
                                         @PathVariable @Positive Long taskId,
                                         @RequestBody TaskDto taskDto) {
        return taskClient.updateTask(userId, taskId, taskDto);
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<Object> getTaskById(@PathVariable @Positive long taskId,
                                              @RequestHeader(userIdInHeader) @Positive long userId) {
        return taskClient.getTaskById(taskId, userId);
    }

    @GetMapping
    public ResponseEntity<Object> getAllTasks(@RequestHeader(userIdInHeader) @Positive long userId,
                                              @RequestParam(defaultValue = "0") @PositiveOrZero int from,
                                              @RequestParam(defaultValue = "10") @Positive int size) {
        return taskClient.getAllUserTasks(userId, from, size);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Object> delete(@PathVariable @Positive long taskId,
                                         @RequestHeader(userIdInHeader) @Positive long userId) {
        return taskClient.deleteTask(taskId, userId);
    }
}
