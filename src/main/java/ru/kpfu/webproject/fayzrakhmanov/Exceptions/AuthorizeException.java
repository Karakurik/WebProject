package ru.kpfu.webproject.fayzrakhmanov.Exceptions;

public class AuthorizeException extends Exception {
    public AuthorizeException() {
    }

    public AuthorizeException(String message) {
        super(message);
    }

    public AuthorizeException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthorizeException(Throwable cause) {
        super(cause);
    }

    public AuthorizeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
