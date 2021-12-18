package ru.netology.cloudservicebackend;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.Network;
import ru.netology.cloudservicebackend.model.MsgAnswerException;
import ru.netology.cloudservicebackend.model.MsgAnswerToken;
import ru.netology.cloudservicebackend.model.MsgLoginPassword;

import java.util.Map;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudServiceBackendApplicationTests {
    private static final String LOGIN = "lev@mail.ru";
    private static final String BAD_LOGIN = "lev11@mail.ru";
    private static final String PASSWORD = "1234";
    private static final String TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9" +
            ".eyJzdWIiOiJsZXZAbWFpbC5ydSIsImF1dGhvcml0aWVzIjpbIlJPTEVfTUFOQUdFUiIsIlJPTEVfVVNFUiJdfQ" +
            ".4cf_Hg6RDYWYFrHXX4TS4UMDM9VpJXTXxM_DzMFpYWs";

    @Autowired
    TestRestTemplate restTemplate;

    public static Network network = Network.newNetwork();

    public static GenericContainer<?> back_container =
            new GenericContainer<>("back")
                    .withExposedPorts(8081)
                    .withEnv("MYSQL_HOST", "mysql")
                    .withNetwork(network);

    public static GenericContainer<?> mysql_container =
            new GenericContainer<>("mysql")
                    .withExposedPorts(3306)
                    .withEnv(Map.of("MYSQL_ROOT_PASSWORD", "mysql",
                            "MYSQL_DATABASE", "netology"))
                    .withNetwork(network)
                    .withNetworkAliases("mysql");

    @BeforeAll
    public static void setUp() {
        mysql_container.start();
        back_container.start();
    }

    @Test
    void responseLoginTest() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        MsgLoginPassword msgLoginPassword = new MsgLoginPassword();
        msgLoginPassword.setLogin(LOGIN);
        msgLoginPassword.setPassword(PASSWORD);

        HttpEntity<MsgLoginPassword> requestEntity =
                new HttpEntity<>(msgLoginPassword, headers);

        ResponseEntity<MsgAnswerToken> forEntity =
                restTemplate.postForEntity("http://localhost:" +
                                back_container.getMappedPort(8081) + "/login",
                        requestEntity,
                        MsgAnswerToken.class);

        var expected = new MsgAnswerToken(TOKEN);
        var actual = forEntity.getBody();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void responseLoginBadRequestTest() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        MsgLoginPassword msgLoginPassword = new MsgLoginPassword();
        msgLoginPassword.setLogin(BAD_LOGIN);
        msgLoginPassword.setPassword(PASSWORD);

        HttpEntity<MsgLoginPassword> requestEntity =
                new HttpEntity<>(msgLoginPassword, headers);

        ResponseEntity<MsgAnswerToken> forEntity =
                restTemplate.postForEntity("http://localhost:" +
                                back_container.getMappedPort(8081) + "/login",
                        requestEntity,
                        MsgAnswerToken.class);

        var expected = HttpStatus.BAD_REQUEST;

        var actual = forEntity.getStatusCode();

        Assertions.assertEquals(expected, actual);
    }

}
