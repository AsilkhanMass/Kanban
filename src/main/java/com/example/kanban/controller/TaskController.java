package com.example.kanban.controller;

import com.example.kanban.dto.task.TaskDto;
import com.example.kanban.exception.TaskNotFoundException;
import com.example.kanban.service.imp.TaskServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskServiceImp taskServiceImp;

    public TaskController(TaskServiceImp taskServiceImp) {
        this.taskServiceImp = taskServiceImp;
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody TaskDto dto){
        try{
            taskServiceImp.create(dto);
            return new ResponseEntity<>("Task created!", HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>("Something went wrong!", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<TaskDto> getById(@PathVariable Integer id){
        try{
            return new ResponseEntity<>(taskServiceImp.read(id), HttpStatus.OK);
        }catch(TaskNotFoundException e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id){
        try{
            taskServiceImp.delete(id);
            return new ResponseEntity<>("Deleted!", HttpStatus.OK);
        }catch(TaskNotFoundException e){
            return new ResponseEntity<>("No such task already!", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> update(@RequestBody TaskDto dto){
        try{
            taskServiceImp.update(dto);
            return new ResponseEntity<>("Updated!", HttpStatus.OK);
        }catch(TaskNotFoundException  e){
            return new ResponseEntity<>("Task not found", HttpStatus.BAD_REQUEST);
        }
    }


}
