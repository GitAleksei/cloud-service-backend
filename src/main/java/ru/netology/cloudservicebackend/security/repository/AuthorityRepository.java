package ru.netology.cloudservicebackend.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.netology.cloudservicebackend.security.entity.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    Authority findByName(String name);
}
