package com.springbootserver.domain.todo.controller;

import com.springbootserver.domain.todo.domain.Todo;
import com.springbootserver.domain.todo.domain.dto.request.TodoRequest;
import com.springbootserver.domain.todo.domain.dto.response.TodoResponse;
import com.springbootserver.domain.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.springbootserver.global.content.TodoContent.TODO_CREATE_OK;

@RestController
@RequestMapping("/api/v1/todo")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @PostMapping("")
    public ResponseEntity<TodoResponse> createTodo(@RequestBody TodoRequest todoRequest) {
        Todo todo = todoService.createTodo(todoRequest);
        TodoResponse response =
                TodoResponse.of(todo.getUserId(), todo.getContent(), todo.getDateTime(), HttpStatus.CREATED, TODO_CREATE_OK);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
