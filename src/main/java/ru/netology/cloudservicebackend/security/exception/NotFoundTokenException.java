package ru.netology.cloudservicebackend.security.exception;

public class NotFoundTokenException extends RuntimeException {
    public NotFoundTokenException(String message) {
        super(message);
    }
}
