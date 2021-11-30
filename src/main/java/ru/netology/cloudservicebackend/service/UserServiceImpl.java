package ru.netology.cloudservicebackend.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.netology.cloudservicebackend.entity.Authority;
import ru.netology.cloudservicebackend.entity.User;
import ru.netology.cloudservicebackend.repository.AuthorityRepository;
import ru.netology.cloudservicebackend.repository.UserRepository;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {
    private final AuthorityRepository authorityRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            log.error("User {} not found in the database", username);
            throw new UsernameNotFoundException("User not found in the database");
        } else {
            log.info("User found in the db: {}", username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(), user.getAuthorities());
    }

    @Override
    public User saveUser(User user) {
        log.info("Saving new user {} to the database", user.getFullName());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public Authority saveAuthority(Authority authority) {
        log.info("Saving new authority {} to the database", authority.getName());
        return authorityRepository.save(authority);
    }

    @Override
    public void addAuthorityToUser(String username, String authorityName) {
        log.info("Adding authority {} to user {}", authorityName, username);
        User user = userRepository.findByUsername(username);
        Authority authority = authorityRepository.findByName(authorityName);
        user.getAuthorities().add(authority);
    }

    @Override
    public User getUser(String username) {
        return userRepository.findByUsername(username);
    }
}
