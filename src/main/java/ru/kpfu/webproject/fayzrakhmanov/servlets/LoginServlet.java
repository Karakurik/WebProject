package ru.kpfu.webproject.fayzrakhmanov.servlets;

import ru.kpfu.webproject.fayzrakhmanov.Exceptions.NoSuchLoginException;
import ru.kpfu.webproject.fayzrakhmanov.Exceptions.WrongPasswordException;
import ru.kpfu.webproject.fayzrakhmanov.services.SecurityService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ru.kpfu.webproject.fayzrakhmanov.constants.ServicesConstants.SECURITY_SERVICE;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private ServletContext context;
    private SecurityService securityService;

    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";

    @Override
    public void init() throws ServletException {
        context = getServletContext();
        securityService = (SecurityService) context.getAttribute(SECURITY_SERVICE);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);

        try {
            securityService.authorize(login, password, request.getSession());

            Cookie c = new Cookie(SecurityService.AUTH_COOKIE_NAME, login);
            c.setMaxAge(60*60*24*365);
            response.addCookie(c);

            response.sendRedirect(context.getContextPath() + "/books");
        } catch (NoSuchLoginException e) {
            request.setAttribute("message", "Пользователь не найден");
        } catch (WrongPasswordException e) {
            request.setAttribute("message", "Догин или пароль неверный");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (securityService.isAuthenticated(request, request.getSession())) {
            securityService.logout(request, response, request.getSession());
        }
        context.getRequestDispatcher("/pages/login.jsp").forward(request, response);
    }
}
