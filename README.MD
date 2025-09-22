# Лабороторная работа №1
Выполнили: Жукова Анна, Недугов Владимр 6131-010402D
# Предметная область
![img_5.png](img_5.png)
# Используемые технологии
- Jakarta Servlet 5.0
- Jakarta Pages (JSP) 
- Jakarta Persistence
- Jakarta Contexts and Dependency Injection
- Jakarta Enterprise Beans
- JPA/Hibernate
- PostgreSQL
- Bootstrap
- WildFly
# Настройка
Сервер: WildFly 
Что нужно чтобы запустить сервер
1) Скачать postgresql.jar в директорию standalone\deployments
2) Перейти в директорию "wildfly-37.0.1.Final\bin\" выполнить скрипт 
`./add-user.sh -m -u admin -p admin`
3) Перейти в консоль администратора `http://localhost:9990/console/`
4) Создать dataSource 

4.1 ![img_1.png](img_1.png)
4.2 ![img_2.png](img_2.png)
4.3 ![img_3.png](img_3.png)
4.4 ![img_4.png](img_4.png)

Выполнить деплой war файла приложения

