CREATE DATABASE IF NOT EXISTS `library`;
USE `library`;

CREATE TABLE `author` (
	`id` bigint(20) NOT NULL AUTO_INCREMENT,
	`fio` varchar(300) NOT NULL,
	`birthday` date NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `genre` (
	`id` bigint(20) NOT NULL AUTO_INCREMENT,
	`name` varchar(100) NOT NULL,
	PRIMARY KEY (`id`)
);
alter table genre add constraint unique (name);

CREATE TABLE `book` (
	id bigint(20) NOT NULL AUTO_INCREMENT,
	name varchar(45) NOT NULL,
	content longblob NOT NULL,
	page_count int(11) NOT NULL,
	isbn varchar(100) NOT NULL,
	genre_id bigint(20) NOT NULL,
	publish_year int(4) NOT NULL,
	publisher_id bigint(20) NOT NULL,
	image longblob,
	PRIMARY KEY (id),
	UNIQUE KEY id_UNIQUE (`id`),
	UNIQUE KEY `isbn_UNIQUE` (`isbn`),
	KEY `fk_genre_idx` (`genre_id`),
	CONSTRAINT `fk_genre` FOREIGN KEY (`genre_id`) REFERENCES `genre` (`id`) ON UPDATE CASCADE
);
alter table book add constraint unique (isbn);
ALTER TABLE library.book MODIFY content VARCHAR(32);

CREATE TABLE `publisher` (
	`id` bigint(20) NOT NULL AUTO_INCREMENT,
	`name` varchar(100) NOT NULL,
	PRIMARY KEY (`id`)
);

create table author_book (
  book_id bigint(20) NOT NULL,
  author_id bigint(20) NOT NULL,
  constraint author_book_pk
      primary key (book_id, author_id)
);
alter table library.author_book add constraint author_book_author_id_fk
    foreign key (author_id) references author (id);
alter table library.author_book add constraint author_book_book_id_fk
    foreign key (book_id) references book (id);