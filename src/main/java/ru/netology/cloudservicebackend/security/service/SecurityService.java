package ru.netology.cloudservicebackend.security.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import ru.netology.cloudservicebackend.model.MsgAnswerToken;
import ru.netology.cloudservicebackend.model.MsgLoginPassword;
import ru.netology.cloudservicebackend.security.SavedTokens;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SecurityService {
    @Value("${jwt.token.secret}")
    private String secret;

    private final SavedTokens savedTokens;
    private final AuthenticationManager authenticationManager;

    public SecurityService(AuthenticationManager authenticationManager,
                           SavedTokens savedTokens) {
        this.authenticationManager = authenticationManager;
        this.savedTokens = savedTokens;
    }

    public Authentication attemptAuthentication(MsgLoginPassword msgLoginPassword)
            throws AuthenticationException {
        String username = msgLoginPassword.getLogin();
        String password = msgLoginPassword.getPassword();
        log.info("Username is: {}", username);
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(username, password);
        log.info(authenticationToken.toString());
        return authenticationManager.authenticate(authenticationToken);
    }

    public MsgAnswerToken successfulAuthentication(Authentication authResult) {
        User user = (User)authResult.getPrincipal();
        Algorithm algorithm = Algorithm.HMAC256(secret);
        String access_token = JWT.create()
                .withSubject(user.getUsername())
                .withClaim("authorities",
                        user.getAuthorities().stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .sign(algorithm);
        savedTokens.add(access_token);
        MsgAnswerToken msgAnswerToken = new MsgAnswerToken(access_token);
        log.info("Sending access_token: {}", access_token);
        return msgAnswerToken;
    }
}
