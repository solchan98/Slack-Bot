package src;

import com.slack.api.bolt.App;
import com.slack.api.bolt.AppConfig;
import com.slack.api.bolt.jetty.SlackAppServer;
import src.router.Message;

public class SlackApp {

    private static final App app;
    private static final SlackAppServer slackAppServer;
    private static final SlackApp INSTANCE = new SlackApp();
    // todo env
    private static final String SINGLE_TOKEN = "";
    private static final String OAUTH_TOKEN = "";

    static {
        AppConfig appConfig = AppConfig.builder().singleTeamBotToken(OAUTH_TOKEN).signingSecret(SINGLE_TOKEN).build();
        app = new App(appConfig);
        slackAppServer = new SlackAppServer(app);
    }

    static {
        Message.setAppMessageRouter(app);
    }

    private SlackApp() {}

    public static SlackApp getInstance() {
        return INSTANCE;
    }

    public void startSlackAppServer() {
        try {
            slackAppServer.start();
        } catch (Exception e) {
            // todo logging
            System.out.println(e.getMessage());
        }
    }

    public void stopSlackAppServer() {
        try {
            slackAppServer.stop();
        } catch (Exception e) {
            // todo logging
            System.out.println(e.getMessage());
        }
    }
}
