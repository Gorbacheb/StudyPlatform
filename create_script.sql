CREATE DATABASE study_platform;

CREATE TABLE topic (id SERIAL PRIMARY KEY, title VARCHAR(200) NOT NULL, description TEXT,  estimated_hours INTEGER, created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP);

CREATE TABLE task (
                      id SERIAL PRIMARY KEY,
                      title VARCHAR(200) NOT NULL,
                      description TEXT,
                      status VARCHAR(20) CHECK (status IN ('NOT_STARTED', 'IN_PROGRESS', 'COMPLETED')) DEFAULT 'NOT_STARTED',
                      deadline TIMESTAMP,
                      created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                      topic_id INTEGER REFERENCES topic(id) ON DELETE CASCADE
);

INSERT INTO topic (title, description, estimated_hours, created_date) VALUES
('Java Basics', 'Основы программирования на Java', 20, '2025-09-17'),
('Spring Framework', 'Изучение Spring Framework',  40, '2025-09-17'),
('Microservices Architecture', 'Архитектура микросервисов', 60, '2025-09-17');

INSERT INTO tasks (title, description, status, deadline, topic_id) VALUES
                                                                       ('Установка JDK', 'Установить Java Development Kit', 'COMPLETED', '2024-10-15 23:59:59', 1),
                                                                       ('Изучение синтаксиса', 'Изучить базовый синтаксис Java', 'IN_PROGRESS', '2024-10-20 23:59:59', 1),
                                                                       ('Создание первого приложения', 'Создать консольное приложение',  'NOT_STARTED', '2024-10-25 23:59:59', 1),
                                                                       ('Изучение Spring Core', 'Освоить основы Spring Core',  'NOT_STARTED', '2024-11-10 23:59:59', 2);