package ru.kpfu.webproject.fayzrakhmanov.services;

import ru.kpfu.webproject.fayzrakhmanov.Exceptions.FileDownloadException;
import ru.kpfu.webproject.fayzrakhmanov.Exceptions.FileUploadException;
import ru.kpfu.webproject.fayzrakhmanov.entity.Book;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public interface BookService extends CrudService<Book> {
    List<Book> getAllBooks();

    List<Book> getBooksByGenre(long id);

    String getBookContentFileNameById(long id);

    Book selectBook(int id);

    void downloadFile(String fileName, OutputStream responseOutputStream) throws IOException, FileDownloadException;

    void uploadFile(String submittedFileName, InputStream inputStream) throws IOException, FileUploadException;
}
