package com.rohit.todoapi.controller;

import com.rohit.todoapi.entity.Todo;
import com.rohit.todoapi.service.TodoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/todos")
public class TodoController {

        private  final TodoService todoService;

        public TodoController(TodoService todoService) {
            this.todoService = todoService;
        }

        @PostMapping
        public ResponseEntity<Todo> createTodo(@RequestBody Todo todo) {
                // later i can use DTOs @RequestBody CreateTodoRequest request
                return ResponseEntity.ok(todoService.createTodo(todo));
        }

        @GetMapping
        public ResponseEntity<List<Todo>> getAllTodos() {
                return ResponseEntity.ok(todoService.getAllTodos());
        }

        @GetMapping("/{id}")
        public ResponseEntity<Todo> getTodoById(@PathVariable Long id) {
                return ResponseEntity.ok(todoService.getTodoById(id));
        }

        @PutMapping("/{id}")
        public ResponseEntity<Todo> updateTodo(@PathVariable Long id, @RequestBody Todo todo) {
                // later i can use DTOs @RequestBody UpdateTodoRequest request
                return ResponseEntity.ok(todoService.updateTodo(id, todo));
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
                todoService.deleteTodo(id);
                return ResponseEntity.ok().build();
        }
}
