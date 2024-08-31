package com.example.kanban.dto.task;

import java.time.LocalDateTime;

public record UnderTaskDto(
        String name,
        String status,
        String priority,
        Integer assignee,
        LocalDateTime deadLine,
        Integer epicId
) {
}
