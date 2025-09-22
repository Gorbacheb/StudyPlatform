package com.example.studysystem.repository;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import com.example.studysystem.entity.Topic;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Stateless
public class TopicRepositoryImpl implements TopicRepository {
    private static final Logger logger = Logger.getLogger(TopicRepositoryImpl.class.getName());
    @PersistenceContext(unitName = "studyPlatformDS")
    private EntityManager entityManager;

    @Override
    public Optional<Topic> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Topic.class, id));
    }

    @Override
    public List<Topic> findAll() {
        return entityManager.createQuery("SELECT t FROM Topic t ", Topic.class)
                .getResultList();
    }

    @Override
    public Topic save(Topic topic) {
        if (topic.getId() == null) {
            entityManager.persist(topic);
            return topic;
        } else {
            return entityManager.merge(topic);
        }
    }

    @Override
    public void delete(Topic topic) {
        entityManager.remove(entityManager.contains(topic) ? topic : entityManager.merge(topic));
    }

    @Override
    public void deleteById(Long id) {
        findById(id).ifPresent(this::delete);
    }
}