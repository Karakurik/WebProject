package ru.kpfu.webproject.fayzrakhmanov.listeners;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.kpfu.webproject.fayzrakhmanov.Exceptions.PropertiesException;
import ru.kpfu.webproject.fayzrakhmanov.repositories.*;
import ru.kpfu.webproject.fayzrakhmanov.services.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.IOException;
import java.util.Properties;

import static ru.kpfu.webproject.fayzrakhmanov.constants.DatabaseConstants.DATA_SOURSE;
import static ru.kpfu.webproject.fayzrakhmanov.constants.ServicesConstants.*;

@WebListener
public class InitListeners implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();

        Properties properties = new Properties();
        try {
            properties.load(servletContext.getResourceAsStream("db/db.properties"));
        } catch (IOException e) {
            throw new PropertiesException(e);
        }

        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(properties.getProperty("db.url"));
        hikariConfig.setDriverClassName(properties.getProperty("db.driver"));
        hikariConfig.setUsername(properties.getProperty("db.user"));
        hikariConfig.setPassword(properties.getProperty("db.password"));
        hikariConfig.setMaximumPoolSize(Integer.parseInt(properties.getProperty("db.hikari.pool-size")));

        HikariDataSource dataSource = new HikariDataSource(hikariConfig);

        servletContext.setAttribute(DATA_SOURSE, dataSource);

        UserRepository userRepository = new UserRepositoryJdbcImpl(dataSource);
        AuthorRepository authorRepository = new AuthorRepositoryJdbcImpl(dataSource);
        GenreRepository genreRepository = new GenreRepositoryJdbcImpl(dataSource);
        BookRepository bookRepository = new BookRepositoryJdbcImpl(dataSource);

        UserService userService = new UserServiceJdbcImpl(userRepository);
        AuthorService authorService = new AuthorServiceJdbcImpl(authorRepository);
        GenreService genreService = new GenreServiceJdbcImpl(genreRepository);
        BookService bookService = new BookServiceJdbcImpl(bookRepository);

        SecurityService securityService = new SecurityServiceJdbcImpl(userRepository, new BCryptPasswordEncoder());

        servletContext.setAttribute(USER_SERVICE, userService);
        servletContext.setAttribute(AUTHOR_SERVICE, authorService);
        servletContext.setAttribute(GENRE_SERVICE, genreService);
        servletContext.setAttribute(BOOK_SERVICE, bookService);
        servletContext.setAttribute(SECURITY_SERVICE, securityService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
