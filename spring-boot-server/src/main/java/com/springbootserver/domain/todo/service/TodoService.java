package com.springbootserver.domain.todo.service;

import com.springbootserver.domain.todo.domain.Todo;
import com.springbootserver.domain.todo.domain.dao.TodoRepository;
import com.springbootserver.domain.todo.domain.dto.request.TodoRequest;
import com.springbootserver.global.error.HttpException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static com.springbootserver.global.content.TodoContent.TODO_INVALID_BEFORE_NOW;

@Service
@Transactional
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    public Todo createTodo(TodoRequest todoRequest) {
        this.checkDateTimeIsValid(todoRequest.getDateTime());
        Todo todo = Todo.of(todoRequest.getUserId(), todoRequest.getContent(), todoRequest.getDateTime());
        return todoRepository.save(todo);
    }

    private void checkDateTimeIsValid(LocalDateTime dateTime) {
        LocalDateTime now = LocalDateTime.now();
        if(dateTime.isBefore(now)) {
            throw new HttpException(HttpStatus.BAD_REQUEST, TODO_INVALID_BEFORE_NOW);
        }
    }
}
