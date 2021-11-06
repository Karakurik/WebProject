package ru.kpfu.webproject.fayzrakhmanov.repositories;

import ru.kpfu.webproject.fayzrakhmanov.entity.Book;

import java.util.ArrayList;
import java.util.List;

public interface BookRepository {
    List<Book> getAllBooks();
    List<Book> getBooksByGenre(long id);
}
