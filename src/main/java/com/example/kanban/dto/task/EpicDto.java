package com.example.kanban.dto.task;

import java.util.List;

public record EpicDto(
        Integer createdBy,
        List<Integer> underTaskIds
) {
}
