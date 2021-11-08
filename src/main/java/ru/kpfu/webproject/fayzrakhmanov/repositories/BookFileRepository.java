package ru.kpfu.webproject.fayzrakhmanov.repositories;

import java.io.OutputStream;

public interface BookFileRepository {
    void downloadFile(String fileName, OutputStream responseOutputStream);
}
