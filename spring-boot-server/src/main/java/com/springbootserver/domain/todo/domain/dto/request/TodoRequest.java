package com.springbootserver.domain.todo.domain.dto.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TodoRequest {
    private String userId;
    private String content;
    private LocalDateTime dateTime;
}
