# Проект с домашними заданиями курса Kotlin Backend Developer Otus

## Help
### gradle
- run gradle - `./gradlew clean build`
- run gradle subtask - `./gradlew clean :hello-world:test`

## Структура проекта
### Подпроекты для занятий по языку Kotlin

1. [hello-world](hello-world) - Вводное занятие, создание первой программы на Kotlin. Тут пробую различные функции Kotlin.
2. [coin-dsl](coin-dsl) - Предметно ориентированные языки (DSL). DSL создания монеты ЦБ РФ.
3. [vk-analyzer](vk-analyzer) - Анализ групп с монетами VK. Содержит скрипты для получения данных из групп с монетами. Данные потом используются в аналитике.
  - нужно включить subproject в `settings.gradle.kts` 
  - запуск скриптов - `./gradlew :vk-analyzer:test`
  - результаты выполнения скриптов - `vk-analyzer/csv-results`

### Аналитика
1. [info](info) - Различная аналитическая информация

## TODO
- поднять версию Kotlin до 1.9.0
- после описания модели монеты нужно новую модель использовать в dsl модуле
