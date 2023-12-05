package com.yudiol.taskManagementSystem.controller;

import com.yudiol.taskManagementSystem.dto.CommentRequestDto;
import com.yudiol.taskManagementSystem.model.Comment;
import com.yudiol.taskManagementSystem.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
@ResponseStatus(HttpStatus.OK)
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public Comment create(@RequestBody CommentRequestDto commentRequestDto) {
        return commentService.save(3L, commentRequestDto);
    }
}
