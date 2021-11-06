USE `library`;

INSERT INTO author (fio, birthday) VALUES ('Грегори Дэвид Робертс', '1952-06-21');
INSERT INTO author (fio, birthday) VALUES ('Анри Шарьер', '1906-11-16');
INSERT INTO author (fio, birthday) VALUES ('Рэй Брэдбери', '1920-08-22');
INSERT INTO author (fio, birthday) VALUES ('Джером Дэвид Сэлинджер', '1919-01-01');
INSERT INTO author (fio, birthday) VALUES ('Харпер Ли', '1926-04-28');
INSERT INTO author (fio, birthday) VALUES ('Дмитрий Глуховский', '1979-06-12');
INSERT INTO author (fio, birthday) VALUES ('Диана Сеттерфилд', '1964-08-22');

INSERT INTO genre (name) VALUES ('Роман');
INSERT INTO genre (name) VALUES ('Филосовский роман');
INSERT INTO genre (name) VALUES ('Детективы');
INSERT INTO genre (name) VALUES ('Повесть');
INSERT INTO genre (name) VALUES ('Фантастика');


INSERT INTO publisher (name) VALUES ('Азбука-Аттикус');
INSERT INTO publisher (name) VALUES ('Азбука');
INSERT INTO publisher (name) VALUES ('Эксмо');
INSERT INTO publisher (name) VALUES ('ФТМ');
INSERT INTO publisher (name) VALUES ('АСТ');


INSERT INTO book (name, content, page_count, isbn, genre_id, publish_year, publisher_id)
VALUES ('Шантарам', 1, 1270, '978-5-389-11023-6', 1, 2009, 1);
INSERT INTO book (name, content, page_count, isbn, genre_id, publish_year, publisher_id)
VALUES ('Мотылек', 1, 740, '978-5-389-07965-6', 1, 1992, 1);
INSERT INTO book (name, content, page_count, isbn, genre_id, publish_year, publisher_id)
VALUES ('451 градус по Фаренгейту', 1, 200, '978-5-04-102293-8', 2, 2019, 3);
INSERT INTO book (name, content, page_count, isbn, genre_id, publish_year, publisher_id)
VALUES ('Над пропастью во ржи', 1, 240, '978-5-04-101317-2', 4, 2011, 3);
INSERT INTO book (name, content, page_count, isbn, genre_id, publish_year, publisher_id)
VALUES ('Убить пересмешника', 1, 350, '978-5-17-083520-1', 1, 1964, 4);
INSERT INTO book (name, content, page_count, isbn, genre_id, publish_year, publisher_id)
VALUES ('Метро 2033', 1, 650, '978-5-17-091382-4', 5, 2005, 5);
INSERT INTO book (name, content, page_count, isbn, genre_id, publish_year, publisher_id)
VALUES ('Метро 2034', 1, 350, '978-5-17-058788-9', 5, 2009, 5);
INSERT INTO book (name, content, page_count, isbn, genre_id, publish_year, publisher_id)
VALUES ('Тринадцатая сказка', 1, 480, '978-5-389-06665-6', 1, 2013, 1);
INSERT INTO book (name, content, page_count, isbn, genre_id, publish_year, publisher_id)
VALUES ('Метро 2035', 1, 480, '978-5-17-090538-6', 5, 2015, 5);
INSERT INTO book (name, content, page_count, isbn, genre_id, publish_year, publisher_id)
VALUES ('Шантарам 2: Тень горы', 1, 1010, '978-5-389-10993-3', 1, 2015, 1);

INSERT  into author_book values('1', '1');
INSERT  into author_book values('2', '2');
INSERT  into author_book values('3', '3');
INSERT  into author_book values('4', '4');
INSERT  into author_book values('5', '5');
INSERT  into author_book values('6', '6');
INSERT  into author_book values('7', '6');
INSERT  into author_book values('8', '7');
INSERT  into author_book values('9', '6');
INSERT  into author_book values('10', '1');