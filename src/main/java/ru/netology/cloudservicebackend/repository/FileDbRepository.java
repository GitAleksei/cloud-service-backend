package ru.netology.cloudservicebackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.netology.cloudservicebackend.model.entity.FileDb;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface FileDbRepository extends JpaRepository<FileDb, String> {

    Optional<FileDb> findByFilename(String filename);

    @Transactional
    Optional<FileDb> deleteByFilename(String filename);
}
