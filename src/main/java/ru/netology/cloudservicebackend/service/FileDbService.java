package ru.netology.cloudservicebackend.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.netology.cloudservicebackend.model.MsgAnswerFileList;
import ru.netology.cloudservicebackend.model.entity.FileDb;
import ru.netology.cloudservicebackend.repository.FileDbRepository;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
        fileDb.setSize(file.getSize());
        return fileDbRepository.save(fileDb);
    }

    public FileDb getFileByFilename(String filename) throws FileNotFoundException {
        return fileDbRepository.findByFilename(filename)
                .orElseThrow(() -> new FileNotFoundException("File not found: " + filename));
    }

    public List<MsgAnswerFileList> getFileList(int limit) {
        var pageRequest = PageRequest.of(0, limit, Sort.by("id").descending());

        return fileDbRepository.findAll(pageRequest).stream()
                .map(file -> new MsgAnswerFileList(file.getFilename(), file.getSize()))
                .collect(Collectors.toList());
    }
}
