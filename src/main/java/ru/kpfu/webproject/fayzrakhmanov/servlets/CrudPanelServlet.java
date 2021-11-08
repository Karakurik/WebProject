package ru.kpfu.webproject.fayzrakhmanov.servlets;

import ru.kpfu.webproject.fayzrakhmanov.Exceptions.CreateBookFailedException;
import ru.kpfu.webproject.fayzrakhmanov.Exceptions.DeleteBookFailedException;
import ru.kpfu.webproject.fayzrakhmanov.Exceptions.FileUploadException;
import ru.kpfu.webproject.fayzrakhmanov.Exceptions.UpdateBookFailedException;
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
import javax.servlet.http.Part;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/crudPanel/*")
public class CrudPanelServlet extends HttpServlet {
    private ServletContext context;
    private BookService bookService;
    private Part part = null;

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
                    deleteBook(request, response);
                    break;
                case "/crudPanel/edit":
                    showEditForm(request, response);
                    break;
                case "/crudPanel/update":
                    updateBook(request, response);
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
        try {
//             TODO: 09.11.2021
//            bookService.uploadFile(part.getSubmittedFileName(), part.getInputStream());
            bookService.create(getBookByParametres(request,response));

            response.sendRedirect("/crudPanel");
        } catch (CreateBookFailedException | FileUploadException e) {
            // TODO: 08.11.2021
        }
    }

    private Book getBookByParametres(HttpServletRequest request, HttpServletResponse response) throws FileUploadException {
//        try {
//            part = request.getPart("file");
//        } catch (ServletException | IOException e) {
//            throw new FileUploadException(e);
//        }
        Book book = new Book();
        book.setName(request.getParameter("name"));
//        book.setContentFileName(part.getSubmittedFileName());
        book.setPageCount(Integer.parseInt(request.getParameter("page_count")));
        book.setIsbn(request.getParameter("isbn"));
        book.setGenre(request.getParameter("genre"));
        book.setAuthor(request.getParameter("author"));
        book.setPublishDate(Integer.parseInt(request.getParameter("publish_date")));
        book.setPublisher(request.getParameter("publisher"));

        return book;
    }

    private void updateBook(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        try {
            Book book = getBookByParametres(request, response);
            book.setId(Integer.parseInt(request.getParameter("id")));
            bookService.update(book);
            response.sendRedirect("/crudPanel");
        } catch (FileUploadException | UpdateBookFailedException e) {
//             TODO: 08.11.2021
        }
    }

    private void deleteBook(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            bookService.delete(id);
            response.sendRedirect("/crudPanel");
        } catch (DeleteBookFailedException e) {
//             TODO: 08.11.2021
        }
    }
}
