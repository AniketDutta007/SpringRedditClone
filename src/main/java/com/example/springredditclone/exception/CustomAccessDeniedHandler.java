package com.example.springredditclone.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
//        if(accessDeniedException instanceof AccessDeniedException) {
//
//        }
        // Set the response status to 403 Forbidden
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);

        // Create a custom response object with custom data
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("success", false);
        responseBody.put("message", "Unauthorized access");

        // Write the response body to the response
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(response.getWriter(), responseBody);
        System.out.println("Invoked Access Denied Handler");
    }
}
