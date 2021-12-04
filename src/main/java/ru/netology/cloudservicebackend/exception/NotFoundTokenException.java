package ru.netology.cloudservicebackend.exception;

public class NotFoundTokenException extends RuntimeException {
    public NotFoundTokenException(String message) {
        super(message);
    }
}
