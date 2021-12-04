package ru.netology.cloudservicebackend.security;

import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

@Component
public class SavedTokens {
    private final Set<String> tokens;

    public SavedTokens() {
        this.tokens = new ConcurrentSkipListSet<>();
    }

    public void add(String token) {
        tokens.add(token);
    }

    public boolean contains(String token) {
        return tokens.contains(token);
    }

    public boolean remove(String token) {
        return tokens.remove(token);
    }
}
