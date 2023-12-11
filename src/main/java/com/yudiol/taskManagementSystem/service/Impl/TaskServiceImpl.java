package com.yudiol.taskManagementSystem.service.Impl;

import com.yudiol.taskManagementSystem.dto.CommentWithAuthorFullNameResponseDto;
import com.yudiol.taskManagementSystem.dto.IdResponseDto;
import com.yudiol.taskManagementSystem.dto.TaskChangeStatusRequestDto;
import com.yudiol.taskManagementSystem.dto.TaskDto;
import com.yudiol.taskManagementSystem.dto.TaskRequestDto;
import com.yudiol.taskManagementSystem.dto.TaskResponseDto;
import com.yudiol.taskManagementSystem.exception.errors.BadRequestError;
import com.yudiol.taskManagementSystem.exception.errors.NotFoundException;
import com.yudiol.taskManagementSystem.mapper.TaskMapper;
import com.yudiol.taskManagementSystem.model.Priority;
import com.yudiol.taskManagementSystem.model.Task;
import com.yudiol.taskManagementSystem.model.TaskStatus;
import com.yudiol.taskManagementSystem.repository.TaskRepository;
import com.yudiol.taskManagementSystem.repository.UserRepository;
import com.yudiol.taskManagementSystem.service.CommentService;
import com.yudiol.taskManagementSystem.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TaskServiceImpl implements TaskService {

    private final TaskMapper taskMapper;
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final CommentService commentService;

    @Transactional
    public IdResponseDto save(Long userId, TaskRequestDto taskRequestDto) {

        userRepository.findById(taskRequestDto.getPerformerId()).orElseThrow(() -> new NotFoundException("Проверьте поле 'performerId' исполнитель", String.valueOf(userId)));

        Task task = taskMapper.toTask(taskRequestDto);

        task.setStatus(checkTaskStatus(taskRequestDto.getTaskStatus()));
        task.setPriority(checkTaskPriority(taskRequestDto.getTaskPriority()));
        task.setAuthorId(userId);
        task.setDateRegistration(LocalDateTime.now());
        return new IdResponseDto(taskRepository.save(task).getTaskId());
    }


    @Transactional
    public void update(Long taskId, TaskRequestDto task) {

        userRepository.findById(task.getPerformerId()).orElseThrow(() ->
                new NotFoundException("Проверьте поле 'performerId' исполнитель", String.valueOf(taskId)));

        taskRepository.update(task.getTitle(), task.getDescription(), checkTaskStatus(task.getTaskStatus()), checkTaskPriority(task.getTaskPriority()), task.getPerformerId(), taskId);
    }


    @Transactional
    public void delete(Long taskId) {

        taskRepository.deleteByTaskId(taskId);
    }


    public TaskResponseDto findByTaskId(Long taskId, Boolean withComments) {

        TaskResponseDto task = taskRepository.findTaskByTaskId(taskId).map(taskMapper::toTaskResponseDto)
                .orElseThrow(() -> new NotFoundException("Задача", String.valueOf(taskId)));
        if (withComments) {
            Map<Long, List<CommentWithAuthorFullNameResponseDto>> map = commentService.getListCommentsByListIds(List.of(taskId));
            task.setComments(map.get(taskId));
        }
        return task;
    }


    @Transactional
    public void changeStatus(Long taskId, TaskChangeStatusRequestDto task) {

        taskRepository.changeStatus(taskId, checkTaskStatus(task.getTaskStatus()));
    }


    public Page<TaskResponseDto> findAllByAuthorId(Pageable pageable, Long authorId, Boolean withComments) {
        Page<TaskDto> commentPage = taskRepository.findAllByAuthorId(pageable, authorId);
        return getTasks(commentPage, withComments);
    }


    public Page<TaskResponseDto> findAllByPerformerId(Pageable pageable, Long performerId, Boolean withComments) {
        Page<TaskDto> commentPage = taskRepository.findAllByPerformerId(pageable, performerId);
        return getTasks(commentPage, withComments);
    }


    public Page<TaskResponseDto> filter(Pageable pageable, String authorName, String authorSurname, String performerName, String performerSurname, LocalDateTime startDate, LocalDateTime endDate, Boolean withComments) {
        Page<TaskDto> commentPage = userRepository.filter(pageable, authorName, authorSurname, performerName, performerSurname, startDate, endDate);
        return getTasks(commentPage, withComments);

    }


    private Page<TaskResponseDto> getTasks(Page<TaskDto> commentPage, Boolean withComments) {
        List<Long> listTaskIds = commentPage.getContent().stream().map(TaskDto::getTaskId).toList();
        Page<TaskResponseDto> taskResponseDto = commentPage.map(taskMapper::toTaskResponseDto);
        if (withComments) {
            return getComments(taskResponseDto, listTaskIds);
        } else {
            return taskResponseDto;
        }
    }


    private Page<TaskResponseDto> getComments(Page<TaskResponseDto> page, List<Long> ids) {
        Map<Long, List<CommentWithAuthorFullNameResponseDto>> map = commentService.getListCommentsByListIds(ids);
        return page.map(taskResponseDto -> {
            List<CommentWithAuthorFullNameResponseDto> comments = map.get((taskResponseDto).getTaskId());
            taskResponseDto.setComments(comments);
            return taskResponseDto;
        });
    }


    private TaskStatus checkTaskStatus(String taskStatus) {
        for (TaskStatus status : TaskStatus.values()) {
            if (status.getStatus().equals(taskStatus)) {
                return status;
            }
        }
        throw new BadRequestError("Проверьте поле 'taskStatus' такого статуса не существует");
    }


    private Priority checkTaskPriority(String taskPriority) {
        for (Priority priority : Priority.values()) {
            if (priority.getPriority().equals(taskPriority)) {
                return priority;
            }
        }
        throw new BadRequestError("Проверьте поле 'taskPriority' такого приоритета не существует");
    }
}
