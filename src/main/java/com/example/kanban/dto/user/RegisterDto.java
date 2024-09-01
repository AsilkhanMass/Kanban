package com.example.kanban.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterDto(
        @NotBlank String username,
        @NotBlank String phoneNumber,
        @NotBlank @Size(min = 6) String password,
        @NotBlank @Email String email
) {
}
