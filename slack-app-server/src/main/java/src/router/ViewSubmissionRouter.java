package src.router;

import com.slack.api.bolt.App;

public class ViewSubmissionRouter implements Router {

    private static final ViewSubmissionRouter INSTANCE = new ViewSubmissionRouter();

    private ViewSubmissionRouter() {}

    public static Router getInstance() {
        return INSTANCE;
    }

    @Override
    public void setAppRouter(App app) {
        //
    }
}
