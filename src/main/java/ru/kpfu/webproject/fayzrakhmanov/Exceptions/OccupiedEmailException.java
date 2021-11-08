package ru.kpfu.webproject.fayzrakhmanov.Exceptions;

public class OccupiedEmailException extends Exception {
    public OccupiedEmailException() {
    }

    public OccupiedEmailException(String message) {
        super(message);
    }

    public OccupiedEmailException(String message, Throwable cause) {
        super(message, cause);
    }

    public OccupiedEmailException(Throwable cause) {
        super(cause);
    }

    public OccupiedEmailException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
