package com.springbootserver.domain.todo.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
public class Todo implements Comparable<Todo> {

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

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", content='" + content + '\'' +
                ", dateTime=" + dateTime +
                ", isDone=" + isDone +
                '}';
    }

    @Override
    public int compareTo(Todo o) {
        if (this.dateTime.isBefore(o.getDateTime())) {
            return -1;
        } else if (this.dateTime.isAfter(o.getDateTime())) {
            return 1;
        }
        return 0;
    }
}
