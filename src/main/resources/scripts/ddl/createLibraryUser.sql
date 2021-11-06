CREATE DATABASE IF NOT EXISTS `library`;
USE `library`;

CREATE TABLE libraryuser
(
    login varchar(32) PRIMARY KEY NOT NULL,
    name varchar(32) NOT NULL,
    password varchar(64) NOT NULL,
    email varchar(128) NOT NULL
);
CREATE UNIQUE INDEX libraryuser_login_uindex ON libraryuser (login);