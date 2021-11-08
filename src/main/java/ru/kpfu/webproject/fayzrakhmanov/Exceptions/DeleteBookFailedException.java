package ru.kpfu.webproject.fayzrakhmanov.Exceptions;

public class DeleteBookFailedException extends Exception {
    public DeleteBookFailedException() {
    }

    public DeleteBookFailedException(String message) {
        super(message);
    }

    public DeleteBookFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public DeleteBookFailedException(Throwable cause) {
        super(cause);
    }

    public DeleteBookFailedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
