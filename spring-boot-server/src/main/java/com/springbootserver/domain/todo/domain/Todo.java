package com.springbootserver.domain.todo.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;

    private String content;

    private LocalDateTime dateTime;

    private boolean isDone;

    private Todo(String userId, String content, LocalDateTime dateTime) {
        this.userId = userId;
        this.content = content;
        this.dateTime = dateTime;
    }

    public static Todo of(String userId, String content, LocalDateTime dateTime) {
        return new Todo(userId, content, dateTime);
    }
}
