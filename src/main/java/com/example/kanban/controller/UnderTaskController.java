package com.example.kanban.controller;

import com.example.kanban.dto.task.UnderTaskDto;
import com.example.kanban.exception.EpicNotFoundException;
import com.example.kanban.exception.TaskNotFoundException;
import com.example.kanban.exception.UserNotFoundException;
import com.example.kanban.service.imp.UnderTaskServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks/epic/underTask")
public class UnderTaskController {
    private final UnderTaskServiceImp underTaskServiceImp;

    public UnderTaskController(UnderTaskServiceImp underTaskServiceImp) {
        this.underTaskServiceImp = underTaskServiceImp;
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody UnderTaskDto dto){
        try{
            underTaskServiceImp.create(dto);
            return new ResponseEntity<>("Created!", HttpStatus.CREATED);
        }catch(UserNotFoundException e){
            return new ResponseEntity<>("Authorize first!", HttpStatus.BAD_REQUEST);
        }catch(EpicNotFoundException e){
            return new ResponseEntity<>("Indicate the epic task!", HttpStatus.OK);
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<UnderTaskDto> getUnderTaskById(@PathVariable Integer id){
        try{
            return new ResponseEntity<>(underTaskServiceImp.getById(id), HttpStatus.OK);
        }catch(TaskNotFoundException e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateUnderTask(UnderTaskDto dto){
        try{
            underTaskServiceImp.update(dto);
            return new ResponseEntity<>("Updated!", HttpStatus.OK);
        }catch(EpicNotFoundException | TaskNotFoundException t){
            return new ResponseEntity<>("Epic not indicated or task not found!", HttpStatus.CONFLICT);
        }
    }
}
