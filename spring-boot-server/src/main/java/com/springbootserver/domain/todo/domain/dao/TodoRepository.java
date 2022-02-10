package com.springbootserver.domain.todo.domain.dao;

import com.springbootserver.domain.todo.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}
