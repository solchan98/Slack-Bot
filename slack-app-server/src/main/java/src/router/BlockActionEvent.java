package src.router;

import com.slack.api.bolt.App;

public class BlockActionEvent implements Event {

    private static final BlockActionEvent INSTANCE = new BlockActionEvent();

    private BlockActionEvent() {}

    public static Event getInstance() {
        return INSTANCE;
    }

    @Override
    public void setAppEvent(App app) {
        //
    }
}
