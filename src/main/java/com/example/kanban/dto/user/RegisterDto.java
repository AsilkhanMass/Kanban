package com.example.kanban.dto.user;

public record RegisterDto(
    String name,
    String phoneNumber,
    String password,
    String email
) {
}
