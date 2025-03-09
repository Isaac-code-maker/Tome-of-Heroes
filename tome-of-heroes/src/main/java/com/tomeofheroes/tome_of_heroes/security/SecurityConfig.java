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

    // Define o filtro de segurança da aplicação
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // Desabilita a proteção CSRF para simplificação (não recomendado para produção)
            .csrf(csrf -> csrf.disable())
            
            // Configura as regras de autorização
            .authorizeHttpRequests(requests -> requests
                // Permite acesso público a URLs que começam com /public/
                .requestMatchers("/public/**").permitAll()
                // Permite acesso a URLs que começam com /admin/ apenas para usuários com o papel ADMIN
                .requestMatchers("/admin/**").hasRole("ADMIN")
                // Permite acesso a URLs que começam com /user/ apenas para usuários com o papel USER
                .requestMatchers("/user/**").hasRole("USER")
                // Requer autenticação para qualquer outra URL
                .anyRequest().authenticated())
            
            // Configura o login baseado em formulário
            .formLogin(login -> login
                // Especifica uma página de login personalizada
                .loginPage("/login")
                // Permite acesso público à página de login
                .permitAll())
            
            // Configura o logout
            .logout(logout -> logout
                // Permite acesso público ao logout
                .permitAll());

        // Constrói e retorna o filtro de segurança configurado
        return http.build();
    }

    // Define um PasswordEncoder que usa o algoritmo BCrypt para codificar senhas
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}