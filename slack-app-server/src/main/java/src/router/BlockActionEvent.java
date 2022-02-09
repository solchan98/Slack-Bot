package src.router;

import com.slack.api.bolt.App;
import src.component.ModalView;

public class BlockActionEvent implements Event {

    private static final BlockActionEvent INSTANCE = new BlockActionEvent();

    private BlockActionEvent() {}

    public static Event getInstance() {
        return INSTANCE;
    }

    @Override
    public void setAppEvent(App app) {
        this.addBlockAction(app);
    }

    private void addBlockAction(App app) {
        app.blockAction("add_todo", (req, ctx) -> {
            var response = ctx.client().viewsOpen(v -> v
                    .triggerId(req.getPayload().getTriggerId())
                    .view(ModalView.ADD_TODO));
            // todo logging by response
            return ctx.ack();

        });
    }
}
