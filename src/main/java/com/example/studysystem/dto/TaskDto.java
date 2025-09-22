package com.example.studysystem.dto;

import com.example.studysystem.entity.TaskStatus;

import java.time.LocalDateTime;

public class TaskDto {
    private Long id;
    private String title;
    private String description;
    private TaskStatus status;
    private Long topicId;
    private String topicTitle;

    public TaskDto() {
    }

    public TaskDto(Long id, String title, String description, TaskStatus status,
                   LocalDateTime deadline, LocalDateTime createdDate,
                   Long topicId, String topicTitle) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.topicId = topicId;
        this.topicTitle = topicTitle;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public TaskStatus getStatus() { return status; }
    public void setStatus(TaskStatus status) { this.status = status; }

    public Long getTopic() { return topicId; }
    public void setTopic(Long topic) { this.topicId = topic; }

    public void setTopicId(Long id) {
        this.topicId = id;
    }

    public String getTopicTitle() { return topicTitle; }
    public void setTopicTitle(String title) {
        this.topicTitle = title;
    }

    public Long getTopicId() {
        return topicId;
    }
}
