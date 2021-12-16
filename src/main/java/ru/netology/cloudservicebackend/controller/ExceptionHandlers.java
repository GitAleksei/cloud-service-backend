package ru.netology.cloudservicebackend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.netology.cloudservicebackend.model.MsgAnswerException;

import javax.naming.AuthenticationException;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@RestControllerAdvice
public class ExceptionHandlers {

//    @ExceptionHandler(AuthenticationException.class)
//    public ResponseEntity<MsgAnswerException> handlerAE(AuthenticationException e) {
//        return ResponseEntity.status(UNAUTHORIZED)
//                .body(new MsgAnswerException(e.getMessage()));
//    }
}
