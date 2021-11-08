package ru.kpfu.webproject.fayzrakhmanov.services;

import ru.kpfu.webproject.fayzrakhmanov.Exceptions.DataSourceException;
import ru.kpfu.webproject.fayzrakhmanov.entity.Author;

import java.util.List;

public interface AuthorService {
    List<Author> getAuthorList() throws DataSourceException;
}
