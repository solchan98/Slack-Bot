package src.router;

import com.slack.api.bolt.App;
import com.slack.api.bolt.context.builtin.EventContext;
import com.slack.api.methods.SlackApiException;
import src.component.ChatPostMessage;
import src.component.Message;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.regex.Pattern;

public class MessageEvent implements Event {
    private static final MessageEvent INSTANCE = new MessageEvent();
    private static final Class<? extends MessageEvent> aClass = INSTANCE.getClass();
    private static final Pattern pattern = Message.getMessagePattern();

    private MessageEvent() {}

    public static Event getInstance() {
        return INSTANCE;
    }

    public void setAppEvent(App app) {
        app.message(pattern, (payload, ctx) -> {
            String text = payload.getEvent().getText();
            try {
                Method method = MessageEvent.aClass.getDeclaredMethod(text, com.slack.api.model.event.MessageEvent.class, EventContext.class);
                method.invoke(this, payload.getEvent(), ctx);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return ctx.ack();
        });
    }

    private void hello(com.slack.api.model.event.MessageEvent event, EventContext ctx) throws SlackApiException, IOException {
        String user = event.getUser();
//        String channel = event.getChannel();
        ctx.client().chatPostMessage(ChatPostMessage.getSayHello(user, user));
    }

    private void command(com.slack.api.model.event.MessageEvent event, EventContext ctx) throws SlackApiException, IOException {
        String user = event.getUser();
        ctx.client().chatPostMessage(ChatPostMessage.commandList(user));
    }
}
