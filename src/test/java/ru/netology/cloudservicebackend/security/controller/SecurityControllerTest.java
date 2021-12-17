package ru.netology.cloudservicebackend.security.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.netology.cloudservicebackend.model.MsgAnswerToken;
import ru.netology.cloudservicebackend.model.MsgLoginPassword;

@SpringBootTest
class SecurityControllerTest {
    private static final String USERNAME = "lev@mail.ru";
    private static final String PASSWORD = "1234";
    private static final String TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9" +
            ".eyJzdWIiOiJsZXZAbWFpbC5ydSIsImF1dGhvcml0aWVzIjpbIlJPTEVfTUFOQUdFUiIsIlJPTEVfVVNFUiJdfQ" +
            ".4cf_Hg6RDYWYFrHXX4TS4UMDM9VpJXTXxM_DzMFpYWs";

    @Autowired
    SecurityController securityController;

    @Test
    void postLoginTest() {
        MsgLoginPassword msgLoginPassword = Mockito.mock(MsgLoginPassword.class);
        Mockito.when(msgLoginPassword.getLogin()).thenReturn(USERNAME);
        Mockito.when(msgLoginPassword.getPassword()).thenReturn(PASSWORD);

        var expected = new MsgAnswerToken(TOKEN);

        var actual = securityController.postLogin(msgLoginPassword);

        Assertions.assertEquals(expected, actual);
    }
}