package src;

import com.slack.api.bolt.App;
import com.slack.api.bolt.AppConfig;
import com.slack.api.bolt.jetty.SlackAppServer;
import src.event.BlockActionEvent;
import src.event.MessageEvent;
import src.event.ViewSubmissionEvent;

public class Main {

    private static final String SINGLE_TOKEN = "";
    private static final String OAUTH_TOKEN = "";
    
    public static void main(String[] args) {

        AppConfig appConfig = AppConfig.builder().singleTeamBotToken(OAUTH_TOKEN).signingSecret(SINGLE_TOKEN).build();
        App app = new App(appConfig);

        MessageEvent.setAppEvent(app);
        BlockActionEvent.setAppEvent(app);
        ViewSubmissionEvent.setAppEvent(app);

        SlackAppServer server = new SlackAppServer(app);
        try {
            server.start();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
