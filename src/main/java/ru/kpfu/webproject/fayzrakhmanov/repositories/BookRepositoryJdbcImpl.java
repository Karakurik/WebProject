package ru.kpfu.webproject.fayzrakhmanov.repositories;

import ru.kpfu.webproject.fayzrakhmanov.constants.DatabaseConstants;
import ru.kpfu.webproject.fayzrakhmanov.entity.Book;

import javax.sql.DataSource;
import javax.sql.rowset.serial.SerialBlob;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static ru.kpfu.webproject.fayzrakhmanov.constants.DatabaseConstants.SELECT_BOOK;
import static ru.kpfu.webproject.fayzrakhmanov.constants.DatabaseConstants.SELECT_BOOK_CONTENT_BY_ID;

public class BookRepositoryJdbcImpl implements BookRepository {
    private DataSource dataSource;

    public BookRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private static Book bookByRs(ResultSet rs) throws SQLException {
        Book book = new Book();
        book.setId(rs.getLong("id"));
        book.setName(rs.getString("name"));
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
    public Blob getBookContentById(long id) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_BOOK_CONTENT_BY_ID)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                byte[] bytes = rs.getBytes("content");
                return new SerialBlob(bytes);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public String create(Book entity) {
        String message = null;
        if (entity != null) {
            try (Connection conn = dataSource.getConnection()) {
                if (validateBook(entity)) {
                    String authorFIO = entity.getAuthor();
                    int authorId = 0;
                    int genreId = 0;
                    int publisherId = 0;
                    try {
                        //Проверяем, существует ли автор в БД
                        PreparedStatement psSelectAuthor = conn.prepareStatement(DatabaseConstants.SELECT_AUTHOR_BY_FIO);
                        psSelectAuthor.setString(1, authorFIO);
                        ResultSet rsSelectAuhor = psSelectAuthor.executeQuery();
                        if (rsSelectAuhor.next()) {
                            authorId = rsSelectAuhor.getInt(1);
                        } else {
                            // TODO: 06.11.2021 throw new exception
                            throw new Exception();
                        }

                        //Проверяем, существует ли жанр
                        PreparedStatement psSelectGenre = conn.prepareStatement(DatabaseConstants.SELECT_GENRE_BY_NAME);
                        psSelectGenre.setString(1, entity.getGenre());
                        ResultSet rsSelectGenre = psSelectGenre.executeQuery();
                        if (rsSelectGenre.next()) {
                            genreId = rsSelectGenre.getInt(1);
                        } else {
                            PreparedStatement psInsertGenre = conn.prepareStatement(DatabaseConstants.INSERT_GENRE);
                            psInsertGenre.setString(1, entity.getGenre());
                            psInsertGenre.execute();
                            rsSelectGenre = psSelectGenre.executeQuery();
                            if (rsSelectGenre.next()) {
                                genreId = rsSelectGenre.getInt(1);
                            }
                        }

                        //Проверяем, существует ли издательство
                        PreparedStatement psSelectPublisher = conn.prepareStatement(DatabaseConstants.SELECT_PUBLISHER_BY_NAME);
                        psSelectPublisher.setString(1, entity.getPublisher());
                        ResultSet rsSelectPublisher = psSelectPublisher.executeQuery();
                        if (rsSelectPublisher.next()) {
                            publisherId = rsSelectPublisher.getInt(1);
                        } else {
                            PreparedStatement psInsertPublisher = conn.prepareStatement(DatabaseConstants.INSERT_PUBLISHER);
                            psInsertPublisher.setString(1, entity.getPublisher());
                            psInsertPublisher.execute();
                            rsSelectPublisher = psSelectPublisher.executeQuery();
                            if (rsSelectPublisher.next()) {
                                publisherId = rsSelectPublisher.getInt(1);
                            }
                        }


                        if (authorId != 0 && genreId != 0 && publisherId != 0) {
                            //заполнение книги и таблицы author_book
                            PreparedStatement psInsertBook = conn.prepareStatement(DatabaseConstants.INSERT_BOOK);
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
                                //Не удалось создать книгу, к примеру, isBN не уникальный
                            }


                            // TODO: 06.11.2021 придумать логику заполнения author_book в зависимости от результата создания book
                            PreparedStatement psSelectBook = conn.prepareStatement(DatabaseConstants.SELECT_BOOK_BY_ISBN);
                            psSelectBook.setString(1, entity.getIsbn());
                            ResultSet rsSelectBook = psSelectBook.executeQuery();
                            if (rsSelectBook.next()) {
                                int bookId = rsSelectBook.getInt(0);
                                PreparedStatement psInsertBookAuthor = conn.prepareStatement(DatabaseConstants.INSERT_AUTHOR_BOOK);
                                psInsertBookAuthor.setInt(0, bookId);
                                psInsertBookAuthor.setInt(1, authorId);
                                psInsertBook.execute();
                            }
                        }


                    } catch (Exception e) {
                        message = e.getMessage();
                        // TODO: 06.11.2021
                        e.printStackTrace();
                    }
                } else {
                    message = "Заполнены не все обязательные поля";
                }
            } catch (SQLException throwables) {
            }
        }
        return message;
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
    public void update(Book entity) {

    }

    //каскадное удаление проверить
    @Override
    public void delete(Book entity) {
        if (entity != null && entity.getId() > 0) {
            try (Connection conn = dataSource.getConnection();
                 PreparedStatement psDeleteBook = conn.prepareStatement(DatabaseConstants.DELETE_BOOK)
            ) {
                psDeleteBook.setLong(1, entity.getId());
                psDeleteBook.execute();
            } catch (SQLException ignored) {

            }
        }
    }

    @Override
    public void delete(int id) {
        if (id > 0) {
            try (Connection conn = dataSource.getConnection();
                 PreparedStatement psDeleteBook = conn.prepareStatement(DatabaseConstants.DELETE_BOOK)
            ) {
                psDeleteBook.setLong(1, id);
                psDeleteBook.execute();
            } catch (SQLException ignored) {
            }
        }
    }
}
