package ru.netology.cloudservicebackend.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FileDbServiceTest {


    @Autowired
    FileDbService fileDbService;

    @Test
    void saveNpeTest() {
        Assertions.assertThrows(NullPointerException.class,
                () -> fileDbService.save(null));
    }
}