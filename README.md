# Task Tracker Service

### Цифровая академия T1

### Открытая школа разработчиков JAVA

Сервис представляет собой простую систему управления задачами с использованием Spring Boot и Spring Data JPA.
Система может создавать, просматривать, редактировать и удалять задачи.

### Стек технологий:

+ Java 17;
+ Maven;
+ Spring boot 3;
+ Spring Data;
+ Spring Web;
+ Liquibase
+ Lombok;
+ Postgres. 

### Запуск приложения:
<p>1. Клонируем проект "git clone https://github.com/boris86msk/openschool_task_tracker"</p>
<p>2. Создаем базу данных task_tracker (PostgreSQL)</p>
<p>3. Для сборки проекта в корне проекта выполняем команду "mvn install".</p>
<p>4. Запуск приложения: "java -jar target/openschool_task_tracer-0.0.1-SNAPSHOT.jar". По умолчанию риложение
доступно на http://localhost:8081/</p>

### API
Обект задачи (Task) принимается на сервер и возвращается в формате JSON, пример:<br />
{<br />
"id": 3,<br />
"title": "someTitle",<br />
"description": "someDescription",<br />
"dueDate": "2024-04-14T23:59:59.016002",<br />
"completed": false<br />
}<br />
Для создания достаточно (и необходимо) передать 3 поля: title, description, dueDate.
id автоматически генерируется системой, "новая" задача имеет статус completed=false, выполненная true.

+ GET /tasks -  Получить список всех задач.
+ GET /tasks/{id} - Получить информацию о задаче по её id.
+ POST /tasks - Создать новую задачу. Возвращает задачу с присвоенным id.
+ PUT /tasks/{id} - Обновить информацию о задаче (отметить как выполненую).
+ DELETE /tasks/{id} - Удалить задачу.