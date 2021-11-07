package ru.kpfu.webproject.fayzrakhmanov.servlets;

import ru.kpfu.webproject.fayzrakhmanov.constants.ServicesConstants;
import ru.kpfu.webproject.fayzrakhmanov.entity.Book;
import ru.kpfu.webproject.fayzrakhmanov.services.BookService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/crudPanel/*")
public class CrudPanelServlet extends HttpServlet {
    private ServletContext context;
    private BookService bookService;

    @Override
    public void init() throws ServletException {
        context = getServletContext();
        bookService = (BookService) context.getAttribute(ServicesConstants.BOOK_SERVICE);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getRequestURI();

        try {
            switch (action) {
                case "/crudPanel/new":
                    showNewForm(request, response);
                    break;
                case "/crudPanel/insert":
                    insertBook(request, response);
                    break;
                case "/crudPanel/delete":
                    deleteUser(request, response);
                    break;
                case "/crudPanel/edit":
                    showEditForm(request, response);
                    break;
                case "/crudPanel/update":
                    updateUser(request, response);
                    break;
                default:
                    listUser(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Book> list = bookService.getAllBooks();
        request.setAttribute("listBook", list);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/crudPanel/book-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/crudPanel/book-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Book existingBook = bookService.selectBook(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/crudPanel/book-form.jsp");
        request.setAttribute("book", existingBook);
        dispatcher.forward(request, response);
    }

    private void insertBook(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        getBookByParametres(request, response);

        bookService.create(getBookByParametres(request,response));
        response.sendRedirect("/crudPanel");
    }

    private Book getBookByParametres(HttpServletRequest request, HttpServletResponse response) {
        Book book = new Book();
        book.setId(Integer.parseInt(request.getParameter("id")));
        book.setName(request.getParameter("name"));
        book.setGenre(request.getParameter("genre"));
        book.setIsbn(request.getParameter("isbn"));
        book.setAuthor(request.getParameter("author"));
        book.setPageCount(Integer.parseInt(request.getParameter("page_count")));
        book.setPublishDate(Integer.parseInt(request.getParameter("publish_year")));
        book.setPublisher(request.getParameter("publisher"));

        return book;
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {

        bookService.update(getBookByParametres(request, response));
        response.sendRedirect("/crudPanel");
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        bookService.delete(id);
        response.sendRedirect("/crudPanel");
    }
}
