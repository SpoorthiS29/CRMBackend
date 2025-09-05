package com.crm.crmbackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .cors(cors -> cors.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .requestMatchers("/auth/**").permitAll()
                .requestMatchers("/users/**").hasRole("ADMIN")
                .requestMatchers("/leads/**").hasAnyRole("ADMIN", "SALES")
                .requestMatchers("/tickets/**").hasAnyRole("ADMIN", "SUPPORT")
                .requestMatchers("/dashboard/**", "/reports/**").authenticated()
                .anyRequest().permitAll()
            )
            .sessionManagement(session -> session
            .sessionFixation().migrateSession()
            .maximumSessions(1)
        )
        .securityContext(securityContext -> securityContext
            .requireExplicitSave(false) // Ensures SecurityContext is saved in session
        );
        return http.build();
    }
}