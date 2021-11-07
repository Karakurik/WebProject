package ru.kpfu.webproject.fayzrakhmanov.servlets;

import org.omg.CosNaming.BindingIterator;
import ru.kpfu.webproject.fayzrakhmanov.constants.ServicesConstants;
import ru.kpfu.webproject.fayzrakhmanov.entity.Book;
import ru.kpfu.webproject.fayzrakhmanov.services.BookService;
import ru.kpfu.webproject.fayzrakhmanov.services.GenreService;
import ru.kpfu.webproject.fayzrakhmanov.services.SecurityService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static ru.kpfu.webproject.fayzrakhmanov.constants.ServicesConstants.*;

@WebServlet("/books")
public class BooksServlet extends HttpServlet {
    private ServletContext context;
    private BookService bookService;
    private GenreService genreService;
    private SecurityService securityService;

    @Override
    public void init() throws ServletException {
        context = getServletContext();
        securityService = (SecurityService) context.getAttribute(SECURITY_SERVICE);
        bookService = (BookService) context.getAttribute(BOOK_SERVICE);
        genreService = (GenreService) context.getAttribute(GENRE_SERVICE);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        request.setAttribute(SECURITY_SERVICE, securityService);
        request.setAttribute(BOOK_SERVICE, bookService);
        if (securityService.isAdmin(request)) {
            request.setAttribute("isAdmin", true);
        } else {
            request.setAttribute("isAdmin", false);
        }

        long genreId = 0L;
        try {
            genreId = Long.parseLong(request.getParameter("genre_id"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        request.setAttribute("genreId", genreId);
        List<Book> list = bookService.getBooksByGenre(genreId);
        if (genreId == 0L) list = bookService.getAllBooks();
        request.getSession().setAttribute("currentBookList", list);
        request.setAttribute("list", list);

        context.getRequestDispatcher("/pages/books.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchString = request.getParameter("search_String");
        String searchOption = request.getParameter("search_option");
        List<Book> list = bookService.getAllBooks();
        switch (searchOption) {
            case "Название":
                list = list.stream()
                        .filter(o -> o.getName().toLowerCase().contains(searchString.toLowerCase()))
                        .collect(Collectors.toList());
                break;
            case "Автор":
                list = list.stream()
                        .filter(o -> o.getAuthor().toLowerCase().contains(searchString.toLowerCase()))
                        .collect(Collectors.toList());
                break;
            default:
                list = new ArrayList<>();
        }
        request.getSession().setAttribute("currentBookList", list);
        request.setAttribute("list", list);
        context.getRequestDispatcher("/pages/books.jsp").forward(request, response);
    }
}
