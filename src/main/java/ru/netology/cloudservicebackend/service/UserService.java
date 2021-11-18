package ru.netology.cloudservicebackend.service;

import ru.netology.cloudservicebackend.entity.Authority;
import ru.netology.cloudservicebackend.entity.User;

public interface UserService {
    User saveUser(User user);
    Authority saveAuthority(Authority authority);
    void addAuthorityToUser(String username, String authorityName);
    User getUser(String username);
}
