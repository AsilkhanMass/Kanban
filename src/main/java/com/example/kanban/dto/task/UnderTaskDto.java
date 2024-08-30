package com.example.kanban.dto.task;

import java.time.LocalDateTime;

public record UnderTaskDto(
        String name,
        String status,
        String priority,
        Integer userId,

        Integer assignee,
        LocalDateTime deadLine,
        Integer epicId
) {
}
