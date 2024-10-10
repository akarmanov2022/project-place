# Project Place

Cервис для ведения отчётности трекеров в ходе реализационных программ ТУСУР.

## Стек технологий

- Java 21
- Spring Boot 3.3.4
- PostgreSQL 13

## Запуск проекта

### С использованием Docker

1. Убедитесь, что у вас установлен Docker.
2. Выполните команду для запуска контейнеров:
    ```shell
    docker-compose up
    ```

### Локально

1. Убедитесь, что у вас установлен PostgreSQL и настроена база данных:
    ```sql
    CREATE DATABASE project_place;
    CREATE USER project_place WITH PASSWORD 'project-place';
    GRANT ALL PRIVILEGES ON DATABASE project_place TO project_place;
    ```
2. Настройте переменные окружения в `application.yaml` или используйте значения по умолчанию.
3. Запустите приложение:
    ```shell
    ./gradlew bootRun
    ```