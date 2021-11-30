package ru.netology.cloudservicebackend.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.cloudservicebackend.model.MsgAnswerToken;
import ru.netology.cloudservicebackend.model.MsgLoginPassword;
import ru.netology.cloudservicebackend.service.SecurityService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

    @PostMapping("/logout")
    public void getLogout(HttpServletRequest request, HttpServletResponse response) {
        log.info("User {} is logout",
                SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        SecurityContextLogoutHandler securityContextLogoutHandler =
                new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request, response, null);
    }

}
