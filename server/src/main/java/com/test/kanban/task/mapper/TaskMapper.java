package com.test.kanban.task.mapper;

import com.test.kanban.task.dto.TaskDto;
import com.test.kanban.task.model.Task;

import java.util.List;
import java.util.stream.Collectors;

public class TaskMapper {
    public static TaskDto toTaskDto(Task task) {
        return TaskDto.builder()
                .id(task.getId())
                .name(task.getName())
                .description(task.getDescription())
                .status(task.getStatus())
                .build();
    }

    public static Task toTask(TaskDto taskDto) {
        return Task.builder()
                .id(taskDto.getId())
                .name(taskDto.getName())
                .description(taskDto.getDescription())
                .owner(taskDto.getOwner())
                .status(taskDto.getStatus())
                .build();
    }

    public static List<TaskDto> toTaskDtoList(List<Task> tasks) {
        return tasks.stream()
                .map(TaskMapper::toTaskDto)
                .collect(Collectors.toList());
    }
}
