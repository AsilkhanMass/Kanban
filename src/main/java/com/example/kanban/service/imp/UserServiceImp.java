package com.example.kanban.service.imp;

import com.example.kanban.dto.user.LoginDto;
import com.example.kanban.dto.user.RegisterDto;
import com.example.kanban.dto.user.UserDto;
import com.example.kanban.dto.web.JwtResponse;
import com.example.kanban.repository.UserEntityRepository;
import com.example.kanban.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {

    private final UserEntityRepository userEntityRepository;


    @Override
    public JwtResponse login(LoginDto dto) {
        return null;
    }

    @Override
    public void register(RegisterDto dto) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public void update(UserDto dto) {

    }
}
