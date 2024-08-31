package com.example.kanban.repository;

import com.example.kanban.enitity.Epic;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;



@Repository
public interface EpicRepository extends JpaRepository<Epic, Integer> {


}
