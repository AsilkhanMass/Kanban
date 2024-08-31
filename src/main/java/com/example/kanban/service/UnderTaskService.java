package com.example.kanban.service;

import com.example.kanban.dto.task.UnderTaskDto;

import java.util.List;

public interface UnderTaskService {
    void create(UnderTaskDto dto);
    UnderTaskDto getById(Integer id);
    List<UnderTaskDto> getByEpicId(Integer id);
    void delete(Integer id);
    void update(UnderTaskDto dto);
}
