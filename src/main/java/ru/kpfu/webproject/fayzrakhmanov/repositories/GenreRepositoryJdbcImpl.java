package ru.kpfu.webproject.fayzrakhmanov.repositories;

import ru.kpfu.webproject.fayzrakhmanov.constants.DatabaseConstants;
import ru.kpfu.webproject.fayzrakhmanov.da.Database;
import ru.kpfu.webproject.fayzrakhmanov.entity.Book;
import ru.kpfu.webproject.fayzrakhmanov.entity.Genre;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GenreRepositoryJdbcImpl implements GenreRepository {
    private DataSource dataSource;

    public GenreRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private List<Genre> getGenres() {
        List<Genre> genreList = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
            Statement stmt = conn.createStatement()){

            ResultSet rs = stmt.executeQuery(DatabaseConstants.SELECT_GENRE);
            while (rs.next()) {
                Genre genre = new Genre();
                genre.setId(rs.getLong("id"));
                genre.setName(rs.getString("name"));
                genreList.add(genre);
            }
        } catch (SQLException ignored) {
        }
        return genreList;
    }

    public List<Genre> getGenreList() {
        return getGenres();
    }
}
