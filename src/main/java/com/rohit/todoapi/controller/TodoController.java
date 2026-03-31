package com.rohit.todoapi.controller;

import com.rohit.todoapi.entity.Todo;
import com.rohit.todoapi.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/todos")
public class TodoController {

        private final TodoService todoService;

        public TodoController(TodoService todoService) {
                this.todoService = todoService;
        }

        // POST /todos
        @PostMapping
        public ResponseEntity<Todo> createTodo(@RequestBody Todo todo) {
                return ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body(todoService.createTodo(todo));
        }

        // GET /todos
        @GetMapping
        public ResponseEntity<List<Todo>> getAllTodos() {
                return ResponseEntity.ok(todoService.getAllTodos());
        }

        // GET /todos/{id}
        @GetMapping("/{id}")
        public ResponseEntity<Todo> getTodoById(@PathVariable Long id) {
                return ResponseEntity.ok(todoService.getTodoById(id));
        }

        // PUT /todos/{id}
        @PutMapping("/{id}")
        public ResponseEntity<Todo> updateTodo(@PathVariable Long id, @RequestBody Todo todo) {
                return ResponseEntity.ok(todoService.updateTodo(id, todo));
        }

        // DELETE /todos/{id}
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
                todoService.deleteTodo(id);
                return ResponseEntity.noContent().build();
        }
}
