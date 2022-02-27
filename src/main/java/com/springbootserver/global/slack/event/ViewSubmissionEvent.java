package com.springbootserver.global.slack.event;

import com.slack.api.bolt.App;
import com.springbootserver.domain.todo.service.TodoService;
import com.springbootserver.global.slack.dto.AddTodo;
import com.springbootserver.global.slack.component.ChatPostMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.springbootserver.global.slack.content.IdList.*;

@Component
@RequiredArgsConstructor
public class ViewSubmissionEvent {

    private final TodoService todoService;

    public void addTodoViewSub(App app) {
        app.viewSubmission(ADD_TODO_CALLBACK_ID, (req, ctx) -> {
            var user = req.getPayload().getUser().getId();
            var state = req.getPayload().getView().getState();
            var content = state.getValues().get(ADD_TODO_TEXT_BLOCK).get(ADD_TODO_TEXT_VALUE).getValue();
            var date = state.getValues().get(ADD_TODO_DATE_BLOCK).get(ADD_TODO_DATE_VALUE).getSelectedDate();
            var time = state.getValues().get(ADD_TODO_TIME_BLOCK).get(ADD_TODO_TIME_VALUE).getSelectedTime();
            var message = "";
            try {
                AddTodo addTodo = AddTodo.of(user, content, date, time);
                todoService.createTodo(addTodo);
                message = "요청 성공☀️ " + date + " " + time + "으로 등록되었습니다!";
            } catch (RuntimeException e) {
                message = "요청 실패! " + e.getMessage();
            }
            ctx.client().chatPostMessage(ChatPostMessage.basicMessage(user, message));
            return ctx.ack();
        });
    }
}
