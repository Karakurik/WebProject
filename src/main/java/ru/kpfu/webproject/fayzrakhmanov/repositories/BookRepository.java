package ru.kpfu.webproject.fayzrakhmanov.repositories;

import ru.kpfu.webproject.fayzrakhmanov.entity.Book;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

public interface BookRepository extends CrudRepository<Book> {
    List<Book> getAllBooks();
    List<Book> getBooksByGenre(long id);
    String getBookContentById(long id);
    Book selectBook(int id);
    void downloadFile(String fileName, OutputStream responseOutputStream) throws IOException;
}
