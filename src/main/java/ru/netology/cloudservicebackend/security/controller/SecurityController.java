package ru.netology.cloudservicebackend.security.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.cloudservicebackend.model.MsgAnswerException;
import ru.netology.cloudservicebackend.model.MsgAnswerToken;
import ru.netology.cloudservicebackend.model.MsgLoginPassword;
import ru.netology.cloudservicebackend.security.service.SecurityService;

@RestController
@Slf4j
public class SecurityController {
    private final SecurityService securityService;

    public SecurityController(SecurityService securityService) {
        this.securityService = securityService;
    }

    @PostMapping("/login")
    public MsgAnswerToken postLogin(@RequestBody MsgLoginPassword msgLoginPassword) {
        Authentication authentication = securityService.attemptAuthentication(msgLoginPassword);
        if (!authentication.isAuthenticated()) {
            throw new AuthenticationCredentialsNotFoundException("Not found couple login/password");
        }
        return securityService.successfulAuthentication(authentication);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<MsgAnswerException> handlerHMNRE(HttpMessageNotReadableException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new MsgAnswerException(e.getMessage()));
    }
}
