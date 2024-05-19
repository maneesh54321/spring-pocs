package com.ms.todo.controller;

import com.ms.todo.model.Todos;
import com.ms.todo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodoController {

	@Autowired
	private TodoRepository todoRepository;

	@GetMapping("/todos")
	public Todos getAllTodos() {
		return new Todos(todoRepository.findAll());
	}
}
