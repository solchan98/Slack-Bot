package com.springbootserver.domain.todo.service;

import com.springbootserver.domain.todo.domain.Todo;
import com.springbootserver.domain.todo.domain.dao.TodoRepository;
import com.springbootserver.domain.todo.domain.dto.request.TodoRequest;
import com.springbootserver.global.error.HttpException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDateTime;

import static com.springbootserver.global.content.TodoContent.TODO_INVALID_BEFORE_NOW;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class TodoServiceTest {

    @InjectMocks
    private TodoService todoService;

    @Mock
    private TodoRepository todoRepository;

    @Test
    @DisplayName("checkDateTimeIsValid 예외 테스트 - 예외 발생 ")
    void checkDateTimeIsValidThrowTest() {
        // given
        LocalDateTime beforeDateTime = LocalDateTime.now().minusHours(1L);
        // when
        HttpException httpException =
                assertThrows(HttpException.class,
                        () -> ReflectionTestUtils.invokeMethod(todoService, "checkDateTimeIsValid", beforeDateTime));
        // then
        assertAll(
                () -> assertEquals(httpException.getStatus(), HttpStatus.BAD_REQUEST),
                () -> assertEquals(httpException.getMessage(), TODO_INVALID_BEFORE_NOW)
        );
    }

    @Test
    @DisplayName("createTodo 테스트 - 정상 케이스 ")
    void createTodoGoodCaseTest() {
        // given
        LocalDateTime afterDateTime = LocalDateTime.now().plusDays(1L);
        Todo mockTodo = Todo.of("MD1234DFA", "프로그래머스 48432 문제 풀기!", afterDateTime);
        TodoRequest todoRequest = new TodoRequest(mockTodo.getUserId(), mockTodo.getContent(), mockTodo.getDateTime());
        given(todoRepository.save(any())).willReturn(mockTodo);
        // when
        Todo todo = todoService.createTodo(todoRequest);
        // then
        assertAll(
                () -> assertEquals(todo.getUserId(), mockTodo.getUserId()),
                () -> assertEquals(todo.getContent(), mockTodo.getContent()),
                () -> assertEquals(todo.getDateTime(), mockTodo.getDateTime())
        );
    }

}
