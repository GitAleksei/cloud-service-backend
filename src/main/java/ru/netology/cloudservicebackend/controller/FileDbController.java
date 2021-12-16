package ru.netology.cloudservicebackend.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.netology.cloudservicebackend.model.MsgAnswerFileList;
import ru.netology.cloudservicebackend.service.FileDbService;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RestController
@Slf4j
public class FileDbController {

    private final FileDbService fileDbService;

    public FileDbController(FileDbService fileDbService) {
        this.fileDbService = fileDbService;
    }

    @PostMapping("/file")
    public void postFile(@RequestParam("file") MultipartFile file) throws IOException {
        log.info(file.getOriginalFilename() + " - " + file.getContentType());
        log.info(file.getSize() + "");
        fileDbService.save(file);
    }

    @DeleteMapping("/file")
    public void deleteFile(@RequestParam("filename") String filename) throws FileNotFoundException {
        log.info("Delete file: " + filename);
        fileDbService.deleteFileByFilename(filename);
    }

    @GetMapping("/list")
    public List<MsgAnswerFileList> getFileList(@RequestParam("limit") int limit) {
        return fileDbService.getFileList(limit);
    }
}
