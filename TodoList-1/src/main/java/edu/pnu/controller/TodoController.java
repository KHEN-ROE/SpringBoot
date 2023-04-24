package edu.pnu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.persistence.TodoRepository;
import pnu.edu.domain.Todo;

@RestController
@RequestMapping("/api/todos")
public class TodoController {
	
	@Autowired
    TodoRepository todoRepository;

    public TodoController(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @GetMapping
    public Iterable<Todo> getTodos() {
        return todoRepository.findAll();
    }

    @PostMapping
    public Todo addTodo(@RequestBody Todo todo) {
        return todoRepository.save(todo);
    }
}

