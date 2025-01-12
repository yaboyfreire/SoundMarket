package com.example.loginauthapi.infra.security;

import com.example.loginauthapi.domain.User;
import com.example.loginauthapi.repositories.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    TokenService tokenService;
    @Autowired
    UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Log to check the incoming request URI
        System.out.println("Processing request URI: " + request.getRequestURI());

        if (request.getRequestURI().startsWith("/auth/")) {
            // Skip /auth/ endpoints and log that the filter is bypassed
            System.out.println("Bypassing authentication for /auth/ endpoint.");
            filterChain.doFilter(request, response);
            return;
        }

        String token = recoverToken(request);

        if (token == null) {
            // Log if no token is found in the header
            System.out.println("No token found in the request.");
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Missing Authorization Token");
            return;
        }

        try {
            // Log the token for debugging purposes (ensure it's not too sensitive in production)
            System.out.println("Received token: " + token);
                
            String login = tokenService.validateToken(token);
            if (login != null) {
                // Log the user information after token validation
                System.out.println("Token validated. User login: " + login);
                
                User user = userRepository.findByEmail(login).orElseThrow(() -> {
                    // Log when user is not found in the repository
                    System.out.println("User not found in the repository with email: " + login);
                    try {
                        response.sendError(HttpServletResponse.SC_NOT_FOUND, "User Not Found");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return new RuntimeException("User Not Found");
                });

                // Log user found and setting authentication
                System.out.println("User authenticated: " + user.getEmail());

                var authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
                var authentication = new UsernamePasswordAuthenticationToken(user, null, authorities);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e) {
            // Log exception details to help diagnose
            System.out.println("Exception occurred during token validation: " + e.getMessage());
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Invalid Token");
            return;
        }

        // Proceed with the filter chain after authentication
        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            // Log when the Authorization header is missing or malformed
            System.out.println("Authorization header is missing or malformed.");
            return null;
        }
        // Log token extraction
        String token = authHeader.substring(7);
        System.out.println("Extracted token: " + token);
        return token;
    }
}
