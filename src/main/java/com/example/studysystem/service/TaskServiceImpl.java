package com.example.studysystem.service;

import com.example.studysystem.dto.TaskDto;
import com.example.studysystem.entity.Task;
import com.example.studysystem.mapper.TaskMapper;
import com.example.studysystem.repository.TaskRepository;
import com.example.studysystem.repository.TopicRepository;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Stateless
public class TaskServiceImpl implements TaskService {

    @Inject
    private TaskRepository taskRepository;

    @Inject
    private TopicRepository topicRepository;

    @Inject
    private TaskMapper taskMapper;

    @Override
    public Optional<TaskDto> findById(Long id) {
        return taskRepository.findById(id)
                .map(taskMapper::toDto);
    }

    @Override
    public List<TaskDto> findAll() {
        return taskRepository.findAll().stream()
                .map(taskMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskDto> findByTopicId(Long topicId) {
        return taskRepository.findByTopicId(topicId).stream()
                .map(taskMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public TaskDto create(TaskDto taskDto) {
        Task task = taskMapper.toEntity(taskDto);

        if (taskDto.getTopicId() != null) {
            topicRepository.findById(taskDto.getTopicId())
                    .ifPresent(task::setTopic);
        }

        Task savedTask = taskRepository.save(task);
        return taskMapper.toDto(savedTask);
    }

    @Override
    public TaskDto update(Long id, TaskDto taskDto) {
        return taskRepository.findById(id)
                .map(existingTask -> {
                    existingTask.setTitle(taskDto.getTitle());
                    existingTask.setDescription(taskDto.getDescription());
                    existingTask.setStatus(taskDto.getStatus());

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

    @Override
    public void delete(Long id) {
        taskRepository.deleteById(id);
    }
}