package ru.kpfu.webproject.fayzrakhmanov.repositories;

import ru.kpfu.webproject.fayzrakhmanov.entity.Genre;

import java.util.List;

public interface GenreRepository {
    List<Genre> getGenreList();
}
