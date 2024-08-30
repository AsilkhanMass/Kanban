package com.example.kanban.service;

import com.example.kanban.dto.user.LoginDto;
import com.example.kanban.dto.user.RegisterDto;
import com.example.kanban.dto.user.UserDto;
import com.example.kanban.dto.web.JwtResponse;

public interface UserService {
    JwtResponse login(LoginDto dto);
    void register(RegisterDto dto);
    void delete(Integer id);
    void update(UserDto dto);
}
