package com.springbootserver.domain.todo.domain.dao;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.springbootserver.domain.todo.domain.Todo;
import lombok.RequiredArgsConstructor;

import java.util.*;

import static com.springbootserver.domain.todo.domain.QTodo.*;

@RequiredArgsConstructor
public class TodoQueryDslRepositoryImpl implements TodoQueryDslRepository {

    private final JPAQueryFactory query;

    @Override
    public Map<String, List<Todo>> getTodoMapByIsDoneIsFalse() {
        Map<String , List<Todo>> todoMap = new HashMap<>();
        List<Todo> fetch = query
                .selectFrom(todo)
                .where(todo.isDone.eq(false))
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
