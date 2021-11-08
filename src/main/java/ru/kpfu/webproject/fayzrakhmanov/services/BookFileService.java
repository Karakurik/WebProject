package ru.kpfu.webproject.fayzrakhmanov.services;

import java.io.OutputStream;

public interface BookFileService {
    void downloadFile(String fileName, OutputStream responseOutputStream);
}
