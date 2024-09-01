package com.example.kanban.controller;

import com.example.kanban.dto.task.EpicDto;
import com.example.kanban.exception.EpicNotFoundException;
import com.example.kanban.exception.UserNotFoundException;
import com.example.kanban.service.imp.EpicServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks/epic")
public class EpicController {

    private final EpicServiceImp epicServiceIml;

    public EpicController(EpicServiceImp epicServiceIml) {
        this.epicServiceIml = epicServiceIml;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createEpic(@RequestBody EpicDto dto) {
        try {
            epicServiceIml.create(dto);
            return new ResponseEntity<>("Epic created!", HttpStatus.CREATED);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>("Authorize first!", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Something went wrong!", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<EpicDto> getEpic(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(epicServiceIml.getById(id), HttpStatus.OK);
        } catch (EpicNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEpicById(@PathVariable Integer id) {
        try {
            epicServiceIml.delete(id);
            return new ResponseEntity<>("Deleted!", HttpStatus.OK);
        }catch(RuntimeException e){
            return new ResponseEntity<>("Something went wrong!", HttpStatus.BAD_REQUEST);
        }
    }

}
