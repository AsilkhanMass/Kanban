package com.example.kanban.repository;

import com.example.kanban.enitity.Epic;
import com.example.kanban.enitity.UnderTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UnderTaskRepository extends JpaRepository<UnderTask, Integer> {
    List<UnderTask> findByEpic(Epic epic);
    Optional<UnderTask> findByNameAndEpic(String name, Epic epic);
}
