package com.yudiol.taskManagementSystem.service.Impl;

import com.yudiol.taskManagementSystem.dto.CommentWithAuthorFullNameResponseDto;
import com.yudiol.taskManagementSystem.dto.FilterDto;
import com.yudiol.taskManagementSystem.dto.TaskChangeStatusRequestDto;
import com.yudiol.taskManagementSystem.dto.TaskCreateRequestDto;
import com.yudiol.taskManagementSystem.dto.TaskUpdateRequestDto;
import com.yudiol.taskManagementSystem.dto.TaskWithCommentsResponseDto;
import com.yudiol.taskManagementSystem.dto.UserFullNameResponseDto;
import com.yudiol.taskManagementSystem.mapper.CommentMapper;
import com.yudiol.taskManagementSystem.mapper.TaskMapper;
import com.yudiol.taskManagementSystem.mapper.UserMapper;
import com.yudiol.taskManagementSystem.model.Comment;
import com.yudiol.taskManagementSystem.model.Task;
import com.yudiol.taskManagementSystem.model.User;
import com.yudiol.taskManagementSystem.repository.CommentRepository;
import com.yudiol.taskManagementSystem.repository.TaskRepository;
import com.yudiol.taskManagementSystem.repository.UserRepository;
import com.yudiol.taskManagementSystem.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TaskServiceImpl implements TaskService {

    private final TaskMapper taskMapper;
    private final TaskRepository taskRepository;
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    @Transactional
    public Long save(Long userId, TaskCreateRequestDto taskRequestDto) {
        Task task = taskMapper.toTask(taskRequestDto);
        task.setAuthorId(userId);
        task.setDateRegistration(LocalDateTime.now());
        return taskRepository.save(task).getTaskId();
    }

    @Transactional
    public void update(Long userId, TaskUpdateRequestDto task) {
//        Task task = taskMapper.toTask(taskRequestDto);
//        task.setAuthorId(userId);
//        task.setDateRegistration(LocalDateTime.now());
         taskRepository.update(task.getTitle(),task.getDescription(),task.getStatus(),task.getPriority(),userId);
    }

    @Transactional
    public void changeStatus(Long taskId, TaskChangeStatusRequestDto taskChangeStatusRequestDto){
        taskRepository.changeStatus(taskId,taskChangeStatusRequestDto.getStatus());
    }


    public TaskWithCommentsResponseDto findByTaskId(Long taskId) {
        Task task = taskRepository.findByTaskId(taskId).orElseThrow();
        return getTaskWithCommentsResponseDto(task);
    }

    public List<TaskWithCommentsResponseDto> findAll() {
        return taskRepository.findAll().stream().map(this::getTaskWithCommentsResponseDto).collect(Collectors.toList());
    }

    public List<TaskWithCommentsResponseDto> findAllByAuthorId(Long authorId) {
        return taskRepository.findAllByAuthorId(authorId).stream().map(this::getTaskWithCommentsResponseDto).collect(Collectors.toList());
    }

    public List<TaskWithCommentsResponseDto> findAllByPerformerId(Long performerId) {
        return taskRepository.findAllByPerformerId(performerId).stream().map(this::getTaskWithCommentsResponseDto).collect(Collectors.toList());
    }

    public List<FilterDto> filter(String title){
//        return userRepository.filter(title);
        return null;
    }


    private TaskWithCommentsResponseDto getTaskWithCommentsResponseDto(Task task) {
        TaskWithCommentsResponseDto taskResponseDto = taskMapper.toTaskResponseDto(task);

        User userAuthor = userRepository.findById(task.getAuthorId()).orElseThrow();
        User userPerformer = userRepository.findById(task.getPerformerId()).orElseThrow();

        UserFullNameResponseDto author = userMapper.toUserForTaskResponseDto(userAuthor);
        UserFullNameResponseDto performer = userMapper.toUserForTaskResponseDto(userPerformer);

        List<CommentWithAuthorFullNameResponseDto> comments = getCommentWithAuthorFullNameResponseDto(
                commentRepository.findAllByTaskId(task.getTaskId()));

        taskResponseDto.setAuthor(author);
        taskResponseDto.setPerformer(performer);
        taskResponseDto.setComments(comments);
        return taskResponseDto;
    }

    private List<CommentWithAuthorFullNameResponseDto> getCommentWithAuthorFullNameResponseDto(List<Comment> commentList) {
        List<CommentWithAuthorFullNameResponseDto> comments = new ArrayList<>();
        User userAuthor;
        UserFullNameResponseDto author;
        for (Comment comment : commentList) {
            userAuthor = userRepository.findById(comment.getUserId()).orElseThrow();
            author = userMapper.toUserForTaskResponseDto(userAuthor);
            CommentWithAuthorFullNameResponseDto commentDto = commentMapper.toCommentWithAuthorFullNameResponseDto(comment);
            commentDto.setAuthor(author);
            comments.add(commentDto);
        }
        return comments;
    }
}
