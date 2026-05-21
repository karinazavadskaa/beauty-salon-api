# Beauty Salon API

## Описание
REST API для салона красоты с JWT авторизацией.

## Технологии
- Java 26
- Spring Boot 4.0.6
- Spring Security + JWT
- PostgreSQL
- Liquibase
- Gradle

## Запуск проекта

### Требования
- Java 26
- PostgreSQL 15+

### Настройка
1. Создайте базу данных:
```sql
CREATE DATABASE salonkr;

2. Настройте подключение в application.properties

Запуск
./gradlew bootRun

Тестовые пользователи
Email	Пароль	Роль
system.admin@beauty.ru	system.admin123	System Administrator
admin@beauty.ru	admin123	Salon Administrator
e.medvedeva@beauty.ru	e.medvedeva123	Cosmetologist

API Endpoints
Базовый URL: http://localhost:8081/api

Аутентификация
POST /auth/login - получение JWT токена

Клиенты (Clients)
GET /clients - получить всех

GET /clients/{id} - получить по ID

POST /clients - создать

PUT /clients/{id} - обновить

DELETE /clients/{id} - удалить

Сотрудники (Employees)
GET /employees - получить всех (только System Admin)

GET /employees/{id} - получить по ID

POST /employees - создать

PUT /employees/{id} - обновить

DELETE /employees/{id} - удалить

Процедуры (Procedures)
Полный CRUD доступен для System Admin и Salon Admin

Записи (Appointments)
Просмотр доступен всем авторизованным (Cosmetologist только свои)

## Пример запроса

### Получение токена
curl -X POST http://localhost:8081/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"email":"system.admin@beauty.ru","password":"system.admin123"}'

### Запрос с токеном
curl -X GET http://localhost:8081/api/clients \
  -H "Authorization: Bearer <ваш_токен>"
