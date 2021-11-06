package ru.kpfu.webproject.fayzrakhmanov.repositories;

import ru.kpfu.webproject.fayzrakhmanov.constants.DatabaseConstants;
import ru.kpfu.webproject.fayzrakhmanov.da.Database;
import ru.kpfu.webproject.fayzrakhmanov.entity.Book;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookRepositoryJdbcImpl implements BookRepository {
    private DataSource dataSource;

    public BookRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private List<Book> getBooks(String query) {
        List<Book> bookList = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement()) {

            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
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
                bookList.add(book);
            }
        } catch (SQLException ignored) {
        }
        return bookList;
    }

    public List<Book> getAllBooks() {
        return getBooks(DatabaseConstants.SELECT_BOOK);
    }

    public List<Book> getBooksByGenre(long id) {
        return getBooks("select b.id, b.name, b.isbn, b.page_count, b.publish_year, p.name as publisher, a.fio as author, g.name as genre, b.image from library.book b "
                + "inner join library.author_book ab on b.id=ab.book_id "
                + "inner join library.author a on a.id=ab.author_id "
                + "inner join library.genre g on b.genre_id=g.id "
                + "inner join library.publisher p on b.publisher_id=p.id "
                + "where genre_id=" + id + " order by b.name "
                + "limit 0,5");
    }
}
