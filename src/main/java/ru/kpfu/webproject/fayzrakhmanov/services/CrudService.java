package ru.kpfu.webproject.fayzrakhmanov.services;

import ru.kpfu.webproject.fayzrakhmanov.Exceptions.*;

public interface CrudService<T> {
    void create(T entity) throws CreateBookFailedException, IsbnAlreadyExistsException, UnrealPublishDateException;

    void update(T entity) throws UpdateBookFailedException, IsbnAlreadyExistsException, UnrealPublishDateException;

    void delete(T entity) throws DeleteBookFailedException;

    void delete(int id) throws DeleteBookFailedException;
}