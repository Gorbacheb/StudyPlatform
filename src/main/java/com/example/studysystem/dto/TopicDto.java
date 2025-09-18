package com.example.studysystem.dto;

import java.time.LocalDateTime;
import java.util.List;

public class TopicDto {
    private Long id;
    private String title;
    private String description;
    private Integer estimatedHours;
    private LocalDateTime createdDate;
    private List<TaskDto> tasks;

    public TopicDto() {
    }

    public TopicDto(Long id, String title, String description,
                    Integer estimatedHours, LocalDateTime createdDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.estimatedHours = estimatedHours;
        this.createdDate = createdDate;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Integer getEstimatedHours() { return estimatedHours; }
    public void setEstimatedHours(Integer estimatedHours) { this.estimatedHours = estimatedHours; }

    public LocalDateTime getCreatedDate() { return createdDate; }
    public void setCreatedDate(LocalDateTime createdDate) { this.createdDate = createdDate; }

    public List<TaskDto> getTasks() { return tasks; }
    public void setTasks(List<TaskDto> tasks) { this.tasks = tasks; }

    public void addTask(TaskDto task) {
        tasks.add(task);
        task.setTopic(this.id);
    }

    public void removeTask(TaskDto task) {
        tasks.remove(task);
        task.setTopic(null);
    }
}