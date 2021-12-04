package ru.netology.cloudservicebackend.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
public class FileController {

    @PostMapping("/file")
    public void postFile(HttpServletRequest request) {
        log.info(request.getHeader("filename"));
    }
}
