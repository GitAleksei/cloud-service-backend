package ru.netology.cloudservicebackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data @NoArgsConstructor @AllArgsConstructor
public class MsgLoginPassword {
    @Pattern(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\" +
            ".[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")
    private String login;
    @Size(min = 3)
    private String password;
}
