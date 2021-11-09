package ru.kpfu.webproject.fayzrakhmanov.services;

import ru.kpfu.webproject.fayzrakhmanov.Exceptions.*;
import ru.kpfu.webproject.fayzrakhmanov.entity.Book;
import ru.kpfu.webproject.fayzrakhmanov.repositories.BookRepository;

import java.io.InputStream;
import java.io.OutputStream;
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
    public String getBookContentFileNameById(long id) {
        return bookRepository.getBookContentById(id);
    }

    @Override
    public Book selectBook(int id) {
        return bookRepository.selectBook(id);
    }

    @Override
    public void create(Book entity) throws CreateBookFailedException, IsbnAlreadyExistsException, UnrealPublishDateException {
        bookRepository.create(entity);
    }

    @Override
    public void update(Book entity) throws UpdateBookFailedException, IsbnAlreadyExistsException, UnrealPublishDateException {
        bookRepository.checkIsbn(entity);
        //Если нет исключения
        bookRepository.validateBook(entity);
        bookRepository.update(entity);
    }

    @Override
    public void delete(Book entity) throws DeleteBookFailedException {
        bookRepository.delete(entity);
    }

    @Override
    public void delete(int id) throws DeleteBookFailedException {
        bookRepository.delete(id);
    }

    @Override
    public void downloadFile(String fileName, OutputStream responseOutputStream) throws FileDownloadException {
        bookRepository.downloadFile(fileName, responseOutputStream);
    }

    @Override
    public void uploadFile(String submittedFileName, InputStream inputStream) throws FileUploadException {
        bookRepository.uploadFile(submittedFileName, inputStream);
    }
}
