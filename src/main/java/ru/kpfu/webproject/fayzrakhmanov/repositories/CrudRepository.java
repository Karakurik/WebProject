package ru.kpfu.webproject.fayzrakhmanov.repositories;

import ru.kpfu.webproject.fayzrakhmanov.Exceptions.CreateBookFailedException;
import ru.kpfu.webproject.fayzrakhmanov.Exceptions.DeleteBookFailedException;
import ru.kpfu.webproject.fayzrakhmanov.Exceptions.UpdateBookFailedException;

import java.util.List;

public interface CrudRepository<T> {
    void create(T entity) throws CreateBookFailedException;
    void update(T entity) throws UpdateBookFailedException;
    void delete(T entity) throws DeleteBookFailedException;
    void delete(int id) throws DeleteBookFailedException;
}
