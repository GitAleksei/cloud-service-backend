package ru.netology.cloudservicebackend.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(prefix = "jwt.token")
public class JwtTokenData {
    private String secret;
    private String header;
}
