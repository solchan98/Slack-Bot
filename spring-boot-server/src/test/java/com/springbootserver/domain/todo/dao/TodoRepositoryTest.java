package com.springbootserver.domain.todo.dao;

import com.springbootserver.domain.todo.domain.Todo;
import com.springbootserver.domain.todo.domain.dao.TodoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DisplayName("Todo Repository 테스트")
public class TodoRepositoryTest {

    @Autowired
    private TodoRepository todoRepository;

    @Test
    @DisplayName("save() 테스트")
    void saveTest() {
        Todo todo = Todo.of("MDS31DF3", "*** 테스트 케이스 작성하기!", LocalDateTime.now().plusDays(2L));
        Todo savedTodo = todoRepository.save(todo);
        assertAll(
                () -> assertEquals(savedTodo.getUserId(), todo.getUserId()),
                () -> assertEquals(savedTodo.getContent(), todo.getContent()),
                () -> assertEquals(savedTodo.getDateTime(), todo.getDateTime())
        );
    }
}
