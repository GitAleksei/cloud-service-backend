package ru.netology.cloudservisebackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.netology.cloudservisebackend.entity.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    Authority findByName(String name);
}