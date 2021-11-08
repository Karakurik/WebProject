package ru.kpfu.webproject.fayzrakhmanov.repositories;

import ru.kpfu.webproject.fayzrakhmanov.Exceptions.DataSourceException;
import ru.kpfu.webproject.fayzrakhmanov.entity.User;

import java.util.List;
import java.util.UUID;

public interface UserRepository {
    User getByLogin(String login) throws DataSourceException;
    User getByEmail(String email) throws DataSourceException;

    void create(User user) throws DataSourceException;
}
