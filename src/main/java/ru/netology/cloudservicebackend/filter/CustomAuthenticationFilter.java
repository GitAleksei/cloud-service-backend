package ru.netology.cloudservicebackend.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.netology.cloudservicebackend.entity.Authority;
import ru.netology.cloudservicebackend.exception.NotFoundTokenException;
import ru.netology.cloudservicebackend.model.MsgAnswerException;
import ru.netology.cloudservicebackend.security.SavedTokens;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Component
@Slf4j
public class CustomAuthenticationFilter extends OncePerRequestFilter {
    @Value("${jwt.token.header}")
    private String header;
    @Value("${jwt.token.secret}")
    private String secret;
    @Value("${jwt.token.start}")
    private String start;

    private final SavedTokens savedTokens;

    public CustomAuthenticationFilter(SavedTokens savedTokens) {
        this.savedTokens = savedTokens;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if ("/login".equals(request.getServletPath())) {
            filterChain.doFilter(request, response);
        } else {
            String authHeader = request.getHeader(header);
            if (authHeader != null && authHeader.startsWith(start)) {
                try {
                    String token = authHeader.substring(start.length());
                    if (!savedTokens.contains(token)) {
                        throw new NotFoundTokenException("Token is not found");
                    }
                    Algorithm algorithm = Algorithm.HMAC256(secret);
                    JWTVerifier verifier = JWT.require(algorithm).build();
                    DecodedJWT decodedJWT = verifier.verify(token);
                    String username = decodedJWT.getSubject();
                    Collection<Authority> authorities = decodedJWT.getClaim(
                            "authorities").asList(String.class).stream()
                            .map(Authority::new)
                            .collect(Collectors.toList());
                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(username, null, authorities);
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                    filterChain.doFilter(request, response);
                } catch (Exception e) {
                    log.error("Error login in: {}", e.getMessage());
                    response.setStatus(UNAUTHORIZED.value());
                    MsgAnswerException msgAnswerException =
                            new MsgAnswerException(e.getMessage());
                    response.setContentType(APPLICATION_JSON_VALUE);
                    new ObjectMapper().writeValue(response.getOutputStream(), msgAnswerException);
                }
            } else {
                filterChain.doFilter(request, response);
            }
        }
    }
}
