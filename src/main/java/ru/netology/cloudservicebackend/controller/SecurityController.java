package ru.netology.cloudservicebackend.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {

    @PostMapping("/login")
    public void postLogin() {
    }
}
