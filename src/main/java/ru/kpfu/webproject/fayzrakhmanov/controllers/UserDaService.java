package ru.kpfu.webproject.fayzrakhmanov.controllers;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.kpfu.webproject.fayzrakhmanov.constants.DatabaseConstants;
import ru.kpfu.webproject.fayzrakhmanov.da.Database;
import ru.kpfu.webproject.fayzrakhmanov.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * User data access service - класс для получения данных о пользователе из БД
 * либо для регистрации пользователя
 */
public class UserDaService {

    private static final String REGISTRATION = "registration";
    private static final String MAIN = "main";

    /**
     * Процесс регистрации нового пользователя
     * @param name - имя
     * @param login - логин
     * @param password - пароль
     * @param email - email
     * @return назание страницы для отображения клиенту
     */
    public static String registerUser(String name, String login, String password, String email) {
        Connection conn = null;
        PreparedStatement ps = null;
        PreparedStatement psRegister = null;
        ResultSet rs = null;
        try {
            conn = Database.getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(DatabaseConstants.SELECT_USER_BY_LOGIN);
                ps.setString(1, login.trim());
                rs = ps.executeQuery();
                if (rs.next()) {
                    System.out.println("Пользователь с таким логином существует!");
                    //название файла - отображение страницы регистрации
                    return REGISTRATION;
                } else {
                    //регистрируем клиента
                    psRegister = conn.prepareStatement(DatabaseConstants.REGISTER_USER);
                    psRegister.setString(1, login.trim());
                    psRegister.setString(2, name);
                    psRegister.setString(3, password);
                    psRegister.setString(4, email);

                    psRegister.execute();

                    return MAIN;
                }
            } else {
                System.out.println("registerUser - connection is NULL");
            }
        } catch (Exception e) {
            System.out.println("Произошла ошибка при попытке зарегистрировать клиента");
        } finally {
            try {
                if (ps != null)
                    ps.close();
                if (psRegister != null)
                    psRegister.close();
                if (rs != null)
                    rs.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException ex) {
            }
        }
        return REGISTRATION;
    }

    public static User getUser(String login, String password) {
        Connection connection = null;
        PreparedStatement ps = null;
        User user = null;
        try {
            connection = Database.getConnection();
            if (connection != null) {
                ps = connection.prepareStatement(DatabaseConstants.SELECT_USER);
                ps.setString(1, login.trim());
                ps.setString(2, password);

                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    user = new User();
                    user.setLogin(rs.getString(1));
                }
            }
        } catch (Exception e) {
            // TODO: 24.10.2021 реализовать логирование ошибки
            e.printStackTrace();
        }
        return user;
    }
}
