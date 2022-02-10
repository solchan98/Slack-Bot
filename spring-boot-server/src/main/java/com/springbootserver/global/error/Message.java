package com.springbootserver.global.error;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Builder
@Getter
public class Message {
    private HttpStatus status;
    private String message;
}
