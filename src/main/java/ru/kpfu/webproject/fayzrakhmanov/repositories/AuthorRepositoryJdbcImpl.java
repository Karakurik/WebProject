package ru.kpfu.webproject.fayzrakhmanov.repositories;

import ru.kpfu.webproject.fayzrakhmanov.Exceptions.DataSourceException;
import ru.kpfu.webproject.fayzrakhmanov.constants.DatabaseConstants;
import ru.kpfu.webproject.fayzrakhmanov.entity.Author;

import javax.management.Query;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static ru.kpfu.webproject.fayzrakhmanov.constants.DatabaseConstants.SELECT_AUTHOR;

public class AuthorRepositoryJdbcImpl implements AuthorRepository {
    private DataSource dataSource;

    public AuthorRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private List<Author> getAuthors(String query) throws DataSourceException {
        List<Author> authorList = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
            Statement stmt = conn.createStatement()){
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Author author = new Author();
                author.setName(rs.getString("fio"));
                authorList.add(author);
            }
        } catch (SQLException e) {
            throw new DataSourceException(e);
        }
        return authorList;
    }

    @Override
    public List<Author> getAuthorList() throws DataSourceException {
        return getAuthors(SELECT_AUTHOR);
    }

}
