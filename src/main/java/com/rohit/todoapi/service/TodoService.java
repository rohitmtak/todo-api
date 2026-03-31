package com.rohit.todoapi.service;
import com.rohit.todoapi.entity.Todo;
import com.rohit.todoapi.exception.TodoNotFoundException;
import com.rohit.todoapi.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    // Create
    public Todo createTodo(Todo todo) {
        if (todo.getTitle() == null || todo.getTitle().isBlank()) {
            throw new IllegalArgumentException("Title is required to create a Todo");
        }
        return todoRepository.save(todo);
    }

    // Read all
    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    // Read one
    public Todo getTodoById(Long id) {
        return getTodoOrThrow(id);
    }

    // Update
    public Todo updateTodo(Long id, Todo updatedTodo) {
        // 1. Fetch first: Get the object or throw immediately
        Todo todo = getTodoOrThrow(id);

        // 2. Validate: check incoming data
        if (updatedTodo.getTitle() == null || updatedTodo.getTitle().isBlank()) {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }

        if(updatedTodo.)

        // 3. Modify: if valid, update the fields
        todo.setTitle(updatedTodo.getTitle());
        todo.setDescription(updatedTodo.getDescription());
        todo.setCompleted(updatedTodo.isCompleted());

        // 4. Save: Return the result
        return todoRepository.save(todo);
    }

    // Delete
    public void deleteTodo(Long id) {
        Todo todo = getTodoOrThrow(id);
        todoRepository.delete(todo);
    }

    private Todo getTodoOrThrow(Long id) {
        return todoRepository.findById(id)
                .orElseThrow(() -> new TodoNotFoundException(id));
    }

}


