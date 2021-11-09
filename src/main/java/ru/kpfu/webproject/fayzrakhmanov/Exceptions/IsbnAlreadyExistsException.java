package ru.kpfu.webproject.fayzrakhmanov.Exceptions;

public class IsbnAlreadyExistsException extends Exception {
    public IsbnAlreadyExistsException() {
    }

    public IsbnAlreadyExistsException(String message) {
        super(message);
    }

    public IsbnAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public IsbnAlreadyExistsException(Throwable cause) {
        super(cause);
    }

    public IsbnAlreadyExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
