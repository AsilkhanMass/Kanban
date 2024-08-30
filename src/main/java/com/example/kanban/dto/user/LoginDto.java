package com.example.kanban.dto.user;

public record LoginDto(
    String name,
    String phoneNumber,
    String password,
    String email
) {
}
