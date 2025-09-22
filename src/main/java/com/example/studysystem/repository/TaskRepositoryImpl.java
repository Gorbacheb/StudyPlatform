package com.example.studysystem.repository;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import com.example.studysystem.entity.Task;

import java.util.List;
import java.util.Optional;

@Stateless
public class TaskRepositoryImpl implements TaskRepository {

    @PersistenceContext()
    private EntityManager entityManager;

    @Override
    public Optional<Task> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Task.class, id));
    }

    @Override
    public List<Task> findAll() {
        return entityManager.createQuery("SELECT t FROM Task t LEFT JOIN FETCH t.topic", Task.class)
                .getResultList();
    }

    @Override
    public List<Task> findByTopicId(Long topicId) {
        var entities =  entityManager.createQuery(
                "SELECT t FROM Task t LEFT JOIN FETCH t.topic WHERE t.topic.id = :topicId", Task.class)
                .setParameter("topicId", topicId)
                .getResultList();
        return entities;
    }

    @Override
    public Task save(Task task) {
        if (task.getId() == null) {
            entityManager.persist(task);
            return task;
        } else {
            return entityManager.merge(task);
        }
    }

    @Override
    public void delete(Task task) {
        entityManager.remove(entityManager.contains(task) ? task : entityManager.merge(task));
    }

    @Override
    public void deleteById(Long id) {
        findById(id).ifPresent(this::delete);
    }
}