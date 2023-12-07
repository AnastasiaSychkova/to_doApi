package com.example.to_do_api.controller;

import com.example.to_do_api.dto.CreateTaskDto;
import com.example.to_do_api.dto.UpdateTaskDto;
import com.example.to_do_api.entity.Task;
import com.example.to_do_api.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> addTask(@ModelAttribute CreateTaskDto createOrUpdateTaskDto) throws IOException {

        return new ResponseEntity<>(taskService.addTask(createOrUpdateTaskDto, SecurityContextHolder.getContext().getAuthentication().getName(), createOrUpdateTaskDto.getFile()), HttpStatus.CREATED);
    }

    @GetMapping("/getByName")
    public ResponseEntity<List<Task>> getTaskByName(@RequestParam String taskName) {
        List<Task> tasks = taskService.getTask(taskName);
        if (tasks != null && !tasks.isEmpty()) {
            return ResponseEntity.ok(tasks);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    @GetMapping("/getById")
    public ResponseEntity<?> getTaskById(@RequestParam Long id) {
        try{
            return ResponseEntity.ok(taskService.getTask(id));
        } catch (IllegalAccessException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Task>> getAllTask() {
            return ResponseEntity.ok(taskService.getAll());
    }

    @PostMapping(value = "/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateTask(@ModelAttribute UpdateTaskDto updateTaskDto) throws IOException {
        try {
            return ResponseEntity.ok(taskService.updateTask(updateTaskDto));
        } catch (IllegalAccessException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> deleteTask(@RequestParam Long id) {
        return ResponseEntity.ok(taskService.deleteTask(id));
    }


}
