package ru.kpfu.webproject.fayzrakhmanov.Exceptions;

public class OccupiedLoginException extends Exception{
    public OccupiedLoginException() {
    }

    public OccupiedLoginException(String message) {
        super(message);
    }

    public OccupiedLoginException(String message, Throwable cause) {
        super(message, cause);
    }

    public OccupiedLoginException(Throwable cause) {
        super(cause);
    }

    public OccupiedLoginException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
