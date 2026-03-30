package com.rohit.todoapi.service;
import com.rohit.todoapi.entity.Todo;
import com.rohit.todoapi.exception.TodoNotFoundException;
import com.rohit.todoapi.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    private final TodoRepository repository;

    public TodoService(TodoRepository repository) {
        this.repository = repository;
    }

    // Create
    public Todo createTodo(Todo todo) {
        if (todo.getTitle() == null || todo.getTitle().isBlank()) {
            throw new IllegalArgumentException("Title is required to create a Todo");
        }
        return repository.save(todo);
    }

    // Read all
    public List<Todo> getAllTodos() {
        return repository.findAll();
    }

    // Read one
    public Todo getTodoById(Long id) {
        return repository.findById(id).orElseThrow(() -> new TodoNotFoundException(id));
    }

    // Update
    public Todo updateTodo(Long id, Todo updatedTodo) {
        // 1. Fetch first: Get the object or throw immediately
        Todo todo = repository.findById(id)
                .orElseThrow(() -> new TodoNotFoundException(id));

        // 2. Validate: check incoming data
        if (updatedTodo.getTitle() == null || updatedTodo.getTitle().isBlank()) {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }

        // 3. Modify: if valid, update the fields
        todo.setTitle(updatedTodo.getTitle());
        if (updatedTodo.getDescription() != null) {
            todo.setDescription(updatedTodo.getDescription());
        }

        todo.setCompleted(false);

        // 4. Save: Return the result
        return repository.save(todo);
    }

    // Delete
    public void deleteTodo(Long id) {
        Todo todo = repository.findById(id)
                .orElseThrow(() -> new TodoNotFoundException(id));
        repository.delete(todo);
    }

}


