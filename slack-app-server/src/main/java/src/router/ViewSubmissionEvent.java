package src.router;

import com.slack.api.bolt.App;

public class ViewSubmissionEvent implements Event {

    private static final ViewSubmissionEvent INSTANCE = new ViewSubmissionEvent();

    private ViewSubmissionEvent() {}

    public static Event getInstance() {
        return INSTANCE;
    }

    @Override
    public void setAppEvent(App app) {
        //
    }
}
