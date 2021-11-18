package ru.netology.cloudservisebackend.service;

import ru.netology.cloudservisebackend.entity.Role;
import ru.netology.cloudservisebackend.entity.User;

public interface UserService {
    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    User getUser(String username);
}
