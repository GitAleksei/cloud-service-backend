package ru.netology.cloudservicebackend.model;

import lombok.Data;

@Data
public class MsgAnswerException {
    private String message;
    private int id;
    private static int idStatic;

    public MsgAnswerException() {
        idStatic++;
        id = idStatic;
    }

    public MsgAnswerException(String message) {
        this.message = message;
        idStatic++;
        id = idStatic;
    }
}
