package ru.netology.cloudservicebackend.security.service;

import ru.netology.cloudservicebackend.security.entity.Authority;
import ru.netology.cloudservicebackend.security.entity.User;

public interface UserService {
    User saveUser(User user);
    Authority saveAuthority(Authority authority);
    void addAuthorityToUser(String username, String authorityName);
    User getUser(String username);
}
