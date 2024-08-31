package com.example.kanban.service.imp;

import com.example.kanban.dto.task.TaskDto;
import com.example.kanban.enitity.Task;
import com.example.kanban.enitity.UserEntity;
import com.example.kanban.exception.TaskNotFoundException;
import com.example.kanban.exception.UserNotFoundException;
import com.example.kanban.repository.TaskRepository;
import com.example.kanban.repository.UserEntityRepository;
import com.example.kanban.service.TaskService;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImp implements TaskService {
    private final TaskRepository taskRepository;
    private final UserEntityRepository userEntityRepository;

    public TaskServiceImp(TaskRepository taskRepository, UserEntityRepository userEntityRepository) {
        this.taskRepository = taskRepository;
        this.userEntityRepository = userEntityRepository;
    }

    @Override
    public void create(TaskDto dto) {
        Task task = Task.builder()
                .name(dto.name())
                .deadLine(dto.deadLine())
                .priority(dto.priority()==null?"Medium": dto.priority())
                .status("NEW")
                .build();
        UserEntity createdBy = userEntityRepository.findById(dto.userId()).get();
        UserEntity assignee = userEntityRepository.findById(dto.assignee())
                .orElse(null);
        task.setAssignee(assignee);
        task.setCreatedBy(createdBy);
    }

    @Override
    public TaskDto read(Integer id) {
        Task task = taskRepository.findById(id).get();
        return new TaskDto(
                task.getName(),
                task.getStatus(),
                task.getPriority(),
                task.getCreatedBy().getId(),
                task.getAssignee().getId(),
                task.getDeadLine()
        );
    }

    @Override
    public void delete(Integer id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(
                        () -> new TaskNotFoundException("No task exists!")
                );
        taskRepository.delete(task);

    }

    @Override
    public void update(TaskDto dto) {
        UserEntity user = userEntityRepository.findById(dto.userId())
                .orElseThrow(
                        () -> new UserNotFoundException("No user found!")
                );
        Task task = taskRepository.findByNameAndCreatedBy(dto.name(), user).get();
        if(dto.name()!=null){
            task.setName(dto.name());
        }
        if(dto.deadLine()!=null){
            task.setDeadLine(dto.deadLine());
        }if(dto.status()!=null){
            task.setStatus(dto.status());
        }if(dto.priority()!=null){
            task.setPriority(dto.priority());
        }if(dto.assignee()!=null){
            task.setAssignee(userEntityRepository.findById(dto.assignee()).orElse(null));
        }
        taskRepository.save(task);

    }
}
