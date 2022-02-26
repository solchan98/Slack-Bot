package com.springbootserver.global.scheduler;

import com.springbootserver.domain.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationScheduler {

    private final TodoService todoService;


    @Scheduled(cron = "0 0 8 * * *", zone = "Asia/Seoul")
    public void sendNotificationTodoList() {
        todoService.sendNotificationTodoList();
    }
}
