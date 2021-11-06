package ru.kpfu.webproject.fayzrakhmanov.services;

import ru.kpfu.webproject.fayzrakhmanov.entity.Book;

import java.util.List;

public interface BookService {
    List<Book> getAllBooks();
    List<Book> getBooksByGenre(long id);
}
