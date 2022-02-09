package src.event;

import com.slack.api.bolt.App;
import src.component.ChatPostMessage;
import src.dto.AddTodo;

import static src.common.IdList.*;

public class ViewSubmissionEvent implements Event {

    private static final ViewSubmissionEvent INSTANCE = new ViewSubmissionEvent();

    private ViewSubmissionEvent() {}

    public static Event getInstance() {
        return INSTANCE;
    }

    @Override
    public void setAppEvent(App app) {
        this.addTodoViewSub(app);
    }

    private void addTodoViewSub(App app) {
        app.viewSubmission(ADD_TODO_CALLBACK_ID, (req, ctx) -> {
            var user = req.getPayload().getUser().getId();
            var state = req.getPayload().getView().getState();
            var todo = state.getValues().get(ADD_TODO_TEXT_BLOCK).get(ADD_TODO_TEXT_VALUE).getValue();
            var date = state.getValues().get(ADD_TODO_DATE_BLOCK).get(ADD_TODO_DATE_VALUE).getSelectedDate();
            var time = state.getValues().get(ADD_TODO_TIME_BLOCK).get(ADD_TODO_TIME_VALUE).getSelectedTime();
            try {
                AddTodo addTodo = AddTodo.of(todo, date, time);
                /* todo request server to add data(addTodo)
                 HttpRequest... */
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
