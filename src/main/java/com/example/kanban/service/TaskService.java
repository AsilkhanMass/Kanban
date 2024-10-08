package com.example.kanban.service;

import com.example.kanban.dto.task.TaskDto;

public interface TaskService {
    void create(TaskDto dto);
    TaskDto read(Integer id);
    void delete(Integer id);
    void update(TaskDto dto);
}
