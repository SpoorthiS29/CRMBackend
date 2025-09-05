package com.crm.crmbackend.Controller;

import com.crm.crmbackend.Entity.User;
import com.crm.crmbackend.Service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    // Hardcoded admin credentials
    private final String ADMIN_EMAIL = "admin@crm.com";
    private final String ADMIN_PASSWORD = "admin123";
    private final String ADMIN_ROLE = "ADMIN";

    public AuthController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, String> payload, HttpSession session) {
        String email = payload.get("email");
        String password = payload.get("password");
        String role = payload.get("role");

        // Check hardcoded admin
        if (ADMIN_EMAIL.equals(email) && ADMIN_PASSWORD.equals(password) && ADMIN_ROLE.equalsIgnoreCase(role)) {
            session.setAttribute("user", Map.of(
                    "userId", 0,
                    "name", "Admin",
                    "email", ADMIN_EMAIL,
                    "role", ADMIN_ROLE));
            // Set authentication for Spring Security
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    ADMIN_EMAIL,
                    null,
                    List.of(new SimpleGrantedAuthority("ROLE_ADMIN")));
            SecurityContextHolder.getContext().setAuthentication(authToken);

            return Map.of(
                    "success", true,
                    "userId", 0,
                    "name", "Admin",
                    "email", ADMIN_EMAIL,
                    "role", ADMIN_ROLE);
        }

        // Check DB for other users
        User user = userService.findByEmail(email).orElse(null);
        if (user != null && passwordEncoder.matches(password, user.getPassword())
                && user.getRole().equalsIgnoreCase(role)) {
            session.setAttribute("user", Map.of(
                    "userId", user.getUserId(),
                    "name", user.getName(),
                    "email", user.getEmail(),
                    "role", user.getRole()));
            // Set authentication for Spring Security
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    user.getEmail(),
                    null,
                    List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().toUpperCase())));
            SecurityContextHolder.getContext().setAuthentication(authToken);

            return Map.of(
                    "success", true,
                    "userId", user.getUserId(),
                    "name", user.getName(),
                    "email", user.getEmail(),
                    "role", user.getRole());
        }
        return Map.of("success", false, "message", "Invalid credentials or role");
    }

    @PostMapping("/logout")
    public Map<String, Object> logout(HttpSession session) {
        session.invalidate();
        SecurityContextHolder.clearContext();
        return Map.of("success", true);
    }
}