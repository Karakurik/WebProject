package ru.kpfu.webproject.fayzrakhmanov.repositories;

import ru.kpfu.webproject.fayzrakhmanov.entity.User;

import java.util.List;
import java.util.UUID;

public interface UserRepository {
    User getByLogin(String login);

    void create(User user);
}
