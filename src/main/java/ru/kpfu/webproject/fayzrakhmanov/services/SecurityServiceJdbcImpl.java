package ru.kpfu.webproject.fayzrakhmanov.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import ru.kpfu.webproject.fayzrakhmanov.Exceptions.*;
import ru.kpfu.webproject.fayzrakhmanov.entity.User;
import ru.kpfu.webproject.fayzrakhmanov.repositories.UserRepository;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static ru.kpfu.webproject.fayzrakhmanov.constants.ServletsConstants.USER;


public class SecurityServiceJdbcImpl implements SecurityService {

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

//    private Logger logger;    

    public SecurityServiceJdbcImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
//        logger = LoggerFactory.getLogger("Security Service");
    }

    @Override
    public void registration(User user, String rawPassword, HttpSession session) throws RegistrationException, OccupiedLoginException, WeakPasswordException, InvalidEmailException, OccupiedEmailException {
        try {
            validateUser(user);
            if (rawPassword.length() < 5) throw new WeakPasswordException("Password too short");
            String hash = passwordEncoder.encode(rawPassword);
            user.setPasswordHash(hash);
            userRepository.create(user);
        } catch (DataSourceException e) {
            throw new RegistrationException(e);
        }
        session.setAttribute(USER, user);
    }

    @Override
    public String authorize(String login, String rawPassword, HttpSession session) throws AuthorizeException, NoSuchLoginException, WrongPasswordException {
        try {
            User user = userRepository.getByLogin(login);
            if (user == null) user = userRepository.getByEmail(login);
            if (user == null) throw new NoSuchLoginException("No email " + login);
            if (passwordEncoder.matches(rawPassword, user.getPasswordHash())) {
                session.setAttribute(USER, user);
            } else throw new WrongPasswordException();
            return user.getLogin();
        } catch (DataSourceException e) {
            throw new AuthorizeException(e);
        }
    }

    @Override
    public boolean isAuthenticated(HttpServletRequest request, HttpSession session) throws AutentificatedException {
        if (session.getAttribute("user") != null) {
            return true;
        }
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals(AUTH_COOKIE_NAME)) {
                    if (!c.getValue().equals("null")) {
                        User user = null;
                        try {
                            user = userRepository.getByLogin(c.getValue());
                        } catch (DataSourceException e) {
                            throw new AutentificatedException(e);
                        }
                        if (user != null) {
                            session.setAttribute(USER, user);
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override
    public void validateUser(User user) throws RegistrationException, OccupiedLoginException, InvalidEmailException, OccupiedEmailException {
        Matcher m = Pattern.compile(EMAIL_REGEXP).matcher(user.getEmail());
        if (!m.matches()) {
            throw new InvalidEmailException(user.getEmail() + " is not email");
        }
        try {
            if (userRepository.getByLogin(user.getLogin()) != null) {
                throw new OccupiedLoginException(user.getLogin() + " is occupied");
            }
            if (userRepository.getByEmail(user.getEmail()) != null) {
                throw new OccupiedEmailException(user.getEmail() + " is occupied");
            }
        } catch (DataSourceException e) {
            throw new RegistrationException(e);
        }
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        session.removeAttribute(USER);
        Cookie[] cookies = request.getCookies();
        for (int i = 0; i < cookies.length; i++) {
            if (cookies[i].getName().equals(AUTH_COOKIE_NAME)) {
                cookies[i] = new Cookie(AUTH_COOKIE_NAME, "null");
                cookies[i].setMaxAge(1);
                response.addCookie(cookies[i]);
                break;
            }
        }
    }

    @Override
    public boolean isAdmin(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals(AUTH_COOKIE_NAME)) {
                    if (c.getValue().equals("admin")) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
