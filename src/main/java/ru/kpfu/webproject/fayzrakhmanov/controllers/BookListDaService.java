package ru.kpfu.webproject.fayzrakhmanov.controllers;

import ru.kpfu.webproject.fayzrakhmanov.constants.DatabaseConstants;
import ru.kpfu.webproject.fayzrakhmanov.da.Database;
import ru.kpfu.webproject.fayzrakhmanov.entity.Book;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class BookListDaService {
    private ArrayList<Book> bookList = new ArrayList<>();

    private ArrayList<Book> getBooks(String query) {

        Statement stmt = null;
        ResultSet rs = null;
        Connection conn = null;

        try {
            conn = Database.getConnection();

            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
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
        } catch (SQLException ex) {
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (rs != null) {
                    rs.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
            }
        }

        return bookList;
    }

    public ArrayList<Book> getAllBooks() {
        if (!bookList.isEmpty()) {
            return bookList;
        } else {
            return getBooks(DatabaseConstants.SELECT_BOOK);
        }
    }

    public ArrayList<Book> getBooksByGenre(long id) {;
        return getBooks("select b.id, b.name, b.isbn, b.page_count, b.publish_year, p.name as publisher, a.fio as author, g.name as genre, b.image from library.book b "
                + "inner join library.author_book ab on b.id=ab.book_id "
                + "inner join library.author a on a.id=ab.author_id "
                + "inner join library.genre g on b.genre_id=g.id "
                + "inner join library.publisher p on b.publisher_id=p.id "
                + "where genre_id=" + id + " order by b.name "
                + "limit 0,5");
    }
}
