package ru.kpfu.webproject.fayzrakhmanov.repositories;

import ru.kpfu.webproject.fayzrakhmanov.Exceptions.*;
import ru.kpfu.webproject.fayzrakhmanov.constants.DatabaseConstants;
import ru.kpfu.webproject.fayzrakhmanov.entity.Book;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static ru.kpfu.webproject.fayzrakhmanov.constants.DatabaseConstants.*;

public class BookRepositoryJdbcImpl implements BookRepository {
    private DataSource dataSource;

    public BookRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private static Book bookByRs(ResultSet rs) throws SQLException {
        Book book = new Book();
        book.setId(rs.getInt("id"));
        book.setName(rs.getString("name"));
        book.setContentFileName(rs.getString("content"));
        book.setGenre(rs.getString("genre"));
        book.setIsbn(rs.getString("isbn"));
        book.setAuthor(rs.getString("author"));
        book.setPageCount(rs.getInt("page_count"));
        book.setPublishDate(rs.getInt("publish_year"));
        book.setPublisher(rs.getString("publisher"));
        book.setImage(rs.getBytes("image"));
        return book;
    }

    @Override
    public List<Book> getAllBooks() {
        List<Book> bookList = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement()) {

            ResultSet rs = stmt.executeQuery(SELECT_BOOK);
            while (rs.next()) {
                bookList.add(bookByRs(rs));
            }
        } catch (SQLException ignored) {
        }
        return bookList;
    }

    @Override
    public List<Book> getBooksByGenre(long id) {
        List<Book> bookList = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(DatabaseConstants.SELECT_BOOK_BY_GENRE)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                bookList.add(bookByRs(rs));
            }
        } catch (SQLException ignored) {
        }
        return bookList;
    }

    @Override
    public String getBookContentById(long id) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_BOOK_CONTENT_BY_ID)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("content");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Book selectBook(int id) {
        return getAllBooks().stream().filter(o -> o.getId() == id).findFirst().get();
    }

    @Override
    public void create(Book entity) throws CreateBookFailedException {
        if (entity != null) {
            try (Connection conn = dataSource.getConnection()) {
                if (validateBook(entity)) {
                    String authorFIO = entity.getAuthor();
                    int authorId = 0;
                    int genreId = 0;
                    int publisherId = 0;
                    try {
                        //Проверяем, существует ли автор в БД
                        PreparedStatement psSelectAuthor = conn.prepareStatement(SELECT_AUTHOR_BY_FIO);
                        psSelectAuthor.setString(1, authorFIO);
                        ResultSet rsSelectAuthor = psSelectAuthor.executeQuery();
                        if (rsSelectAuthor.next()) {
                            authorId = rsSelectAuthor.getInt(1);
                        } else {
                            PreparedStatement psInsertAuthor = conn.prepareStatement(INSERT_AUTHOR);
                            psInsertAuthor.setString(1, entity.getAuthor());
                            psInsertAuthor.execute();
                            rsSelectAuthor = psSelectAuthor.executeQuery();
                            if (rsSelectAuthor.next()) {
                                genreId = rsSelectAuthor.getInt(1);
                            }
                        }

                        //Проверяем, существует ли жанр
                        PreparedStatement psSelectGenre = conn.prepareStatement(SELECT_GENRE_BY_NAME);
                        psSelectGenre.setString(1, entity.getGenre());
                        ResultSet rsSelectGenre = psSelectGenre.executeQuery();
                        if (rsSelectGenre.next()) {
                            genreId = rsSelectGenre.getInt(1);
                        } else {
                            PreparedStatement psInsertGenre = conn.prepareStatement(INSERT_GENRE);
                            psInsertGenre.setString(1, entity.getGenre());
                            psInsertGenre.execute();
                            rsSelectGenre = psSelectGenre.executeQuery();
                            if (rsSelectGenre.next()) {
                                genreId = rsSelectGenre.getInt(1);
                            }
                        }

                        //Проверяем, существует ли издательство
                        PreparedStatement psSelectPublisher = conn.prepareStatement(SELECT_PUBLISHER_BY_NAME);
                        psSelectPublisher.setString(1, entity.getPublisher());
                        ResultSet rsSelectPublisher = psSelectPublisher.executeQuery();
                        if (rsSelectPublisher.next()) {
                            publisherId = rsSelectPublisher.getInt(1);
                        } else {
                            PreparedStatement psInsertPublisher = conn.prepareStatement(INSERT_PUBLISHER);
                            psInsertPublisher.setString(1, entity.getPublisher());
                            psInsertPublisher.execute();
                            rsSelectPublisher = psSelectPublisher.executeQuery();
                            if (rsSelectPublisher.next()) {
                                publisherId = rsSelectPublisher.getInt(1);
                            }
                        }


                        if (authorId != 0 && genreId != 0 && publisherId != 0) {
                            //заполнение книги и таблицы author_book
                            PreparedStatement psInsertBook = conn.prepareStatement(INSERT_BOOK);
                            psInsertBook.setString(1, entity.getName());
                            psInsertBook.setInt(2, 0);
                            psInsertBook.setInt(3, entity.getPageCount());
                            psInsertBook.setString(4, entity.getIsbn());
                            psInsertBook.setInt(5, genreId);
                            psInsertBook.setInt(6, entity.getPublishDate());
                            psInsertBook.setInt(7, publisherId);
                            try {
                                psInsertBook.execute();
                            } catch (Exception e) {
                                throw new CreateBookFailedException(e);
                            }


                            PreparedStatement psSelectBook = conn.prepareStatement(DatabaseConstants.SELECT_BOOK_BY_ISBN);
                            psSelectBook.setString(1, entity.getIsbn());
                            ResultSet rsSelectBook = psSelectBook.executeQuery();
                            if (rsSelectBook.next()) {
                                int bookId = rsSelectBook.getInt(1);
                                PreparedStatement psInsertBookAuthor = conn.prepareStatement(DatabaseConstants.INSERT_AUTHOR_BOOK);
                                psInsertBookAuthor.setInt(1, bookId);
                                psInsertBookAuthor.setInt(2, authorId);
                                psInsertBookAuthor.execute();
                            }
                        }
                    } catch (SQLException e) {
                        throw new CreateBookFailedException(e);
                    }
                } else {
                    throw new CreateBookFailedException();
                }
            } catch (SQLException e) {
                throw new CreateBookFailedException(e);
            }
        }
    }

    public static boolean validateBook(Book book) {
        return book != null
                && book.getName() != null
                && !book.getName().isEmpty()
                && book.getPageCount() > 0
                && book.getIsbn() != null
                && !book.getIsbn().isEmpty()
                && book.getGenre() != null
                && !book.getGenre().isEmpty()
                && book.getPublishDate() > 1000
                && book.getPublishDate() <= Calendar.getInstance().getWeekYear()
                && book.getPublisher() != null
                && !book.getPublisher().isEmpty()
                && book.getAuthor() != null
                && !book.getAuthor().isEmpty()
                ;
    }

    @Override
    public void update(Book entity) throws UpdateBookFailedException {
        try {
            delete(entity.getId());
            create(entity);
        } catch (CreateBookFailedException | DeleteBookFailedException e) {
            throw new UpdateBookFailedException(e);
        }
    }

    @Override
    public void delete(Book entity) throws DeleteBookFailedException {
        if (entity != null && entity.getId() > 0) {
            try (Connection conn = dataSource.getConnection();
                 PreparedStatement psDeleteBook = conn.prepareStatement(DatabaseConstants.DELETE_BOOK);
                 PreparedStatement psDeleteAuthorBook = conn.prepareStatement(DatabaseConstants.DELETE_AUTHOR_BOOK)
            ) {
                psDeleteBook.setLong(1, entity.getId());
                psDeleteAuthorBook.setLong(1, entity.getId());
                psDeleteAuthorBook.execute(); //Сначала это
                psDeleteBook.execute(); //Потом это
            } catch (SQLException e) {
                throw new DeleteBookFailedException(e);
            }
        } else {
            throw new DeleteBookFailedException();
        }
    }

    @Override
    public void delete(int id) throws DeleteBookFailedException {
        if (id > 0) {
            try (Connection conn = dataSource.getConnection();
                 PreparedStatement psDeleteAuthorBook = conn.prepareStatement(DatabaseConstants.DELETE_AUTHOR_BOOK);
                 PreparedStatement psDeleteBook = conn.prepareStatement(DatabaseConstants.DELETE_BOOK)
            ) {
                psDeleteAuthorBook.setLong(1, id);
                psDeleteBook.setLong(1, id);
                psDeleteAuthorBook.execute(); //Сначала это
                psDeleteBook.execute(); //Потом это
            } catch (SQLException e) {
                throw new DeleteBookFailedException(e);
            }
        } else {
            throw new DeleteBookFailedException();
        }
    }

    @Override
    public void downloadFile(String fileName, OutputStream outputStream) throws FileDownloadException {
        try {
            Files.copy(Paths.get(BOOKS_FILES_PATH + fileName), outputStream);
        } catch (IOException e) {
            throw new FileDownloadException(e);
        }
    }

    @Override
    public void uploadFile(String submittedFileName, InputStream inputStream) throws FileUploadException {
        try {
            Files.copy(inputStream, Paths.get(BOOKS_FILES_PATH + submittedFileName));
        } catch (IOException e) {
            throw new FileUploadException(e);
        }
    }
}
