package src.router;

import com.slack.api.bolt.App;
import src.component.ChatPostMessage;
import src.dto.AddTodo;

public class ViewSubmissionEvent implements Event {

    private static final ViewSubmissionEvent INSTANCE = new ViewSubmissionEvent();
    private static final String CALL_BACK_ADD_TODO = "callback_add_todo";

    private ViewSubmissionEvent() {}

    public static Event getInstance() {
        return INSTANCE;
    }

    @Override
    public void setAppEvent(App app) {
        this.addTodoViewSub(app);
    }

    private void addTodoViewSub(App app) {
        app.viewSubmission(CALL_BACK_ADD_TODO, (req, ctx) -> {
            var user = req.getPayload().getUser().getId();
            var state = req.getPayload().getView().getState();
            var todo = state.getValues().get("add_todo_text").get("todo_value").getValue();
            var date = state.getValues().get("add_todo_date").get("date_value").getSelectedDate();
            var time = state.getValues().get("add_todo_time").get("time_value").getSelectedTime();
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
