package ru.netology.cloudservicebackend.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class SecurityController {

    @PostMapping("/login")
    public HttpServletResponse postLogin(HttpServletResponse response) {
        return response;
    }
}
