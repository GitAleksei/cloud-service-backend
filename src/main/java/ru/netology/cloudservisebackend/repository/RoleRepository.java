package ru.netology.cloudservisebackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.netology.cloudservisebackend.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
