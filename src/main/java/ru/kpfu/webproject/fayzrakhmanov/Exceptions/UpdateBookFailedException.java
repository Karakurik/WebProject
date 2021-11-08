package ru.kpfu.webproject.fayzrakhmanov.Exceptions;

public class UpdateBookFailedException extends Exception {
    public UpdateBookFailedException() {
    }

    public UpdateBookFailedException(String message) {
        super(message);
    }

    public UpdateBookFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UpdateBookFailedException(Throwable cause) {
        super(cause);
    }

    public UpdateBookFailedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
