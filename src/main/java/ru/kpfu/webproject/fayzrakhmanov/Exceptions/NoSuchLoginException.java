package ru.kpfu.webproject.fayzrakhmanov.Exceptions;

public class NoSuchLoginException extends RuntimeException{
    public NoSuchLoginException() {
    }

    public NoSuchLoginException(String message) {
        super(message);
    }

    public NoSuchLoginException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchLoginException(Throwable cause) {
        super(cause);
    }
}
