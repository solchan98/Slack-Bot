package src;

public class Main {

    public static void main(String[] args) {
        SlackApp slackApp = SlackApp.getInstance();
        slackApp.startSlackAppServer();
    }
}
