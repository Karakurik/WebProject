package ru.kpfu.webproject.fayzrakhmanov.repositories;

import ru.kpfu.webproject.fayzrakhmanov.entity.User;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends CrudRepository<User> {
    User getByLogin(String login);
}
