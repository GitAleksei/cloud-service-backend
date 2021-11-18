package ru.netology.cloudservisebackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.netology.cloudservisebackend.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}