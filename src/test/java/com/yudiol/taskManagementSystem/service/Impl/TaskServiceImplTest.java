package com.yudiol.taskManagementSystem.service.Impl;

import com.yudiol.taskManagementSystem.dto.TaskDto;
import com.yudiol.taskManagementSystem.dto.TaskResponseDto;
import com.yudiol.taskManagementSystem.exception.errors.NotFoundException;
import com.yudiol.taskManagementSystem.mapper.TaskMapper;
import com.yudiol.taskManagementSystem.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class TaskServiceImplTest {

    @InjectMocks
    private TaskServiceImpl taskService;

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private TaskMapper taskMapper;

    @Mock
    private CommentServiceImpl commentService;

    @Test
    void findByTaskId_whenTaskFoundReturnWithoutComments_thenReturnTaskResponseDtoWithoutComments() {

        Long taskId = 1L;
        Boolean withComments = false;
        TaskDto taskDto = new TaskDto();
        TaskResponseDto expectedTaskResponseDto = new TaskResponseDto();

        Mockito.when(taskRepository.findTaskByTaskId(taskId)).thenReturn(Optional.of(taskDto));
        Mockito.when(taskMapper.toTaskResponseDto(taskDto)).thenReturn(expectedTaskResponseDto);

        TaskResponseDto taskResponseDto = taskService.findByTaskId(taskId, withComments);

        assertEquals(taskResponseDto, expectedTaskResponseDto);
    }

    @Test
    void findByTaskId_whenTaskFoundReturnWithComments_thenReturnTaskResponseDtoWithComments() {

        Long taskId = 1L;
        Boolean withComments = true;
        TaskDto taskDto = new TaskDto();
        TaskResponseDto expectedTaskResponseDto = new TaskResponseDto();
        Mockito.when(taskRepository.findTaskByTaskId(taskId)).thenReturn(Optional.of(taskDto));
        Mockito.when(taskMapper.toTaskResponseDto(taskDto)).thenReturn(expectedTaskResponseDto);
        Mockito.when(commentService.getListCommentsByListIds(List.of(taskId))).thenReturn(Collections.emptyMap());

        TaskResponseDto taskResponseDto = taskService.findByTaskId(taskId, withComments);

        assertEquals(taskResponseDto, expectedTaskResponseDto);
    }

    @Test
    void findByTaskId_whenTaskNotFound_thenThrowExceptionNotFound() {

        Long taskId = 1L;
        Boolean withComments = true;
        Mockito.when(taskRepository.findTaskByTaskId(taskId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> taskService.findByTaskId(taskId, withComments));
    }
}