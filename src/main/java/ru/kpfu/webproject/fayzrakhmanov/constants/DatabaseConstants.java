package ru.kpfu.webproject.fayzrakhmanov.constants;

/**
 * Шаблоны sql запросов к БД, подходит для MySQL
 * Для Postgres потребуется изменение синтаксиса!
 */
public class DatabaseConstants {

    public static final String DATA_SOURSE = "dataSourse";

    private static String SCHEMA = "library.";

    private static String USER_TABLE = "libraryuser";
    private static String AUTHOR_TABLE = "author";
    private static String GENRE_TABLE = "genre";
    private static String BOOK_TABLE = "book";

    /**
     * Поля таблицы LibaryUser
     */
    public static String LOGIN_FIELD_USER_TABLE = "login";
    public static String NAME_FIELD_USER_TABLE = "name";
    public static String PASSWORD_FIELD_USER_TABLE = "password";
    public static String EMAIL_FIELD_USER_TABLE = "email";
    /**
     * Поля таблицы Author
     */
    private static String FIO_FIELD_AUTHOR_TABLE = "fio";

    private static String NAME_FIELD = "name";

    public static String SELECT_ALL_USER = "" +
            "SELECT * " +
            "FROM " + SCHEMA + USER_TABLE;

    public static String SELECT_USER = "" +
            "SELECT * " +
            "FROM " + SCHEMA + USER_TABLE + " " +
            "WHERE " + LOGIN_FIELD_USER_TABLE + " = ? " +
            "AND " + PASSWORD_FIELD_USER_TABLE + " = ?";

    public static String SELECT_USER_BY_LOGIN = "" +
            "SELECT * " +
            "FROM " + SCHEMA + USER_TABLE + " " +
            "WHERE " + LOGIN_FIELD_USER_TABLE + " = ?";

    public static String REGISTER_USER = "" +
            "INSERT INTO " +
            SCHEMA + USER_TABLE + " " +
            "VALUES (?, ?, ?, ?)";

    public static String SELECT_AUTHOR = "" +
            "SELECT * " +
            "FROM " + SCHEMA + AUTHOR_TABLE + " " +
            "ORDER BY " + FIO_FIELD_AUTHOR_TABLE;

    public static String SELECT_GENRE = "" +
            "SELECT * " +
            "FROM " + SCHEMA + GENRE_TABLE + " " +
            "ORDER BY " + NAME_FIELD;

    public static String SELECT_BOOK = "" +
            "SELECT * " +
            "FROM " + SCHEMA + BOOK_TABLE + " " +
            "ORDER BY " + NAME_FIELD;
}
