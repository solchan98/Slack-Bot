package com.springbootserver.global.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = HttpException.class)
    public ResponseEntity<Message> handle(HttpException e){
        Message message = Message.builder().status(HttpStatus.BAD_REQUEST).message(e.getMessage()).build();
        return ResponseEntity.status(e.getStatus()).body(message);
    }
}
