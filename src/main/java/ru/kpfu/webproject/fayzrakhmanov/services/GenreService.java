package ru.kpfu.webproject.fayzrakhmanov.services;

import ru.kpfu.webproject.fayzrakhmanov.Exceptions.DataSourceException;
import ru.kpfu.webproject.fayzrakhmanov.entity.Genre;

import java.util.List;

public interface GenreService {
    List<Genre> getGenreList() throws DataSourceException;
}
