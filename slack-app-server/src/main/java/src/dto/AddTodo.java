package src.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class AddTodo {
    private final String todo;
    private final LocalDateTime dateTime;

    private AddTodo(String todo, LocalDateTime dateTime) {
        this.todo = todo;
        this.dateTime = dateTime;
    }

    public static AddTodo of(String todo, String dateStr, String timeStr) throws RuntimeException {
        LocalDate date = LocalDate.parse(dateStr);
        LocalTime time = LocalTime.parse(timeStr);
        LocalDateTime dateTime = LocalDateTime.of(date, time);
        if(dateTime.isBefore(LocalDateTime.now())) {
            throw new RuntimeException("현재보다 이전으로 예약은 불가능합니다.");
        }
        return new AddTodo(todo, dateTime);
    }

    public String getTodo() {
        return todo;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    @Override
    public String toString() {
        return "AddTodo{" +
                "todo='" + todo + '\'' +
                ", dateTime=" + dateTime +
                '}';
    }
}
