package ru.kpfu.webproject.fayzrakhmanov.repositories;

import ru.kpfu.webproject.fayzrakhmanov.constants.DatabaseConstants;
import ru.kpfu.webproject.fayzrakhmanov.da.Database;
import ru.kpfu.webproject.fayzrakhmanov.entity.Author;
import ru.kpfu.webproject.fayzrakhmanov.entity.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static ru.kpfu.webproject.fayzrakhmanov.constants.DatabaseConstants.*;

public class UserRepositoryJdbcImpl implements UserRepository {
    private DataSource dataSource;

    public UserRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void save(User entity) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement psRegister = conn.prepareStatement(REGISTER_USER)
        ) {
            psRegister.setString(1, entity.getLogin());
            psRegister.setString(2, entity.getName());
            psRegister.setString(3, entity.getPasswordHash());
            psRegister.setString(4, entity.getEmail());

            psRegister.execute();
        } catch (SQLException throwables) {
        }
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void delete(User entity) {

    }

    @Override
    public List<User> findAll() {
        List<User> userList = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
            Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(SELECT_ALL_USER);
            while (rs.next()) {
//                User user = new User();
//                user.setLogin(rs.getString(LOGIN_FIELD_USER_TABLE));
//                user.setName(rs.getString(NAME_FIELD_USER_TABLE));
//                user.setPasswordHash(rs.getString(PASSWORD_FIELD_USER_TABLE));
//                user.setEmail(rs.getString(EMAIL_FIELD_USER_TABLE));
//                userList.add(user);
                userList.add(userByRs(rs));
            }
        } catch (SQLException ignored) {

        }
        return userList;
    }

    @Override
    public User getByLogin(String login) {
        User user = null;
        try (Connection conn = dataSource.getConnection();
             PreparedStatement psRegister = conn.prepareStatement(SELECT_USER_BY_LOGIN)
        ) {
            psRegister.setString(1, login);

            ResultSet rs = psRegister.executeQuery();

            if (rs.next()) {
                user = userByRs(rs);
            }
        } catch (SQLException ignored) {
        }
        return user;
    }

    private static User userByRs(ResultSet rs) throws SQLException {
        User user = new User();
        user.setLogin(rs.getString(LOGIN_FIELD_USER_TABLE));
        user.setName(rs.getString(NAME_FIELD_USER_TABLE));
        user.setPasswordHash(rs.getString(PASSWORD_FIELD_USER_TABLE));
        user.setEmail(rs.getString(EMAIL_FIELD_USER_TABLE));
        return user;
    }
}
