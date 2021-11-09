package ru.kpfu.webproject.fayzrakhmanov.Exceptions;

public class UnrealPublishDateException extends Exception {
    public UnrealPublishDateException() {
    }

    public UnrealPublishDateException(String message) {
        super(message);
    }

    public UnrealPublishDateException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnrealPublishDateException(Throwable cause) {
        super(cause);
    }

    public UnrealPublishDateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
