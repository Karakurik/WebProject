package ru.kpfu.webproject.fayzrakhmanov.servlets;

import ru.kpfu.webproject.fayzrakhmanov.Exceptions.InvalidEmailException;
import ru.kpfu.webproject.fayzrakhmanov.Exceptions.OccupiedLoginException;
import ru.kpfu.webproject.fayzrakhmanov.Exceptions.WeakPasswordException;
import ru.kpfu.webproject.fayzrakhmanov.constants.ServicesConstants;
import ru.kpfu.webproject.fayzrakhmanov.controllers.UserDaService;
import ru.kpfu.webproject.fayzrakhmanov.entity.User;
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
import static ru.kpfu.webproject.fayzrakhmanov.constants.ServletsConstants.*;

@WebServlet(urlPatterns = "/registration")
public class RegistrationServlet extends HttpServlet {
    private ServletContext context;
    private SecurityService securityService;

    @Override
    public void init() throws ServletException {
        context = getServletContext();
        securityService = (SecurityService) context.getAttribute(SECURITY_SERVICE);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String password = request.getParameter(PASSWORD).trim();
        String passwordRepeat = request.getParameter(PASSWORD_REPEAT).trim();
        if (!passwordRepeat.equals(password)) {
            request.setAttribute("message", "Пароли не совпадают");
            request.getRequestDispatcher("/pages/registration.jsp").forward(request, response);
            return;
        }
        String name = request.getParameter(NAME).trim();
        String login = request.getParameter(LOGIN).trim();
        String email = request.getParameter(EMAIL).trim();
        User user = new User(name, login, password, email);

        try {
            securityService.registration(user, password, request.getSession());

            Cookie c = new Cookie(SecurityService.AUTH_COOKIE_NAME, login);
            c.setMaxAge(60*60*24*365);
            response.addCookie(c);

            response.sendRedirect(context.getContextPath() + "/123");
        } catch (InvalidEmailException e){
            request.setAttribute("message", "Неверный email");
        } catch (OccupiedLoginException e){
            request.setAttribute("message", "Email уже зарегистрирован");
        } catch (WeakPasswordException e){
            request.setAttribute("message", "Пароль слишком короткий(мин. 6 символов)");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        context.getRequestDispatcher("/pages/registration.jsp").forward(request, response);
    }
}
