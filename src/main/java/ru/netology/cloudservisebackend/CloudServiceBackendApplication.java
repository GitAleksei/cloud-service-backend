package ru.netology.cloudservisebackend;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.netology.cloudservisebackend.entity.Role;
import ru.netology.cloudservisebackend.entity.User;
import ru.netology.cloudservisebackend.service.UserService;

import java.util.ArrayList;

@SpringBootApplication
public class CloudServiceBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudServiceBackendApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
