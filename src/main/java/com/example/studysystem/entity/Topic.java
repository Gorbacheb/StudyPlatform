package com.example.studysystem.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "topic")
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(length = 1000)
    private String description;

    @Column(name = "estimated_hours")
    private Integer estimatedHours;

    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> tasks = new ArrayList<>();

    public Topic() {
    }

    public Topic(String title, String description, Integer estimatedHours) {
        this();
        this.title = title;
        this.description = description;
        this.estimatedHours = estimatedHours;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Integer getEstimatedHours() { return estimatedHours; }
    public void setEstimatedHours(Integer estimatedHours) { this.estimatedHours = estimatedHours; }

    public List<Task> getTasks() { return tasks; }
    public void setTasks(List<Task> tasks) { this.tasks = tasks; }

    public void addTask(Task task) {
        tasks.add(task);
        task.setTopic(this);
    }

    public void removeTask(Task task) {
        tasks.remove(task);
        task.setTopic(null);
    }
    @Override
    public String toString() {
        return "Topic{id=" + id + ", title='" + title + "}";
    }
}
