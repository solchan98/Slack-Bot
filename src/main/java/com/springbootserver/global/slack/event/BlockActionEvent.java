package com.springbootserver.global.slack.event;

import com.slack.api.bolt.App;
import com.springbootserver.global.slack.component.ModalView;

import static com.springbootserver.global.slack.content.IdList.*;

public class BlockActionEvent {

    private BlockActionEvent() {}

    public static void setAppEvent(App app) {
        addTodoBlockAction(app);
    }

    private static void addTodoBlockAction(App app) {
        app.blockAction(ADD_TODO_BTN, (req, ctx) -> {
            ctx.client().viewsOpen(v -> v
                    .triggerId(req.getPayload().getTriggerId())
                    .view(ModalView.ADD_TODO));
            // todo logging by response
            return ctx.ack();
        });
    }
}
