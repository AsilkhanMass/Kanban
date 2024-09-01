package com.example.kanban.service.imp;

import com.example.kanban.dto.user.LoginDto;
import com.example.kanban.dto.user.RegisterDto;
import com.example.kanban.dto.user.UserDto;
import com.example.kanban.dto.web.JwtResponse;
import com.example.kanban.enitity.Role;
import com.example.kanban.enitity.UserEntity;
import com.example.kanban.exception.UserAlreadyExistsException;
import com.example.kanban.exception.UserNotFoundException;
import com.example.kanban.provider.JwtProvider;
import com.example.kanban.repository.RoleRepository;
import com.example.kanban.repository.UserEntityRepository;
import com.example.kanban.service.UserService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserServiceImp implements UserService {

    private final UserEntityRepository userEntityRepository;
    private final JwtProvider jwtProvider;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImp(UserEntityRepository userEntityRepository, JwtProvider jwtProvider, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userEntityRepository = userEntityRepository;
        this.jwtProvider = jwtProvider;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public JwtResponse login(LoginDto dto) {
        final UserEntity user = userEntityRepository.findByEmail(dto.email())
                .orElseThrow(
                        () -> new UserNotFoundException("User with email " + dto.email() + " not registered!")
                );
        if(passwordEncoder.matches(dto.password(), user.getPassword())){
            throw new BadCredentialsException("Incorrect password! \nTry again");
        }
        return new JwtResponse(jwtProvider.generateToken(user), jwtProvider.getExpiration());
    }

    @Override
    public void register(RegisterDto dto) {
        if(userEntityRepository.existsByEmail(dto.email())){
            throw new UserAlreadyExistsException("User already exists, email: " + dto.email());
        }
        UserEntity user = UserEntity.builder()
                .email(dto.email())
                .name(dto.username())
                .password(passwordEncoder.encode(dto.password()))
                .type("SIMPLE")
                .phoneNumber(dto.phoneNumber())
                .build();
        Role role = roleRepository.findByName("USER").get();
        user.setRoles(Collections.singletonList(role));
        userEntityRepository.save(user);

    }

    @Override
    public void delete(Integer id) {
        userEntityRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("Already no user with id: " + id)
        );
        userEntityRepository.deleteById(id);

    }

    @Override
    public void update(UserDto dto) {
        UserEntity user = userEntityRepository.findByEmail(dto.email())
                .orElseThrow(
                        () -> new UserNotFoundException("User not found with email: " + dto.email())
                );

        if(dto.name()!=null){
            user.setName(dto.name());
        }
        if(dto.phoneNumber()!=null){
            user.setPhoneNumber(dto.phoneNumber());
        }
        userEntityRepository.save(user);
    }
}
