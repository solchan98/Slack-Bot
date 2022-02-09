package src.router;

import com.slack.api.bolt.App;
import com.slack.api.bolt.context.builtin.EventContext;
import com.slack.api.methods.SlackApiException;
import com.slack.api.model.event.MessageEvent;
import src.component.ChatPostMessage;
import src.component.Message;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.regex.Pattern;

public class MessageRouter implements Router{
    private static final MessageRouter INSTANCE = new MessageRouter();
    private static final Class<? extends MessageRouter> aClass = INSTANCE.getClass();
    private static final Pattern pattern = Message.getMessagePattern();

    private MessageRouter() {}

    public static Router getInstance() {
        return INSTANCE;
    }

    public void setAppRouter(App app) {
        app.message(pattern, (payload, ctx) -> {
            String text = payload.getEvent().getText();
            try {
                Method method = MessageRouter.aClass.getDeclaredMethod(text, MessageEvent.class, EventContext.class);
                method.invoke(this, payload.getEvent(), ctx);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return ctx.ack();
        });
    }

    private void hello(MessageEvent event, EventContext ctx) throws SlackApiException, IOException {
        String user = event.getUser();
//        String channel = event.getChannel();
        ctx.client().chatPostMessage(ChatPostMessage.getSayHello(user, user));
    }

    private void command(MessageEvent event, EventContext ctx) throws SlackApiException, IOException {
        String user = event.getUser();
        ctx.client().chatPostMessage(ChatPostMessage.commandList(user));
    }
}
