package com.example.kanban.service;

import com.example.kanban.dto.task.EpicDto;

public interface EpicService {
    void create(EpicDto dto);
    EpicDto getById(Integer id);
    void delete(Integer id);

}
