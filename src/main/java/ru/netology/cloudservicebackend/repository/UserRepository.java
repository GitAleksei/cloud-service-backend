package ru.netology.cloudservicebackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.netology.cloudservicebackend.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
