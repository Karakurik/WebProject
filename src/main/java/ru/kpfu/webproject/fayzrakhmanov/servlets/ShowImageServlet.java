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

@WebServlet("/ShowImageServlet")
public class ShowImageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("image/jpeg");
        OutputStream out = response.getOutputStream();
        try {
            int index = Integer.valueOf(request.getParameter("index"));

            ArrayList<Book> list = (ArrayList<Book>)request.getSession(false).getAttribute("currentBookList");
            Book book = list.get(index);
            response.setContentLength(book.getImage().length);
            out.write(book.getImage());
        } finally {
            out.close();
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
