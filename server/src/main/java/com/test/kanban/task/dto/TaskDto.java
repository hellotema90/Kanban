package com.test.kanban.task.dto;

import com.test.kanban.task.model.TaskStatus;
import com.test.kanban.user.model.User;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TaskDto {
    private Long id;
    private String name;
    private String description;
    private User owner;
    private TaskStatus status;
}
