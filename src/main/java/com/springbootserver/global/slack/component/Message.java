package com.springbootserver.global.slack.component;

import java.util.regex.Pattern;

public enum Message {
    HELLO("hello"),
    COMMAND("command");

    Message(String message) {}

    public static Pattern getMessagePattern() {
        StringBuilder pattern = new StringBuilder();
        Message[] values = Message.values();
        for(Message value: values) {
            pattern.append(value.name().toLowerCase()).append("|");
        }
        pattern = new StringBuilder(pattern.substring(0, pattern.length() - 1));
        return Pattern.compile(pattern.toString());
    }
}
