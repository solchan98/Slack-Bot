package com.springbootserver.global.slack.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static com.springbootserver.global.slack.content.Content.TIME_INVALID;


public class AddTodo {
    private final String userId;
    private final String content;
    private final String dateTime;

    private AddTodo(String userId, String content, String dateTime) {
        this.userId = userId;
        this.content = content;
        this.dateTime = dateTime;
    }

    public static AddTodo of(String userId, String content, String dateStr, String timeStr) throws RuntimeException {
        LocalDate date = LocalDate.parse(dateStr);
        LocalTime time = LocalTime.parse(timeStr);
        LocalDateTime dateTime = LocalDateTime.of(date, time);
        if(dateTime.isBefore(LocalDateTime.now())) {
            throw new RuntimeException(TIME_INVALID);
        }
        return new AddTodo(userId, content, dateTime.toString());
    }

    public String getContent() {
        return content;
    }

    public String getDateTime() {
        return dateTime;
    }

    public String getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "AddTodo{" +
                "content='" + content + '\'' +
                ", dateTime=" + dateTime +
                '}';
    }
}
