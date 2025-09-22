package com.example.studysystem.service;

import com.example.studysystem.dto.TaskDto;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    Optional<TaskDto> findById(Long id);

    List<TaskDto> findAll();

    List<TaskDto> findByTopicId(Long topicId);

    TaskDto create(TaskDto taskDto);

    TaskDto update(Long id, TaskDto taskDto);

    void delete(Long id);
}
