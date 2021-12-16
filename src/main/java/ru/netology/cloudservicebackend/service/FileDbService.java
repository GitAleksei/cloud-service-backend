package ru.netology.cloudservicebackend.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.netology.cloudservicebackend.model.entity.FileDb;
import ru.netology.cloudservicebackend.repository.FileDbRepository;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class FileDbService {

    private final FileDbRepository fileDbRepository;

    public FileDbService(FileDbRepository fileDbRepository) {
        this.fileDbRepository = fileDbRepository;
    }

    public FileDb save(MultipartFile file) throws IOException {
        FileDb fileDb = new FileDb();
        fileDb.setFilename(file.getOriginalFilename());
        fileDb.setType(file.getContentType());
        fileDb.setData(file.getBytes());
        fileDb.setId(12L);
        return fileDbRepository.save(fileDb);
    }

    public FileDb getFileByFilename(String filename) throws FileNotFoundException {
        return fileDbRepository.findByFilename(filename)
                .orElseThrow(() -> new FileNotFoundException("File not found: " + filename));
    }

    public List<FileDb> getFileList() {
        return fileDbRepository.findAll();
    }
}
