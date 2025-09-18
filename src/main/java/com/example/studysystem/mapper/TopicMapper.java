package com.example.studysystem.mapper;

import com.example.studysystem.dto.TopicDto;
import com.example.studysystem.entity.Topic;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.stream.Collectors;

@ApplicationScoped
public class TopicMapper {

    @Inject
    private TaskMapper taskMapper;

    public TopicDto toDto(Topic topic) {
        if (topic == null) {
            return null;
        }

        TopicDto dto = new TopicDto();
        dto.setId(topic.getId());
        dto.setTitle(topic.getTitle());
        dto.setDescription(topic.getDescription());
        dto.setEstimatedHours(topic.getEstimatedHours());
        dto.setCreatedDate(topic.getCreatedDate());

        // Маппим задачи, если они загружены
        if (topic.getTasks() != null && !topic.getTasks().isEmpty()) {
            dto.setTasks(topic.getTasks().stream()
                    .map(taskMapper::toDto)
                    .collect(Collectors.toList()));
        }

        return dto;
    }

    public Topic toEntity(TopicDto dto) {
        if (dto == null) {
            return null;
        }

        Topic topic = new Topic();
        topic.setId(dto.getId());
        topic.setTitle(dto.getTitle());
        topic.setDescription(dto.getDescription());
        topic.setEstimatedHours(dto.getEstimatedHours());
        topic.setCreatedDate(dto.getCreatedDate());

        return topic;
    }
}