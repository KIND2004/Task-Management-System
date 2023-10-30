package com.abdullah.task.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Task extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "comments", columnDefinition = "TEXT")
    private String comments;
    @Column(name = "due_date")
    private LocalDate dueDate;
    @Column(name = "active")
    private Boolean active = true;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    private Status status = Status.TO_DO;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "priority")
    private Priority priority = Priority.LOW;
    @Column(name = "created_by")
    private Long createdBy;
    @ManyToOne
    @JoinColumn(name = "created_by", referencedColumnName = "id", insertable = false, updatable = false)
    private User user;
}
