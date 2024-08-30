package com.example.kanban.dto.task;

import com.example.kanban.enitity.UserEntity;

import java.time.LocalDateTime;

public record TaskDto(
        String name,
        String status,
        String priority,
        Integer userId,

        Integer assignee,
        LocalDateTime deadLine) {
}
