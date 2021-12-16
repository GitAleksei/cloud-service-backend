package ru.netology.cloudservicebackend.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import ru.netology.cloudservicebackend.model.MsgAnswerException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
public class CustomAuthenticationEntryPoint implements org.springframework.security.web.AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.setContentType(APPLICATION_JSON_VALUE);
        ObjectMapper objectMapper = new ObjectMapper();
        log.error("Error: {}\n {}", authException.getMessage(), authException.getStackTrace());
        objectMapper.writeValue(response.getWriter(),
                new MsgAnswerException(authException.getMessage()));
    }
}
