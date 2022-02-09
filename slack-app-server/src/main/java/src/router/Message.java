package src.router;

import com.slack.api.bolt.App;
import src.component.ChatPostMessage;

public class Message {
    private Message() {}

    public static void setAppMessageRouter(App app) {
        sayHelloToDm(app);
//        sayHelloToChannel(app);
    }

    private static void sayHelloToChannel(App app) {
        app.message("hello", (payload, ctx) -> {
            String channel = payload.getEvent().getChannel();
            String user = payload.getEvent().getUser();
            ctx.client().chatPostMessage(ChatPostMessage.getSayHello(channel, user));
            return ctx.ack();
        });
    }

    private static void sayHelloToDm(App app) {
        app.message("hello", (payload, ctx) -> {
            String user = payload.getEvent().getUser();
            ctx.client().chatPostMessage(ChatPostMessage.getSayHello(user, user));
            return ctx.ack();
        });
    }
}
