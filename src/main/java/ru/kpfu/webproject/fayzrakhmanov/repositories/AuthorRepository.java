package ru.kpfu.webproject.fayzrakhmanov.repositories;

import ru.kpfu.webproject.fayzrakhmanov.entity.Author;

import java.util.List;

public interface AuthorRepository {
    List<Author> getAuthorList();
}
