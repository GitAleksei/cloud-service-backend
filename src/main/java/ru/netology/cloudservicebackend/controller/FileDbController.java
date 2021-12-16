package ru.netology.cloudservicebackend.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.netology.cloudservicebackend.service.FileDbService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;

@RestController
@Slf4j
@RequestMapping("/file")
public class FileDbController {

    private final FileDbService fileDbService;

    public FileDbController(FileDbService fileDbService) {
        this.fileDbService = fileDbService;
    }

    @PostMapping()
    public void postFile(@RequestParam("filename") MultipartFile file) throws IOException {
        log.info(file.getOriginalFilename() + " - " + file.getContentType());
        log.info(file.getSize() + "");
        fileDbService.save(file);
    }
}
