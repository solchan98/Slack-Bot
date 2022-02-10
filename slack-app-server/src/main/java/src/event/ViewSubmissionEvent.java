package src.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.slack.api.bolt.App;
import src.common.Container;
import src.common.HttpTemplate;
import src.component.ChatPostMessage;
import src.dto.AddTodo;

import static src.common.IdList.*;
import static src.common.UrlList.URL_ADD_TODO;

public class ViewSubmissionEvent implements Event {

    private static final ViewSubmissionEvent INSTANCE = new ViewSubmissionEvent();

    private ViewSubmissionEvent() {}

    public static Event getInstance() {
        return INSTANCE;
    }
    private final static HttpTemplate httpTemplate = Container.getHttpTemplate();
    private final static ObjectMapper objectMapper = Container.getObjectInstance();

    @Override
    public void setAppEvent(App app) {
        this.addTodoViewSub(app);
    }

    private void addTodoViewSub(App app) {
        app.viewSubmission(ADD_TODO_CALLBACK_ID, (req, ctx) -> {
            var user = req.getPayload().getUser().getId();
            var state = req.getPayload().getView().getState();
            var content = state.getValues().get(ADD_TODO_TEXT_BLOCK).get(ADD_TODO_TEXT_VALUE).getValue();
            var date = state.getValues().get(ADD_TODO_DATE_BLOCK).get(ADD_TODO_DATE_VALUE).getSelectedDate();
            var time = state.getValues().get(ADD_TODO_TIME_BLOCK).get(ADD_TODO_TIME_VALUE).getSelectedTime();
            try {
                AddTodo addTodo = AddTodo.of(user, content, date, time);
                String todoJson = objectMapper.writeValueAsString(addTodo);
                httpTemplate.sendPostByJson(URL_ADD_TODO, todoJson);
                var message = "요청 성공☀️ " + date + " " + time + "으로 등록되었습니다!";
                ctx.client().chatPostMessage(ChatPostMessage.basicMessage(user, message));
                // todo logging SUCCESS
            } catch (RuntimeException e) {
                var message = "요청 실패‼️ " + e.getMessage();
                ctx.client().chatPostMessage(ChatPostMessage.basicMessage(user, message));
                // todo logging FAIL cause with userId and message
            }
            return ctx.ack();
        });
    }
}
