package com.springbootserver.domain.todo.domain.dto.response;

import com.springbootserver.global.common.BasicResponse;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
public class TodoResponse extends BasicResponse {
    private final String userId;
    private final String content;
    private final LocalDateTime dateTime;
    private final boolean isDone;

    private TodoResponse(String userId, String content, LocalDateTime dateTime, boolean isDone, HttpStatus status, String message) {
        super(status, message);
        this.userId = userId;
        this.content = content;
        this.dateTime = dateTime;
        this.isDone = isDone;
    }

    public static TodoResponse of(String userId, String content, LocalDateTime dateTime, boolean isDone, HttpStatus status, String message) {
        return new TodoResponse(userId, content, dateTime, isDone, status, message);
    }

    public static TodoResponse of(String userId, String content, LocalDateTime dateTime, HttpStatus status, String message) {
        return new TodoResponse(userId, content, dateTime, false, status, message);
    }

    @Override
    public String toString() {
        return "TodoResponse{" +
                "userId='" + userId + '\'' +
                ", content='" + content + '\'' +
                ", dateTime=" + dateTime +
                ", isDone=" + isDone +
                '}';
    }
}
