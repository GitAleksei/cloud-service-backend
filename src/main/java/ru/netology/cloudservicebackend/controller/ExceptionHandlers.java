package ru.netology.cloudservicebackend.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.netology.cloudservicebackend.model.MsgAnswerException;

import java.io.FileNotFoundException;
import java.io.IOException;

@RestControllerAdvice
@Slf4j
public class ExceptionHandlers {

    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<MsgAnswerException> handlerAE(FileNotFoundException e) {
        log.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new MsgAnswerException(e.getMessage()));
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<MsgAnswerException> handlerAE(IOException e) {
        log.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new MsgAnswerException(e.getMessage()));
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<MsgAnswerException> handlerAE(MissingServletRequestParameterException e) {
        log.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new MsgAnswerException(e.getMessage()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<MsgAnswerException> handlerAE(IllegalArgumentException e) {
        log.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new MsgAnswerException(e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MsgAnswerException> handlerCVE(MethodArgumentNotValidException e) {
        log.error("The form is filled out incorrectly");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new MsgAnswerException("The form is filled out incorrectly"));
    }
}
