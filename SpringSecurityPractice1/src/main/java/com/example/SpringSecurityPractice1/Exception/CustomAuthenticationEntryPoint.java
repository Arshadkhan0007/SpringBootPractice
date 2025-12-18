package com.example.SpringSecurityPractice1.Exception;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {

        response.setStatus(403);
        response.setContentType("text/json");
        response.getWriter().write("""
                {
                    "error": "Authentication Failed - Invalid credentials",
                    "message": "%s"
                }
                """.formatted(authException.getMessage()));
    }

}
