package com.example.studysystem.service;

import com.example.studysystem.dto.TopicDto;

import java.util.List;
import java.util.Optional;

public interface TopicService {
    Optional<TopicDto> findById(Long id);

    List<TopicDto> findAll();

    TopicDto create(TopicDto topicDto);

    TopicDto update(Long id, TopicDto topicDto);

    void delete(Long id);
}
