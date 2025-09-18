package com.example.studysystem.service;

import com.example.studysystem.dto.TaskDto;
import com.example.studysystem.entity.Task;
import com.example.studysystem.mapper.TaskMapper;
import com.example.studysystem.repository.TaskRepository;
import com.example.studysystem.repository.TopicRepository;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Stateless
public class TaskService{

    @Inject
    private TaskRepository taskRepository;

    @Inject
    private TopicRepository topicRepository;

    @Inject
    private TaskMapper taskMapper;

    public Optional<TaskDto> findById(Long id) {
        return taskRepository.findById(id)
                .map(taskMapper::toDto);
    }

    public List<TaskDto> findAll() {
        return taskRepository.findAll().stream()
                .map(taskMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<TaskDto> findByTopicId(Long topicId) {
        return taskRepository.findByTopicId(topicId).stream()
                .map(taskMapper::toDto)
                .collect(Collectors.toList());
    }

    public TaskDto create(TaskDto taskDto) {
        Task task = taskMapper.toEntity(taskDto);

        // Устанавливаем связь с Topic
        if (taskDto.getTopicId() != null) {
            topicRepository.findById(taskDto.getTopicId())
                    .ifPresent(task::setTopic);
        }

        // Устанавливаем дату создания
        task.setCreatedDate(LocalDateTime.now());

        // Сохраняем задачу
        Task savedTask = taskRepository.save(task);
        return taskMapper.toDto(savedTask);
    }

    public TaskDto update(Long id, TaskDto taskDto) {
        return taskRepository.findById(id)
                .map(existingTask -> {
                    // Обновляем поля
                    existingTask.setTitle(taskDto.getTitle());
                    existingTask.setDescription(taskDto.getDescription());
                    existingTask.setStatus(taskDto.getStatus());
                    existingTask.setDeadline(taskDto.getDeadline());

                    // Обновляем связь с Topic, если изменился topicId
                    if (taskDto.getTopicId() != null &&
                            (existingTask.getTopic() == null ||
                                    !existingTask.getTopic().getId().equals(taskDto.getTopicId()))) {
                        topicRepository.findById(taskDto.getTopicId())
                                .ifPresent(existingTask::setTopic);
                    }

                    Task updatedTask = taskRepository.save(existingTask);
                    return taskMapper.toDto(updatedTask);
                })
                .orElseThrow(() -> new IllegalArgumentException("Task not found with id: " + id));
    }

    public void delete(Long id) {
        taskRepository.deleteById(id);
    }
}