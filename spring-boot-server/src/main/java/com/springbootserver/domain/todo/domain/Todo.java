package com.springbootserver.domain.todo.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
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
        this.isDone = false;
    }

    public static Todo of(String userId, String content, LocalDateTime dateTime) {
        return new Todo(userId, content, dateTime);
    }
}
