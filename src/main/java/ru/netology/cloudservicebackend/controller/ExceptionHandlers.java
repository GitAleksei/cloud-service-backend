package ru.netology.cloudservicebackend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;
import ru.netology.cloudservicebackend.model.MsgAnswerException;

import javax.naming.AuthenticationException;
import java.util.concurrent.atomic.AtomicInteger;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@RestControllerAdvice
public class ExceptionHandlers {
    private final AtomicInteger exceptionId = new AtomicInteger();

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<MsgAnswerException> handlerAE(AuthenticationException e) {
        return ResponseEntity.status(UNAUTHORIZED)
                .body(new MsgAnswerException(e.getMessage(), exceptionId.incrementAndGet()));
    }

//    @ExceptionHandler(ResponseStatusException.class)
//    public ResponseEntity<MsgAnswerException> handlerRSE(ResponseStatusException e) {
//        return ResponseEntity.status(e.getStatus())
//                .body(new MsgAnswerException(e.getMessage(), exceptionId.incrementAndGet()));
//    }
}
