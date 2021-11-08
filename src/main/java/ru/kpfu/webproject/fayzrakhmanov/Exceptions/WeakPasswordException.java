package ru.kpfu.webproject.fayzrakhmanov.Exceptions;

public class WeakPasswordException extends Exception {
    public WeakPasswordException() {
    }

    public WeakPasswordException(String message) {
        super(message);
    }

    public WeakPasswordException(String message, Throwable cause) {
        super(message, cause);
    }

    public WeakPasswordException(Throwable cause) {
        super(cause);
    }

    public WeakPasswordException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
