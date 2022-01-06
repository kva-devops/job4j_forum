# Приложение Forum
[![Build Status](https://app.travis-ci.com/kva-devops/job4j_forum.svg?branch=master)](https://app.travis-ci.com/kva-devops/job4j_forum)
[![codecov](https://codecov.io/gh/kva-devops/job4j_forum/branch/master/graph/badge.svg?token=zVF7WjMA8q)](https://codecov.io/gh/kva-devops/job4j_forum)
[![alt-text](https://img.shields.io/badge/-heroku-283e4a?style=flat&logo=heroku&logoColor=white)](https://secret-peak-96289.herokuapp.com/)

## О проекте. 
Веб приложение, реализующее функционал закрытого интернет форума.
Позволяет создавать темы для обсуждения, и оставлять комментарии.
Темы и обсуждения могут просматривать только зарегистрированные пользователи. 

## Сборка
JDK14, Maven, Spring Boot, PostgreSQL

## Запуск через Docker Compose
1. Создать директорию на сервере и скопировать файлы репозитория
2. Перейти в созданную директорию (корень проекта) и собрать приложение командой: "mvn install"
3. Собирать docker-образ приложения командой: "docker build -t forum ."
4. При необходимости отредактировать порты, используемые приложением в файле docker-compose.yml
5. Запустить приложение командой: "docker-compose up"

## Как пользоваться
При старте приложения необходимо ввести логин и пароль или зарегистрироваться.
![loginStart](images/Selection_138.png)
![regStart](images/Selection_139.png)

После регистрации пользователь попадает на главную страницу со списком 
всех существующих тем для обсуждения. Для перехода в обсуждение необходимо кликнуть
по названию темы.
![mainPage](images/Selection_141.png)

В обсуждении можно узнать подробную информацию по теме, а также оставить свои комментарии.
![postAndComments](images/Selection_142.png)

Также пользователь может создавать собственные темы для обсуждения и редактировать их.
![mainNewTopicLink](images/Selection_143.png)
![createNewTopic](images/Selection_144.png)

## Контакты
Кутявин Владимир

skype: tribuna87

email: tribuna87@mail.ru

telegram: @kutiavinvladimir