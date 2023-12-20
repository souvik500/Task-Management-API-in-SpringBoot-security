package com.example.SpringBootExample1.repository;

import com.example.SpringBootExample1.Enum.TaskStatus;
import com.example.SpringBootExample1.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface TaskRepository extends JpaRepository<Task, Integer> {

    List<Task> findByUserId(int userId);
    List<Task> findAllTaskByStatus(TaskStatus taskStatus);
}