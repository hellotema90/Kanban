package com.test.kanban.task.model;

import com.test.kanban.user.model.User;
import lombok.*;
import lombok.extern.slf4j.Slf4j;


import javax.persistence.*;

@Slf4j
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @ToString.Exclude
    @JoinColumn(name = "OWNER_ID")
    private User owner;
    @Enumerated(EnumType.STRING)
    private TaskStatus status;
}
