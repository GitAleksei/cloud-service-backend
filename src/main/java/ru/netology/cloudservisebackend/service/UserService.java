package ru.netology.cloudservisebackend.service;

import ru.netology.cloudservisebackend.entity.Authority;
import ru.netology.cloudservisebackend.entity.User;

public interface UserService {
    User saveUser(User user);
    Authority saveAuthority(Authority authority);
    void addAuthorityToUser(String username, String authorityName);
    User getUser(String username);
}
