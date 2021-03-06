package ru.kpfu.webproject.fayzrakhmanov.services;

import ru.kpfu.webproject.fayzrakhmanov.Exceptions.*;
import ru.kpfu.webproject.fayzrakhmanov.constants.CookieConstants;
import ru.kpfu.webproject.fayzrakhmanov.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public interface SecurityService {

    String EMAIL_REGEXP = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";

    String AUTH_COOKIE_NAME = CookieConstants.USER_LOGIN;

    void registration(User user, String password, HttpSession session) throws RegistrationException, OccupiedLoginException, WeakPasswordException, InvalidEmailException, OccupiedEmailException;

    String authorize(String login, String password, HttpSession session) throws AuthorizeException, NoSuchLoginException, WrongPasswordException;

    boolean isAuthenticated(HttpServletRequest request, HttpSession session) throws AutentificatedException;

    void validateUser(User user) throws RegistrationException, OccupiedLoginException, InvalidEmailException, OccupiedEmailException;

    void logout(HttpServletRequest request, HttpServletResponse response, HttpSession session);

    boolean isAdmin(HttpServletRequest request);
}
