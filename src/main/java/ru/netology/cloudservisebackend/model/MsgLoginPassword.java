package ru.netology.cloudservisebackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class MsgLoginPassword {
    private String login;
    private String password;
}
