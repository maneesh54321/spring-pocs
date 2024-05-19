package com.ms.todo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

public record Todo(
		@Id
		Integer id,
		String description,
		Boolean completed,
		@Version
		Integer version) {
}
