/*!40101 SET @OLD_CHARACTER_SET_CLIENT = @@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS = @@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION = @@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0 */;
/*!40101 SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES = @@SQL_NOTES, SQL_NOTES = 0 */;

create table project
(
    `id`      int auto_increment primary key,
    `name`    varchar(45) null,
    `code`    varchar(45) null,
    `user_id` int         null,
    constraint Project_code_uindex
        unique (`code`),
    constraint fk_project_users
        foreign key (`user_id`) references users (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

create table task
(
    `id`          int auto_increment
        primary key,
    `description` varchar(255) null,
    `progress`    int          null,
    `status`      varchar(40)  null,
    `deadline`    date         null,
    `user_id`     int          null,
    `project_id`  int          null,
    constraint fk_task_project
        foreign key (`project_id`) references project (`id`),
    constraint fk_task_users
        foreign key (`user_id`) references users (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;


create table users
(
    `id`         int auto_increment
        primary key,
    `username`   varchar(100)              null,
    `email`      varchar(255)              not null,
    `first_name` varchar(250)              not null,
    `last_name`  varchar(255)              not null,
    `password`   varchar(255)              null,
    `role`       varchar(30) default 'DEV' not null,
    constraint users_email_uindex unique (email),
    constraint users_username_uindex unique (`username`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;


create table users_projects
(
    `user_id`     int null,
    `projects_id` int not null primary key
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;;






