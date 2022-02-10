package src.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import src.event.BlockActionEvent;
import src.event.MessageEvent;
import src.event.ViewSubmissionEvent;

public class Container {
    private Container() {}

    private static BlockActionEvent BLOCK_EVENT;
    private static ViewSubmissionEvent VIEW_SUBMIT;
    private static MessageEvent MESSAGE_EVENT;
    private static HttpTemplate HTTP_TEMPLATE;
    private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static {
        HttpTemplate.init();
        ViewSubmissionEvent.init();
        MessageEvent.init();
        BlockActionEvent.init();
    }

    public static void setBlockEvent(BlockActionEvent INSTANCE) {
        BLOCK_EVENT = INSTANCE;
    }

    public static BlockActionEvent getBlockEvent() {
        return BLOCK_EVENT;
    }

    public static void setMessageEvent(MessageEvent INSTANCE) {
        MESSAGE_EVENT = INSTANCE;
    }

    public static MessageEvent getMessageEvent() {
        return MESSAGE_EVENT;
    }

    public static void setViewSubmit(ViewSubmissionEvent INSTANCE) {
        VIEW_SUBMIT = INSTANCE;
    }

    public static ViewSubmissionEvent getViewSubmit() {
        return VIEW_SUBMIT;
    }

    public static void setHttpTemplate(HttpTemplate INSTANCE) {
        HTTP_TEMPLATE = INSTANCE;
    }

    public static HttpTemplate getHttpTemplate() {
        return HTTP_TEMPLATE;
    }

    public static ObjectMapper getObjectInstance() {
        return OBJECT_MAPPER;
    }
}
