package src.event;

import com.slack.api.bolt.App;
import src.common.Container;
import src.component.ModalView;

import static src.common.IdList.ADD_TODO_BTN;

public class BlockActionEvent implements Event {

    private static final BlockActionEvent INSTANCE = new BlockActionEvent();

    private BlockActionEvent() {}

    public static void init() {
        Container.setBlockEvent(INSTANCE);
    }

    @Override
    public void setAppEvent(App app) {
        this.addTodoBlockAction(app);
    }

    private void addTodoBlockAction(App app) {
        app.blockAction(ADD_TODO_BTN, (req, ctx) -> {
            var response = ctx.client().viewsOpen(v -> v
                    .triggerId(req.getPayload().getTriggerId())
                    .view(ModalView.ADD_TODO));
            // todo logging by response
            return ctx.ack();

        });
    }
}
