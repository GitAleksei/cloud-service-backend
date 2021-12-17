package ru.netology.cloudservicebackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MsgAnswerFile {
    private String hash;
    private byte[] file;
}
