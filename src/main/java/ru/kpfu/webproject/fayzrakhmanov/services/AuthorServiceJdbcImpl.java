package ru.kpfu.webproject.fayzrakhmanov.services;

import ru.kpfu.webproject.fayzrakhmanov.entity.Author;
import ru.kpfu.webproject.fayzrakhmanov.repositories.AuthorRepository;

import java.util.List;

public class AuthorServiceJdbcImpl implements AuthorService {
    private AuthorRepository authorRepository;

    public AuthorServiceJdbcImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> getAuthorList() {
        return authorRepository.getAuthorList();
    }
}
