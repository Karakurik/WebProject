package ru.kpfu.webproject.fayzrakhmanov.services;

import ru.kpfu.webproject.fayzrakhmanov.entity.Book;
import ru.kpfu.webproject.fayzrakhmanov.repositories.BookRepository;

import java.util.List;

public class BookServiceJdbcImpl implements BookService {
    private BookRepository bookRepository;

    public BookServiceJdbcImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.getAllBooks();
    }

    @Override
    public List<Book> getBooksByGenre(long id) {
        return bookRepository.getBooksByGenre(id);
    }
}
