package ru.kpfu.webproject.fayzrakhmanov.servlets;

import ru.kpfu.webproject.fayzrakhmanov.entity.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/ShowImageServlet")
public class ShowImageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        response.setContentType("image/jpeg");
        try (OutputStream out = response.getOutputStream()){
            int index = Integer.parseInt(request.getParameter("index"));

            List<Book> list = (List<Book>)request.getSession(false).getAttribute("currentBookList");
            Book book = list.get(index);
            if (book.getImage() != null) {
                response.setContentLength(book.getImage().length);
                out.write(book.getImage());
            }
        } catch (IOException ignored) {
        }
    }
}
