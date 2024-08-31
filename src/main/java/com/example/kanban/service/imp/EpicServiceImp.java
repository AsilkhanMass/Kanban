package com.example.kanban.service.imp;

import com.example.kanban.dto.task.EpicDto;
import com.example.kanban.enitity.Epic;
import com.example.kanban.enitity.UnderTask;
import com.example.kanban.exception.EpicNotFoundException;
import com.example.kanban.exception.UserNotFoundException;
import com.example.kanban.repository.EpicRepository;
import com.example.kanban.repository.UnderTaskRepository;
import com.example.kanban.repository.UserEntityRepository;
import com.example.kanban.service.EpicService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EpicServiceImp implements EpicService {
    private final UserEntityRepository userEntityRepository;
    private final EpicRepository epicRepository;
    private final UnderTaskRepository underTaskRepository;

    public EpicServiceImp(UserEntityRepository userEntityRepository, EpicRepository epicRepository, UnderTaskRepository underTaskRepository) {
        this.userEntityRepository = userEntityRepository;
        this.epicRepository = epicRepository;
        this.underTaskRepository = underTaskRepository;
    }

    @Override
    public void create(EpicDto dto) {
        Epic epic = Epic.builder()
                .createdBy(userEntityRepository.findById(dto.createdBy())
                        .orElseThrow(
                                () -> new UserNotFoundException("You must fill field 'Created by'!")
                        ))
                .build();
        epicRepository.save(epic);

    }

    @Override
    public EpicDto getById(Integer id) {
        Epic epic = epicRepository.findById(id)
                .orElseThrow(
                        () -> new EpicNotFoundException("No epic with id: " + id)
                );
        List<UnderTask> underTaskList = underTaskRepository.findByEpic(epic);
        List<Integer> ids = underTaskList.stream().map(UnderTask::getId).toList();
        return new EpicDto(
                epic.getCreatedBy().getId(),
                ids
        );
    }

    @Override
    public void delete(Integer id) {
        epicRepository.deleteById(id);

    }

}
