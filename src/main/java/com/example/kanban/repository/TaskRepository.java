package com.example.kanban.repository;

import com.example.kanban.enitity.Task;
import com.example.kanban.enitity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    Optional<Task> findByNameAndCreatedBy(String name, UserEntity user);
}
