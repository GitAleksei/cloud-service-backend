package ru.netology.cloudservicebackend.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;

@Controller
public class ApiErrorController implements ErrorController {

    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping("/error")
    public void globalError(HttpServletResponse response) {
        throw new ResponseStatusException(HttpStatus.valueOf(response.getStatus()));
    }
}
