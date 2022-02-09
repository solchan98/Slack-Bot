package src.component;

import java.util.regex.Pattern;

public enum Message {
    HELLO("hello"),
    COMMAND("command");

    Message(String message) {}

    public static Pattern getMessagePattern() {
        String pattern = "";
        Message[] values = Message.values();
        for(Message value: values) {
            pattern += value.name().toLowerCase() + "|";
        }
        System.out.println(pattern.substring(0, pattern.length() - 1));
        pattern = pattern.substring(0, pattern.length() - 1);
        return Pattern.compile(pattern);
    }
}
