package src.router;

import com.slack.api.bolt.App;

public class BlockActionRouter implements Router {

    private static final BlockActionRouter INSTANCE = new BlockActionRouter();

    private BlockActionRouter() {}

    public static Router getInstance() {
        return INSTANCE;
    }

    @Override
    public void setAppRouter(App app) {
        //
    }
}
