package src.router;

import com.slack.api.bolt.App;

public interface Event {
    void setAppEvent(App app);
}
