package com.example.kanban.service;

import com.example.kanban.dto.task.UnderTaskDto;

public interface UnderTaskService {
    void create(UnderTaskDto dto);
    void getById(Integer id);
    void delete(Integer id);
    void update(UnderTaskDto dto);
}
