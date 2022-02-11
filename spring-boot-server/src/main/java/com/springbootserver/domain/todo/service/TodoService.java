package com.springbootserver.domain.todo.service;

import com.google.gson.Gson;
import com.slack.api.methods.request.chat.ChatPostMessageRequest;
import com.springbootserver.domain.todo.domain.Todo;
import com.springbootserver.domain.todo.domain.dao.TodoRepository;
import com.springbootserver.domain.todo.domain.dto.request.TodoRequest;
import com.springbootserver.global.common.ComponentBuilder;
import com.springbootserver.global.error.HttpException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static com.springbootserver.global.content.TodoContent.TODO_INVALID_BEFORE_NOW;

@Service
@Transactional
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;
    private final RestTemplate restTemplate;
    private final Gson gson;

    @Value("${slack.token}")
    private String SLACK_TOKEN;

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

    public void sendNotificationTodoList() {
        String url = "https://slack.com/api/chat.postMessage";
        Map<String, List<Todo>> todoMap = todoRepository.getTodoMapByIsDoneIsFalse();
        for(String userId: todoMap.keySet()) {
            ChatPostMessageRequest component = ComponentBuilder.getTodoListComponent(userId, todoMap.get(userId));
            String componentJson = gson.toJson(component);
            this.sendToBotDmByUrlAndJsonBody(url, componentJson);
            // Todo 수행여부 설정하는 기능 구현되면 아래 로직은 삭제한다.
            todoMap.get(userId).forEach(Todo::performTodo);
        }
    }

    private void sendToBotDmByUrlAndJsonBody(String url, String body) {
        HttpHeaders header = new HttpHeaders();
        header.set("Content-Type", "application/json");
        header.set("Authorization", "Bearer " + SLACK_TOKEN);
        HttpEntity<String> entity = new HttpEntity<>(body, header);
        ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        // todo sout -> logging
        System.out.println(exchange);
    }
}