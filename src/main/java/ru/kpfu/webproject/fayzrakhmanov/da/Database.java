package ru.kpfu.webproject.fayzrakhmanov.da;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Класс для подключения к БД
 */
public class Database {

    private static Connection conn;

    public static Connection getConnection(){
        try {

            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/mysql",
                    "root",
                    "root");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}
