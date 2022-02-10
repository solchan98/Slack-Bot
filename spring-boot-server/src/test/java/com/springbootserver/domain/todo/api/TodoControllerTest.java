package com.springbootserver.domain.todo.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springbootserver.domain.todo.controller.TodoController;
import com.springbootserver.domain.todo.domain.Todo;
import com.springbootserver.domain.todo.domain.dto.request.TodoRequest;
import com.springbootserver.domain.todo.service.TodoService;
import com.springbootserver.global.error.HttpException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;

import static com.springbootserver.global.content.TodoContent.TODO_CREATE_OK;
import static com.springbootserver.global.content.TodoContent.TODO_INVALID_BEFORE_NOW;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(value = {TodoController.class})
@DisplayName("Todo Controller 테스트")
public class TodoControllerTest {
    @MockBean
    private TodoService todoService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Todo 생성 - 성공")
    void createTodoSuccess() throws Exception {
        // given
        LocalDateTime afterDateTime = LocalDateTime.now().plusDays(2L);
        Todo mockTodo = Todo.of("MD1234DFA", "프로그래머스 48432 문제 풀기!", afterDateTime);
        TodoRequest todoRequest = new TodoRequest(mockTodo.getUserId(), mockTodo.getContent(), mockTodo.getDateTime());
        String request = objectMapper.writeValueAsString(todoRequest);
        given(todoService.createTodo(any())).willReturn(mockTodo);

        // when
        ResultActions perform = mockMvc.perform(post("/api/v1/todo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request));

        // then
        perform
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(jsonPath("$.status").value(HttpStatus.CREATED.name()))
                .andExpect(jsonPath("$.message").value(TODO_CREATE_OK))
                .andExpect(jsonPath("$.userId").value(mockTodo.getUserId()))
                .andExpect(jsonPath("$.content").value(mockTodo.getContent()))
                .andExpect(jsonPath("$.done").value(mockTodo.isDone()));
    }

    @Test
    @DisplayName("Todo 생성 - 실패")
    void createTodoFail() throws Exception {
        // given
        LocalDateTime beforeDateTime = LocalDateTime.now().minusDays(2L);
        Todo mockTodo = Todo.of("MD1234DFA", "프로그래머스 48432 문제 풀기!", beforeDateTime);
        TodoRequest todoRequest = new TodoRequest(mockTodo.getUserId(), mockTodo.getContent(), mockTodo.getDateTime());
        String request = objectMapper.writeValueAsString(todoRequest);
        given(todoService.createTodo(any())).willThrow(new HttpException(HttpStatus.BAD_REQUEST, TODO_INVALID_BEFORE_NOW));

        // when
        ResultActions perform = mockMvc.perform(post("/api/v1/todo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request));

        // then
        perform
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(jsonPath("$.status").value(HttpStatus.BAD_REQUEST.name()))
                .andExpect(jsonPath("$.message").value(TODO_INVALID_BEFORE_NOW));

    }
}
