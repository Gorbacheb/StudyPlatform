package com.example.studysystem.repository;

import com.example.studysystem.entity.Topic;

import java.util.List;
import java.util.Optional;

public interface TopicRepository {
    Optional<Topic> findById(Long id);
    List<Topic> findAll();
    Topic save(Topic topic);
    void delete(Topic topic);
    void deleteById(Long id);
}
