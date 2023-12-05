package com.yudiol.taskManagementSystem.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
@Getter
@Setter
@ToString
public class Comment {
    @Id
    @Column(name = "comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    //    @ManyToOne
//    @JoinColumn(name = "task_id", referencedColumnName = "task_id")
//    private Task task;
    @Column(name = "task_id")
    private Long taskId;

    @Column(name = "user_id")
    private Long userId;

//    @ManyToOne
//    @JoinColumn(name = "author_id", referencedColumnName = "user_id")
//    private User authorComment;


    @Column(name = "description")
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-ddTHH:mm:ss")
    @Column(name = "date_registration")
    private LocalDateTime dateRegistration;

}
