package com.springbootserver.global.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class HttpException extends RuntimeException{
    private final HttpStatus status;
    public HttpException(HttpStatus status, String message){
        super(message);
        this.status = status;
    }
}
