package com.ms.todo.repository;

import com.ms.todo.model.Todo;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends ListCrudRepository<Todo, Integer> {
}
