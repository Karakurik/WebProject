package ru.kpfu.webproject.fayzrakhmanov.repositories;

import ru.kpfu.webproject.fayzrakhmanov.entity.Book;

import java.io.File;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

public interface BookRepository extends CrudRepository<Book> {
    List<Book> getAllBooks();
    List<Book> getBooksByGenre(long id);
    Blob getBookContentById(long id);
}
