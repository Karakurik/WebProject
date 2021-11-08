package ru.kpfu.webproject.fayzrakhmanov.Exceptions;

public class AutentificatedException extends Exception {
    public AutentificatedException() {
    }

    public AutentificatedException(String message) {
        super(message);
    }

    public AutentificatedException(String message, Throwable cause) {
        super(message, cause);
    }

    public AutentificatedException(Throwable cause) {
        super(cause);
    }

    public AutentificatedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
