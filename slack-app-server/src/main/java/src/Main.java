package src;

import src.common.Container;
import src.event.ViewSubmissionEvent;

public class Main {

    public static void main(String[] args) {
        SlackApp slackApp = SlackApp.getInstance();
        slackApp.startSlackAppServer();
    }
}
