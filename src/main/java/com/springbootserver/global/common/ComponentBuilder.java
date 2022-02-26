package com.springbootserver.global.common;

import com.slack.api.methods.request.chat.ChatPostMessageRequest;
import com.slack.api.model.block.LayoutBlock;
import com.springbootserver.domain.todo.domain.Todo;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.slack.api.model.block.Blocks.divider;
import static com.slack.api.model.block.Blocks.section;
import static com.slack.api.model.block.composition.BlockCompositions.markdownText;

@Component
public class ComponentBuilder {
    private ComponentBuilder() {}

    public static ChatPostMessageRequest getTodoListComponent(String userId, List<Todo> todoList) {
        String message = LocalDate.now() + " 일정이 도착했어요!🗓";
        ChatPostMessageRequest component = getBasicChatPostComponent(userId, message);
        addTodoListBody(todoList, component);
        return component;
    }

    private static void addTodoListBody(List<Todo> todoList, ChatPostMessageRequest component) {
        Collections.sort(todoList);
        List<LayoutBlock> layoutBlockList = new ArrayList<>(todoList.size() * 2);
        layoutBlockList.add(section(s -> s.text(markdownText(mk -> mk.text("*오늘도 계획한 일을 모두 할 수 있도록 화이팅!👏*")))));
        layoutBlockList.add(divider());
        todoList.forEach(todo -> {
            String content = todo.getContent();
            String dateTime = String.valueOf(todo.getDateTime()).replace("T", " ");
            String bodyMessage = "*" + content + "*\n" + dateTime;
            layoutBlockList.add(section(s -> s.text(markdownText(mk -> mk.text(bodyMessage)))));
        });
        component.setBlocks(layoutBlockList);
    }

    private static ChatPostMessageRequest getBasicChatPostComponent(String userId, String message) {
        return ChatPostMessageRequest.builder()
                .channel(userId)
                .text(message).build();
    }
}