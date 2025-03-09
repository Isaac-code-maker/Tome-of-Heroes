package com.tomeofheroes.tome_of_heroes.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Desabilita CSRF para simplificação, não recomendado para produção
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/public/**").permitAll() // Permite acesso público a URLs que começam com /public/
                        .anyRequest().authenticated())
                .formLogin(login -> login
                        .loginPage("/login") // Página de login personalizada
                        .permitAll())
                .logout(logout -> logout
                        .permitAll());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}