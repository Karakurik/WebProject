package ru.kpfu.webproject.fayzrakhmanov.services;

import ru.kpfu.webproject.fayzrakhmanov.entity.Book;
import ru.kpfu.webproject.fayzrakhmanov.repositories.BookRepository;

import java.sql.Blob;
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

    @Override
    public Blob getBookContentById(long id) {
        return bookRepository.getBookContentById(id);
    }

    @Override
    public Book selectBook(int id) {
        return null;
    }

    @Override
    public String create(Book entity) {
        return bookRepository.create(entity);
    }

    @Override
    public void update(Book entity) {
        bookRepository.update(entity);
    }

    @Override
    public void delete(Book entity) {
        bookRepository.delete(entity);
    }

    @Override
    public void delete(int id) {
        bookRepository.delete(id);
    }
}
