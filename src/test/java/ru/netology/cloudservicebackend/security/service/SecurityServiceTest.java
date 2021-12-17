package ru.netology.cloudservicebackend.security.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import ru.netology.cloudservicebackend.model.MsgAnswerToken;
import ru.netology.cloudservicebackend.security.entity.Authority;

import java.util.List;

@SpringBootTest
class SecurityServiceTest {
    private static final String USERNAME = "lev@mail.ru";
    private static final String PASSWORD = "";
    private static final List<Authority> AUTHORITIES =
            List.of(new Authority(1L, "ROLE_USER"),
                    new Authority(2L, "ROLE_MANAGER"));
    private static final String TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9" +
            ".eyJzdWIiOiJsZXZAbWFpbC5ydSIsImF1dGhvcml0aWVzIjpbIlJPTEVfTUFOQUdFUiIsIlJPTEVfVVNFUiJdfQ" +
            ".4cf_Hg6RDYWYFrHXX4TS4UMDM9VpJXTXxM_DzMFpYWs";

    @Autowired
    SecurityService securityService;

    @Test
    void attemptAuthenticationNpeTest() {
        Assertions.assertThrows(NullPointerException.class,
                () -> securityService.attemptAuthentication(null));
    }

    @Test
    void successfulAuthenticationNpeTest() {
        Assertions.assertThrows(NullPointerException.class,
                () -> securityService.successfulAuthentication(null));
    }

    @Test
    void successfulAuthenticationTest() {
        User user = new User(USERNAME, PASSWORD, AUTHORITIES);

        Authentication authResult = Mockito.mock(Authentication.class);
        Mockito.when(authResult.getPrincipal()).thenReturn(user);

        var expected = new MsgAnswerToken(TOKEN);

        var actual = securityService.successfulAuthentication(authResult);

        Assertions.assertEquals(expected, actual);
    }
}