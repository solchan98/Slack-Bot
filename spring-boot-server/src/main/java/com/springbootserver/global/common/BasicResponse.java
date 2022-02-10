package com.springbootserver.global.common;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class BasicResponse {
    private HttpStatus status;
    private String message;

    protected BasicResponse(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
