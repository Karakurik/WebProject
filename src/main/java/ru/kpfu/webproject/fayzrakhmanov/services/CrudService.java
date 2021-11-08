package ru.kpfu.webproject.fayzrakhmanov.services;

import ru.kpfu.webproject.fayzrakhmanov.Exceptions.CreateBookFailedException;
import ru.kpfu.webproject.fayzrakhmanov.Exceptions.DeleteBookFailedException;
import ru.kpfu.webproject.fayzrakhmanov.Exceptions.UpdateBookFailedException;

public interface CrudService<T> {
    void create(T entity) throws CreateBookFailedException;
    void update(T entity) throws UpdateBookFailedException;
    void delete(T entity) throws DeleteBookFailedException;
    void delete(int id) throws DeleteBookFailedException;
}