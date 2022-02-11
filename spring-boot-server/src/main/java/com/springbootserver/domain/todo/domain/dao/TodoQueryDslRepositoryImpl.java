package com.springbootserver.domain.todo.domain.dao;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.springbootserver.domain.todo.domain.Todo;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

import static com.springbootserver.domain.todo.domain.QTodo.*;

@RequiredArgsConstructor
public class TodoQueryDslRepositoryImpl implements TodoQueryDslRepository {

    private final JPAQueryFactory query;

    @Override
    public Map<String, List<Todo>> getTodoMapByIsDoneIsFalse() {
        Map<String , List<Todo>> todoMap = new HashMap<>();
        LocalDate localDate = LocalDate.now().plusDays(1);
        LocalTime of = LocalTime.of(0, 0);
        LocalDateTime tomorrow = LocalDateTime.of(localDate, of);
        List<Todo> fetch = query
                .selectFrom(todo)
                .where(todo.isDone.eq(false).and(todo.dateTime.before(tomorrow)))
                .fetch();

        fetch.forEach(t -> {
            if(Objects.isNull(todoMap.get(t.getUserId()))) {
                List<Todo> tempList = new ArrayList<>();
                tempList.add(t);
                todoMap.put(t.getUserId(), tempList);
            } else {
                todoMap.get(t.getUserId()).add(t);
            }
        });
        return todoMap;
    }
}
