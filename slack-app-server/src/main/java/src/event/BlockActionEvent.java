package src.event;

import com.slack.api.bolt.App;
import src.component.ModalView;

import static src.common.IdList.ADD_TODO_BTN;

public class BlockActionEvent {

    private BlockActionEvent() {}

    public static void setAppEvent(App app) {
        addTodoBlockAction(app);
    }

    private static void addTodoBlockAction(App app) {
        app.blockAction(ADD_TODO_BTN, (req, ctx) -> {
            var response = ctx.client().viewsOpen(v -> v
                    .triggerId(req.getPayload().getTriggerId())
                    .view(ModalView.ADD_TODO));
            // todo logging by response
            return ctx.ack();
        });
    }
}
