package com.example.kanban.service.imp;


import com.example.kanban.dto.task.UnderTaskDto;
import com.example.kanban.enitity.Epic;
import com.example.kanban.enitity.UnderTask;
import com.example.kanban.enitity.UserEntity;
import com.example.kanban.exception.EpicNotFoundException;
import com.example.kanban.exception.TaskNotFoundException;
import com.example.kanban.exception.UserNotFoundException;
import com.example.kanban.repository.EpicRepository;
import com.example.kanban.repository.UnderTaskRepository;
import com.example.kanban.repository.UserEntityRepository;
import com.example.kanban.service.UnderTaskService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UnderTaskServiceImp implements UnderTaskService {
    private final UnderTaskRepository underTaskRepository;
    private final UserEntityRepository userEntityRepository;
    private final EpicRepository epicRepository;

    public UnderTaskServiceImp(UnderTaskRepository underTaskRepository, UserEntityRepository userEntityRepository, EpicRepository epicRepository) {
        this.underTaskRepository = underTaskRepository;
        this.userEntityRepository = userEntityRepository;
        this.epicRepository = epicRepository;
    }

    @Override
    public void create(UnderTaskDto dto) {
        UserEntity user = userEntityRepository.findById(dto.assignee())
                .orElseThrow(
                        () -> new UserNotFoundException("No user")
                );
        UnderTask underTask = (UnderTask) UnderTask.builder()
                .name(dto.name())
                .assignee(user)
                .priority("Medium")
                .status("NEW")
                .deadLine(dto.deadLine())
                .build();

        Epic epic = epicRepository.findById(dto.epicId())
                .orElseThrow(
                        () -> new EpicNotFoundException("No epic found with id: " + dto.epicId())
                );

        underTask.setEpic(epic);

        underTaskRepository.save(underTask);

    }

    @Override
    public UnderTaskDto getById(Integer id) {
        UnderTask underTask =  underTaskRepository.findById(id)
                .orElseThrow(
                        () -> new TaskNotFoundException("Task not found wiht id: " + id)
                );
        return new UnderTaskDto(
                underTask.getName(),
                underTask.getStatus(),
                underTask.getPriority(),
                underTask.getAssignee().getId(),
                underTask.getDeadLine(),
                underTask.getEpic().getId()
        );
    }

    @Override
    public List<UnderTaskDto> getByEpicId(Integer id) {
        Epic epic = epicRepository.findById(id)
                .orElseThrow(
                        () -> new EpicNotFoundException("No epic with id: " + id)
                );
        List<UnderTask> underTasks = underTaskRepository.findByEpic(epic);
        return underTasks.stream().map(this::toDto).collect(Collectors.toList());


    }

    public UnderTaskDto toDto(UnderTask underTask) {
        return new UnderTaskDto(
                underTask.getName(),
                underTask.getStatus(),
                underTask.getPriority(),
                underTask.getAssignee().getId(),
                underTask.getDeadLine(),
                underTask.getEpic().getId()
        );
    }

    @Override
    public void delete(Integer id) {
        underTaskRepository.deleteById(id);

    }

    @Override
    public void update(UnderTaskDto dto) {
        Epic epic = epicRepository.findById(dto.epicId())
                .orElseThrow(
                        () -> new EpicNotFoundException("No epic with id: " + dto.epicId())
                );
        UnderTask underTask = underTaskRepository.findByNameAndEpic(dto.name(), epic)
                .orElseThrow(
                        () -> new TaskNotFoundException("No under task with name: " + dto.name())
                );
        if(dto.priority()!=null){
            underTask.setPriority(dto.priority());
        }if(dto.status()!=null){
            underTask.setStatus(dto.status());
        }if(dto.deadLine()!=null){
            underTask.setDeadLine(dto.deadLine());
        }
        underTaskRepository.save(underTask);

    }
}
