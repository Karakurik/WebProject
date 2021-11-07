package ru.kpfu.webproject.fayzrakhmanov.repositories;

import java.util.List;

public interface CrudRepository<T> {
    String create(T entity);
    void update(T entity);
    void delete(T entity);
    void delete(int id);
}
