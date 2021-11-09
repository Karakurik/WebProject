package ru.kpfu.webproject.fayzrakhmanov.constants;

public class DatabaseConstants {

    public static final String DATA_SOURSE = "dataSourse";

    public static final String BOOKS_FILES_PATH = "D:\\java-projects\\Semestrovka1\\WebProject\\src\\main\\webapp\\resources\\files\\";

    private static String SCHEMA = "library.";

    private static String USER_TABLE = "libraryuser";
    private static String AUTHOR_TABLE = "author";
    private static String GENRE_TABLE = "genre";
    private static String BOOK_TABLE = "book";
    private static String PUBLISHER_TABLE = "publisher";
    private static String AUTHOR_BOOK_TABLE = "author_book";
    private static String BOOK_CONTENT = "content";

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
    private static String BIRTHDAY_FIELD_AUTHOR_TABLE = "birthday";

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

    public static String SELECT_USER_BY_EMAIL = "" +
            "SELECT * " +
            "FROM " + SCHEMA + USER_TABLE + " " +
            "WHERE " + EMAIL_FIELD_USER_TABLE + " = ?";

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
            "SELECT b.id, b.name, b.content, b.isbn, b.page_count, b.publish_year, p.name as publisher, a.fio as author, g.name as genre, b.image " +
            "FROM " + SCHEMA + BOOK_TABLE + " b " +
            "INNER JOIN library.author_book ab on b.id=ab.book_id " +
            "INNER JOIN library.author a on a.id=ab.author_id " +
            "INNER JOIN library.genre g on b.genre_id=g.id " +
            "INNER JOIN library.publisher p on b.publisher_id=p.id " +
            "ORDER BY b.id";

    public static String SELECT_BOOK_BY_GENRE = "" +
            "SELECT b.id, b.name, b.content, b.isbn, b.page_count, b.publish_year, p.name as publisher, a.fio as author, g.name as genre, b.image " +
            "FROM " + SCHEMA + BOOK_TABLE + " b " +
            "INNER JOIN library.author_book ab on b.id=ab.book_id " +
            "INNER JOIN library.author a on a.id=ab.author_id " +
            "INNER JOIN library.genre g on b.genre_id=g.id " +
            "INNER JOIN library.publisher p on b.publisher_id=p.id " +
            "WHERE genre_id=? order by b.name " +
            "limit 0,5";

    public static String SELECT_BOOK_CONTENT_BY_ID = "" +
            "SELECT " + BOOK_CONTENT + " " +
            "FROM " + SCHEMA + BOOK_TABLE + " " +
            "WHERE " + "id=?";

    public static String SELECT_AUTHOR_BY_FIO = "" +
            "SELECT * " +
            "FROM " + SCHEMA + AUTHOR_TABLE + " " +
            "WHERE " + FIO_FIELD_AUTHOR_TABLE + " = ?";

    public static String SELECT_GENRE_BY_NAME = "" +
            "SELECT * " +
            "FROM " + SCHEMA + GENRE_TABLE + " " +
            "WHERE " + NAME_FIELD + " = ?";

    public static String SELECT_PUBLISHER_BY_NAME = "" +
            "SELECT * " +
            "FROM " + SCHEMA + PUBLISHER_TABLE + " " +
            "WHERE " + NAME_FIELD + " = ?";

    public static String INSERT_AUTHOR = "" +
            "INSERT INTO " + SCHEMA + AUTHOR_TABLE +
            " (" + FIO_FIELD_AUTHOR_TABLE + ") " +
            "VALUES (?);";

    public static String INSERT_GENRE = "" +
            "INSERT INTO " + SCHEMA + GENRE_TABLE + " (" + NAME_FIELD + ") " +
            "VALUES (?);";

    public static String INSERT_PUBLISHER =
            "INSERT INTO " + SCHEMA + PUBLISHER_TABLE + " (" + NAME_FIELD + ") " +
                    "VALUES (?)";

    public static String INSERT_BOOK = "" +
            "INSERT INTO " + SCHEMA + BOOK_TABLE + " (" +
            "name, content, page_count, isbn, genre_id, publish_year, publisher_id) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?)";

    public static String SELECT_BOOK_BY_ISBN = "" +
            "SELECT id " +
            "FROM " + SCHEMA + BOOK_TABLE + " " +
            "WHERE isbn = ?";

    public static String INSERT_AUTHOR_BOOK = "" +
            "INSERT INTO " + SCHEMA + AUTHOR_BOOK_TABLE + " (" +
            "book_id, author_id) " +
            "VALUES (?, ?)";

    public static String DELETE_AUTHOR_BOOK = "" +
            "DELETE " +
            "FROM " + SCHEMA + AUTHOR_BOOK_TABLE + " " +
            "WHERE book_id = ?";

    public static String DELETE_BOOK = "" +
            "DELETE " +
            "FROM " + SCHEMA + BOOK_TABLE + " " +
            "WHERE ID = ?;";
}
