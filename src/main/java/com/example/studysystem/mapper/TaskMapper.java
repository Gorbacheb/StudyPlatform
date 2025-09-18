package com.example.studysystem.mapper;

import com.example.studysystem.dto.TaskDto;
import com.example.studysystem.entity.Task;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TaskMapper {

    public TaskDto toDto(Task task) {
        if (task == null) {
            return null;
        }

        TaskDto dto = new TaskDto();
        dto.setId(task.getId());
        dto.setTitle(task.getTitle());
        dto.setDescription(task.getDescription());
        dto.setStatus(task.getStatus());
        dto.setDeadline(task.getDeadline());
        dto.setCreatedDate(task.getCreatedDate());

        if (task.getTopic() != null) {
            dto.setTopicId(task.getTopic().getId());
            dto.setTopicTitle(task.getTopic().getTitle());
        }

        return dto;
    }

    public Task toEntity(TaskDto dto) {
        if (dto == null) {
            return null;
        }

        Task task = new Task();
        task.setId(dto.getId());
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setStatus(dto.getStatus());
        task.setDeadline(dto.getDeadline());
        task.setCreatedDate(dto.getCreatedDate());

        return task;
    }
}