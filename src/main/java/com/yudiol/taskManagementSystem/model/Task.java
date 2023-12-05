package com.yudiol.taskManagementSystem.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
@Data
public class Task {

    @Id
    @Column(name = "task_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskId;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    @Enumerated(EnumType.ORDINAL)
    private TaskStatus status;

    @Column(name = "priority")
    @Enumerated(EnumType.ORDINAL)
    private Priority priority;

    //    @ManyToOne
//    @JoinColumn(name = "author_id",referencedColumnName = "user_id")
//    private User authorTask;
    @Column(name = "author_id")
    private Long authorId;

    //    @ManyToOne
//    @JoinColumn(name = "performer_id",referencedColumnName = "user_id")
//    private User performer;
    @Column(name = "performer_id")
    private Long performerId;

//    @OneToMany(mappedBy = "task")
//    private List<Comment> comments;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-ddTHH:mm:ss")
    @Column(name = "date_registration")
    private LocalDateTime dateRegistration;

}
