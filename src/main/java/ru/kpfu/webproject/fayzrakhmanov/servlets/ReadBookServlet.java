package ru.kpfu.webproject.fayzrakhmanov.servlets;

import ru.kpfu.webproject.fayzrakhmanov.Exceptions.FileDownloadException;
import ru.kpfu.webproject.fayzrakhmanov.services.BookService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

import static ru.kpfu.webproject.fayzrakhmanov.constants.ServicesConstants.BOOK_SERVICE;

@WebServlet("/readBook")
public class ReadBookServlet extends HttpServlet {
    private ServletContext context;
    private BookService bookService;

    @Override
    public void init() throws ServletException {
        context = getServletContext();
        bookService = (BookService) context.getAttribute(BOOK_SERVICE);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/pdf");
        try (OutputStream out = response.getOutputStream()){
            long bookId = Long.parseLong(request.getParameter("id"));
            String contentFileName = bookService.getBookContentFileNameById(bookId);
            response.setHeader("Content-Disposition", "filename=\"" + contentFileName + "\"");
            bookService.downloadFile(contentFileName, out);
        } catch (IOException | FileDownloadException e) {
            response.setContentType("text/html");
            request.setAttribute("message", "Не удалось открыть файл");
            request.getRequestDispatcher("/pages/readBook.jsp").forward(request, response);
        }
    }
}
