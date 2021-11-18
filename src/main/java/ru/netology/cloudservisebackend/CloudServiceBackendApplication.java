package ru.netology.cloudservisebackend;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
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
    CommandLineRunner run(UserService userService) {
        return args -> {
            userService.saveUser(new User(null, "Lev Tolstoy",
                    "lev", "1234", new ArrayList<>()));
            userService.saveUser(new User(null, "Fyodor Dostoevsky",
                    "fyodor", "1234", new ArrayList<>()));
            userService.saveUser(new User(null, "Lewis Carroll",
                    "lewis", "1234", new ArrayList<>()));
            userService.saveUser(new User(null, "Alexander Pushkin",
                    "alex", "1234", new ArrayList<>()));

            userService.saveRole(new Role(null, "ROLE_USER"));
            userService.saveRole(new Role(null, "ROLE_MANAGER"));
            userService.saveRole(new Role(null, "ROLE_ADMIN"));

            userService.addRoleToUser("lev", "ROLE_USER");
            userService.addRoleToUser("lev", "ROLE_MANAGER");
            userService.addRoleToUser("fyodor", "ROLE_USER");
            userService.addRoleToUser("lewis", "ROLE_MANAGER");
            userService.addRoleToUser("alex", "ROLE_USER");
            userService.addRoleToUser("alex", "ROLE_MANAGER");
            userService.addRoleToUser("alex", "ROLE_ADMIN");
        };
    }
}
