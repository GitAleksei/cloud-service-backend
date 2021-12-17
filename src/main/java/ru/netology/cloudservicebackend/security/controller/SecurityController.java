package ru.netology.cloudservicebackend.security.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.cloudservicebackend.model.MsgAnswerException;
import ru.netology.cloudservicebackend.model.MsgAnswerToken;
import ru.netology.cloudservicebackend.model.MsgLoginPassword;
import ru.netology.cloudservicebackend.security.service.SecurityService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@Slf4j
@Validated
public class SecurityController {
    private final SecurityService securityService;

    public SecurityController(SecurityService securityService) {
        this.securityService = securityService;
    }

    @PostMapping("/login")
    public MsgAnswerToken postLogin(@Valid @RequestBody MsgLoginPassword msgLoginPassword) {
        Authentication authentication = securityService.attemptAuthentication(msgLoginPassword);
        if (!authentication.isAuthenticated()) {
            throw new AuthenticationCredentialsNotFoundException("Not found couple login/password");
        }
        return securityService.successfulAuthentication(authentication);
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request, response, null);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<MsgAnswerException> handlerHMNRE(HttpMessageNotReadableException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new MsgAnswerException(e.getMessage()));
    }
}
