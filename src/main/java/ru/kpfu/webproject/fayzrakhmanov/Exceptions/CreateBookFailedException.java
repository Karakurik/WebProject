package ru.kpfu.webproject.fayzrakhmanov.Exceptions;

public class CreateBookFailedException extends Exception {
    public CreateBookFailedException() {
    }

    public CreateBookFailedException(String message) {
        super(message);
    }

    public CreateBookFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public CreateBookFailedException(Throwable cause) {
        super(cause);
    }

    public CreateBookFailedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
