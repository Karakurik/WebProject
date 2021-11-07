package ru.kpfu.webproject.fayzrakhmanov.services;

public interface CrudService<T> {
    String create(T entity);
    void update(T entity);
    void delete(T entity);
    void delete(int id);
}