CREATE DATABASE study_platform;

CREATE TABLE topic (
id SERIAL PRIMARY KEY,
title VARCHAR(200) NOT NULL,
description TEXT,
estimated_hours INTEGER);

CREATE TABLE task (
id SERIAL PRIMARY KEY,
title VARCHAR(200) NOT NULL,
description TEXT,
status VARCHAR(20) CHECK (status IN ('NOT_STARTED', 'IN_PROGRESS', 'COMPLETED')) DEFAULT 'NOT_STARTED',
topic_id INTEGER REFERENCES topic(id) ON DELETE CASCADE
);

INSERT INTO topic (title, description, estimated_hours) VALUES
('Java Basics', 'Fundamentals of Java programming', 20),
('Spring Framework', 'Learning Spring Framework ecosystem', 40),
('Microservices Architecture', 'Designing microservices-based systems', 60),
('Database Design', 'Relational databases and SQL optimization', 35),
('DevOps Fundamentals', 'CI/CD pipelines and containerization', 45),
('REST API Development', 'Building RESTful web services', 30);

INSERT INTO task (title, description, status, topic_id) VALUES
('Install JDK', 'Install Java Development Kit', 'COMPLETED', 1),
('Learn Syntax', 'Study basic Java syntax', 'IN_PROGRESS', 1),
('Create First Application', 'Build a console application', 'NOT_STARTED', 1),
('OOP Principles', 'Master inheritance and polymorphism', 'NOT_STARTED', 1),

('Spring Core', 'Understand dependency injection', 'NOT_STARTED', 2),
('Spring Boot', 'Create REST controller', 'NOT_STARTED', 2),
('Data JPA', 'Configure database connectivity', 'NOT_STARTED', 2),
('Security', 'Implement authentication', 'NOT_STARTED', 2),

('Domain Analysis', 'Define service boundaries', 'NOT_STARTED', 3),
('Docker Setup', 'Containerize services', 'NOT_STARTED', 3),
('API Gateway', 'Implement routing logic', 'NOT_STARTED', 3),

('Normalization', 'Apply 3rd normal form', 'NOT_STARTED', 4),
('Indexing', 'Optimize query performance', 'NOT_STARTED', 4),
('Transactions', 'Implement ACID operations', 'NOT_STARTED', 4),

('Jenkins Setup', 'Configure CI pipeline', 'NOT_STARTED', 5),
('Kubernetes', 'Deploy container orchestration', 'NOT_STARTED', 5),
('Monitoring', 'Set up logging system', 'NOT_STARTED', 5);