package com.springbootserver.domain.todo.domain.dto.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TodoRequest {
    private String userId;
    private String content;
    private LocalDateTime dateTime;


    // Test용 생성자!
    public TodoRequest(String userId, String content, LocalDateTime dateTime) {
        this.userId = userId;
        this.content = content;
        this.dateTime = dateTime;
    }
}
