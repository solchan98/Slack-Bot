package com.springbootserver.domain.todo.service;

import com.slack.api.Slack;
import com.slack.api.methods.SlackApiException;
import com.slack.api.methods.request.chat.ChatPostMessageRequest;
import com.springbootserver.domain.todo.domain.Todo;
import com.springbootserver.domain.todo.domain.dao.TodoRepository;
import com.springbootserver.global.common.ComponentBuilder;
import com.springbootserver.global.error.HttpException;
import com.springbootserver.global.slack.dto.AddTodo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static com.springbootserver.global.content.TodoContent.TODO_INVALID_BEFORE_NOW;

@Service
@Transactional
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;
    private final Slack slack = Slack.getInstance();

    @Value("${slack.oauth.token}")
    private String SLACK_TOKEN;

    public void createTodo(AddTodo addTodo) {
        LocalDateTime dateTime = LocalDateTime.parse(addTodo.getDateTime());
        this.checkDateTimeIsValid(dateTime);
        Todo todo = Todo.of(addTodo.getUserId(), addTodo.getContent(), dateTime);
        todoRepository.save(todo);
    }

    private void checkDateTimeIsValid(LocalDateTime dateTime) {
        LocalDateTime now = LocalDateTime.now();
        if(dateTime.isBefore(now)) {
            throw new RuntimeException(TODO_INVALID_BEFORE_NOW);
        }
    }

    public void sendNotificationTodoList() {
        Map<String, List<Todo>> todoMap = todoRepository.getTodoMapByIsDoneIsFalse();
        for(String userId: todoMap.keySet()) {
            ChatPostMessageRequest component = ComponentBuilder.getTodoListComponent(userId, todoMap.get(userId));
            try {
                slack.methods(SLACK_TOKEN).chatPostMessage(component);
            } catch (IOException | SlackApiException e) {
                e.printStackTrace();
            }
            // Todo 수행여부 설정하는 기능 구현되면 아래 로직은 삭제한다.
            todoMap.get(userId).forEach(Todo::performTodo);
        }
    }
}