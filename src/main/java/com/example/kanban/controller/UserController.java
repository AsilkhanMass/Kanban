package com.example.kanban.controller;

import com.example.kanban.dto.user.LoginDto;
import com.example.kanban.dto.user.RegisterDto;
import com.example.kanban.dto.user.UserDto;
import com.example.kanban.dto.web.JwtResponse;
import com.example.kanban.exception.UserAlreadyExistsException;
import com.example.kanban.service.imp.UserServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserServiceImp userServiceImp;

    public UserController(UserServiceImp userServiceImp) {
        this.userServiceImp = userServiceImp;
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginDto dto){
        return ResponseEntity.ok(userServiceImp.login(dto));
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto dto){
        try{
            userServiceImp.register(dto);
            return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
        }catch (UserAlreadyExistsException e) {
            return new ResponseEntity<>("User already exists!", HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>("Registration failed. Please try again.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN')")
    public ResponseEntity<String> delete(@PathVariable Integer id){
        try{
            userServiceImp.delete(id);
            return new ResponseEntity<>("User removed successfully", HttpStatus.OK);
        }catch(RuntimeException e){
            return new ResponseEntity<>("Something went wrong!", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/update")
    public ResponseEntity<String> update(@RequestBody UserDto dto){
        try{
            userServiceImp.update(dto);
            return new ResponseEntity<>("Updated successfully!", HttpStatus.OK);
        }catch (RuntimeException e){
            return new ResponseEntity<>("Something went wrong!", HttpStatus.BAD_REQUEST);
        }
    }


}
