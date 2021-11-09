package ru.kpfu.webproject.fayzrakhmanov.repositories;

import ru.kpfu.webproject.fayzrakhmanov.Exceptions.DataSourceException;
import ru.kpfu.webproject.fayzrakhmanov.entity.User;

public interface UserRepository {
    User getByLogin(String login) throws DataSourceException;

    User getByEmail(String email) throws DataSourceException;

    void create(User user) throws DataSourceException;
}
