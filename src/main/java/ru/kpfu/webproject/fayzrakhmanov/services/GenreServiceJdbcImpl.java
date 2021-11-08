package ru.kpfu.webproject.fayzrakhmanov.services;

import ru.kpfu.webproject.fayzrakhmanov.Exceptions.DataSourceException;
import ru.kpfu.webproject.fayzrakhmanov.entity.Genre;
import ru.kpfu.webproject.fayzrakhmanov.repositories.GenreRepository;

import java.util.List;

public class GenreServiceJdbcImpl implements GenreService {
    private GenreRepository genreRepository;

    public GenreServiceJdbcImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public List<Genre> getGenreList() throws DataSourceException {
        return genreRepository.getGenreList();
    }
}
