package ru.kpfu.webproject.fayzrakhmanov.services;

import ru.kpfu.webproject.fayzrakhmanov.entity.Book;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Blob;
import java.util.List;

public interface BookService extends CrudService<Book> {
    List<Book> getAllBooks();
    List<Book> getBooksByGenre(long id);
    String getBookContentFileNameById(long id);

    Book selectBook(int id);
    void downloadFile(String fileName, OutputStream responseOutputStream) throws IOException;
}
