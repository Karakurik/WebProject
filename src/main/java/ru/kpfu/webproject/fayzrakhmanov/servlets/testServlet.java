package ru.kpfu.webproject.fayzrakhmanov.servlets;

import com.mysql.cj.jdbc.Blob;
import ru.kpfu.webproject.fayzrakhmanov.entity.Book;
import ru.kpfu.webproject.fayzrakhmanov.services.BookService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import static ru.kpfu.webproject.fayzrakhmanov.constants.ServicesConstants.BOOK_SERVICE;

@WebServlet("/test")
public class testServlet extends HttpServlet {
    private ServletContext context;
    private BookService bookService;

    @Override
    public void init() throws ServletException {
        context = getServletContext();
        bookService = (BookService) context.getAttribute(BOOK_SERVICE);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        request.setAttribute("bookService", bookService);

        long genreId = 0L;
        try {
            genreId = Long.parseLong(request.getParameter("genre_id"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        request.setAttribute("genreId", genreId);
        List<Book> list = bookService.getBooksByGenre(1);
        request.getSession().setAttribute("currentBookList", list);
        request.setAttribute("list", list);
        response.getWriter().println(list.size());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }
}
