package ru.netology.cloudservicebackend.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.netology.cloudservicebackend.security.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
