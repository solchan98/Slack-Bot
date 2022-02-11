package com.springbootserver.domain.todo.domain.dao;

import com.springbootserver.domain.todo.domain.Todo;

import java.util.List;
import java.util.Map;

public interface TodoQueryDslRepository {
    Map<String, List<Todo>> getTodoMapByIsDoneIsFalse();
}
