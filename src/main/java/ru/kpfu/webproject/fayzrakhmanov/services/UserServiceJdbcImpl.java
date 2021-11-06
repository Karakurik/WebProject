package ru.kpfu.webproject.fayzrakhmanov.services;

import ru.kpfu.webproject.fayzrakhmanov.repositories.UserRepository;

public class UserServiceJdbcImpl implements UserService {
    private UserRepository userRepository;

    public UserServiceJdbcImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
