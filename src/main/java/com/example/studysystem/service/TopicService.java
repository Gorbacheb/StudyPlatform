package com.example.studysystem.service;

import com.example.studysystem.mapper.TopicMapper;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import com.example.studysystem.dto.TopicDto;
import com.example.studysystem.entity.Topic;
import com.example.studysystem.repository.TopicRepository;

@Stateless
public class TopicService {

    @Inject
    private TopicRepository topicRepository;

    @Inject
    private TopicMapper topicMapper;

    public Optional<TopicDto> findById(Long id) {
        return topicRepository.findById(id)
                .map(topicMapper::toDto);
    }

    public List<TopicDto> findAll() {
        List<Topic> topics = topicRepository.findAll();
        topics.forEach(topicMapper::toDto);
        return topicRepository.findAll().stream()
                .map(topicMapper::toDto)
                .collect(Collectors.toList());
    }

    public TopicDto create(TopicDto topicDto) {
        Topic topic = topicMapper.toEntity(topicDto);
        topic.setCreatedDate(LocalDateTime.now());

        Topic savedTopic = topicRepository.save(topic);
        return topicMapper.toDto(savedTopic);
    }

    public TopicDto update(Long id, TopicDto topicDto) {
        return topicRepository.findById(id)
                .map(existingTopic -> {
                    existingTopic.setTitle(topicDto.getTitle());
                    existingTopic.setDescription(topicDto.getDescription());
                    existingTopic.setEstimatedHours(topicDto.getEstimatedHours());

                    Topic updatedTopic = topicRepository.save(existingTopic);
                    return topicMapper.toDto(updatedTopic);
                })
                .orElseThrow(() -> new IllegalArgumentException("Topic not found with id: " + id));
    }

    public void delete(Long id) {
        topicRepository.deleteById(id);
    }
}