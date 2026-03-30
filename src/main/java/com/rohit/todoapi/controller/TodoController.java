package com.rohit.todoapi.controller;

import com.rohit.todoapi.entity.Todo;
import com.rohit.todoapi.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/todos")
public class TodoController {

        private  final TodoService todoService;

        public TodoController(TodoService todoService) {
            this.todoService = todoService;
        }

        // POST /tosos
        @PostMapping
        @ResponseStatus(HttpStatus.CREATED)
        public Todo createTodo(@RequestBody Todo todo) {
                return todoService.createTodo(todo);
        }

        // GET /todos
        @GetMapping
        public List<Todo> getAllTodos() {
                return todoService.getAllTodos();
        }

        // GET /todos/{id}
        @GetMapping("/{id}")
        public Todo getTodoById(@PathVariable Long id) {
                return todoService.getTodoById(id);
        }

        // PUT /todos/{id}
        @PutMapping("/{id}")
        public Todo updateTodo(@PathVariable Long id, @RequestBody Todo todo) {
                return todoService.updateTodo(id, todo);
        }

        // DELETE /todos/{id}
        @DeleteMapping("/{id}")
        @ResponseStatus(HttpStatus.NO_CONTENT)
        public void deleteTodo(@PathVariable Long id) {
                todoService.deleteTodo(id);
        }
}
