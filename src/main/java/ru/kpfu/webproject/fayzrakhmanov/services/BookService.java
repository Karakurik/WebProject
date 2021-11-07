package ru.kpfu.webproject.fayzrakhmanov.services;

import ru.kpfu.webproject.fayzrakhmanov.entity.Book;

import java.sql.Blob;
import java.util.List;

public interface BookService extends CrudService<Book> {
    List<Book> getAllBooks();
    List<Book> getBooksByGenre(long id);
    public Blob getBookContentById(long id);

    Book selectBook(int id);
}
