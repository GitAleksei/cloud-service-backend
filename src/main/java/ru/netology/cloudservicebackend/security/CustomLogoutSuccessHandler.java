package ru.netology.cloudservicebackend.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler
        implements LogoutSuccessHandler {
    @Value("${jwt.token.header}")
    private String header;
    @Value("${jwt.token.start}")
    private String start;

    private final SavedTokens savedTokens;

    public CustomLogoutSuccessHandler(SavedTokens savedTokens) {
        this.savedTokens = savedTokens;
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest request,
                                HttpServletResponse response,
                                Authentication authentication)
            throws IOException, ServletException {
        String authHeader = request.getHeader(header);
        if (authHeader != null && authHeader.startsWith(start)) {
            String token = authHeader.substring(start.length());
            savedTokens.remove(token);
        }
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
