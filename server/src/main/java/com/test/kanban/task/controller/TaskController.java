package com.test.kanban.task.controller;

import com.test.kanban.task.dto.TaskDto;
import com.test.kanban.task.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
@Validated
public class TaskController {
    private final TaskService taskService;
    private static final String userIdInHeader = "X-Sharer-User-Id";

    @PostMapping
    public TaskDto create(@RequestHeader(userIdInHeader) long ownerId,
                          @RequestBody TaskDto taskDto){
        return taskService.addTask(ownerId,taskDto);
    }

    @PatchMapping("/{taskId}")
    public TaskDto update(@RequestHeader(userIdInHeader) Long ownerId, @PathVariable Long taskId,
                          @RequestBody TaskDto taskDto){
        return taskService.updateTask(ownerId,taskId,taskDto);
    }
    @GetMapping("/{taskId}")
    public TaskDto getTask(@PathVariable long taskId,
                           @RequestHeader(userIdInHeader) long userId) {
        return taskService.getTaskDtoById(taskId, userId);
    }
    @GetMapping
    public List<TaskDto> getAllTasks(@RequestHeader(userIdInHeader) long ownerId,
                                     @RequestParam(defaultValue = "0") int from,
                                     @RequestParam(defaultValue = "10") int size){
        return taskService.getAllTasks(ownerId,from,size);
    }
    @DeleteMapping("/{taskId}")
    public void delete(@PathVariable long taskId,@RequestHeader(userIdInHeader) long ownerId) {
        taskService.deleteTaskById(taskId,ownerId);
    }
}
