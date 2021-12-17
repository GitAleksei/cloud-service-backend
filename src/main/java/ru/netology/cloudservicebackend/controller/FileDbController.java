package ru.netology.cloudservicebackend.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import ru.netology.cloudservicebackend.model.MsgAnswerFile;
import ru.netology.cloudservicebackend.model.MsgAnswerFileList;
import ru.netology.cloudservicebackend.model.entity.FileDb;
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
        log.info("Attempt post file: " + file.getOriginalFilename());
        fileDbService.save(file);
        log.info("Post file: " + file.getOriginalFilename());
    }

    @DeleteMapping("/file")
    public void deleteFile(@RequestParam("filename") String filename) throws FileNotFoundException {
        log.info("Attempt delete file: " + filename);
        fileDbService.deleteFileByFilename(filename);
        log.info("Delete file: " + filename);
    }

    @GetMapping("/file")
    public ResponseEntity<Resource> getFile(@RequestParam("filename") String filename)
            throws IOException {
        log.info("Attempt get file: " + filename);
        FileDb fileDb = fileDbService.getFileByFilename(filename);
        log.info("Get file: " + filename);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(fileDb.getType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=" + fileDb.getFilename())
                .body(new ByteArrayResource(fileDb.getData()));
    }

    @GetMapping("/list")
    public List<MsgAnswerFileList> getFileList(@RequestParam("limit") int limit) {
        return fileDbService.getFileList(limit);
    }
}
