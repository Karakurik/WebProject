package ru.kpfu.webproject.fayzrakhmanov.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import ru.kpfu.webproject.fayzrakhmanov.Exceptions.*;
import ru.kpfu.webproject.fayzrakhmanov.entity.User;
import ru.kpfu.webproject.fayzrakhmanov.repositories.UserRepository;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
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
    public void registration(User user, String rawPassword, HttpSession session) {
        validateUser(user);
        if(rawPassword.length() < 6) throw new WeakPasswordException("Password too short");
        String hash = passwordEncoder.encode(rawPassword);
        user.setPasswordHash(hash);
        userRepository.create(user);
        session.setAttribute(USER, user);
    }

    @Override
    public void authorize(String login, String rawPassword, HttpSession session) {
        User user = userRepository.getByLogin(login);
        if(user == null) throw new NoSuchLoginException("No email " + login);
        if(passwordEncoder.matches(rawPassword, user.getPasswordHash())){
            session.setAttribute(USER, user);
        }
        else throw new WrongPasswordException();
    }

    @Override
    public boolean isAuthenticated(HttpServletRequest request, HttpSession session) {
        if(session.getAttribute("user") != null){
            return true;
        }
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for (Cookie c : cookies){
                if(c.getName().equals(AUTH_COOKIE_NAME)){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void validateUser(User user) {
        Matcher m = Pattern.compile(EMAIL_REGEXP).matcher(user.getEmail());
        if(!m.matches()){
            throw new InvalidEmailException(user.getEmail() + " is not email");
        }
        if(userRepository.getByLogin(user.getEmail()) != null){
            throw new OccupiedLoginException(user.getEmail() + " is occupied");
        }
    }

    @Override
    public void logout(HttpServletRequest req, HttpSession session) {
        session.removeAttribute(USER);
    }
}
