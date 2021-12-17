package ru.netology.cloudservicebackend.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.netology.cloudservicebackend.model.MsgAnswerFileList;
import ru.netology.cloudservicebackend.model.MsgFilename;
import ru.netology.cloudservicebackend.model.entity.FileDb;
import ru.netology.cloudservicebackend.service.FileDbService;

import java.io.FileNotFoundException;
import java.io.IOException;
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
        fileDbService.save(file);
        log.info("Post file: " + file.getOriginalFilename());
    }

    @DeleteMapping("/file")
    public void deleteFile(@RequestParam("filename") String filename) throws FileNotFoundException {
        fileDbService.deleteFileByFilename(filename);
        log.info("Delete file: " + filename);
    }

    @GetMapping("/file")
    public ResponseEntity<Resource> getFile(@RequestParam("filename") String filename)
            throws IOException {
        FileDb fileDb = fileDbService.getFileByFilename(filename);
        log.info("Get file: " + filename);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(fileDb.getType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=" + fileDb.getFilename())
                .body(new ByteArrayResource(fileDb.getData()));
    }

    @PutMapping("/file")
    public void renameFile(@RequestParam("filename") String filename,
                        @RequestBody MsgFilename msgFilename)
            throws FileNotFoundException {
        fileDbService.renameFile(filename, msgFilename.getFilename());
        log.info("Rename file: " + filename + " to " + msgFilename.getFilename());
    }

    @GetMapping("/list")
    public List<MsgAnswerFileList> getFileList(@RequestParam("limit") int limit) {
        log.info("Get file list");
        return fileDbService.getFileList(limit);
    }
}
