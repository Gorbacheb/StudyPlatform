package com.example.studysystem.repository;

import com.example.studysystem.entity.Task;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {
    Optional<Task> findById(Long id);
    List<Task> findAll();
    List<Task> findByTopicId(Long topicId);
    Task save(Task task);
    void delete(Task task);
    void deleteById(Long id);
}
