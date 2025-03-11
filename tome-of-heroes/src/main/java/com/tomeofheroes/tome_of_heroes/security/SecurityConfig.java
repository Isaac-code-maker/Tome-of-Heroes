package com.tomeofheroes.tome_of_heroes.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // Define o filtro de segurança da aplicação
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtAuthenticationFilter jwtAuthenticationFilter)
            throws Exception {
        http
                // Desabilita a proteção CSRF (Cross-Site Request Forgery)
                .csrf(csrf -> csrf.disable())
                // Configura as regras de autorização para as requisições HTTP
                .authorizeHttpRequests(requests -> requests
                        // Permite acesso público aos endpoints que começam com "/public/"
                        .requestMatchers("/public/**").permitAll()
                        // Permite acesso público aos endpoints de registro e login de autenticação
                        .requestMatchers("/auth/register", "/auth/login").permitAll()
                        // Permite acesso público ao endpoint de registro de classe
                        .requestMatchers("/classes/register").permitAll()
                        // Permite acesso público aos endpoints de raça
                        .requestMatchers("/races/**").permitAll()
                        // Permite acesso público aos endpoints de magia
                        .requestMatchers("/spells/**").permitAll()
                        // Permite acesso público ao endpoint de criação de personagem
                        .requestMatchers("/createCharacter").permitAll()
                        // Permite acesso público aos endpoints de personagem
                        .requestMatchers("/characters/**").permitAll()
                        // Permite acesso público aos endpoints de status
                        .requestMatchers("/stats/**").permitAll()
                        // Permite acesso aos endpoints que começam com "/admin/" apenas para usuários
                        // com a role "ADMIN"
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        // Permite acesso aos endpoints que começam com "/user/" apenas para usuários
                        // com a role "USER"
                        .requestMatchers("/user/**").hasRole("USER")
                        // Exige autenticação para qualquer outra requisição
                        .anyRequest().authenticated())
                // Configura a página de login
                .formLogin(login -> login
                        .loginPage("/login")
                        .permitAll())
                // Configura o logout
                .logout(logout -> logout
                        .permitAll())
                // Configura a política de criação de sessão para ser STATELESS (sem estado)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // Adiciona o filtro de autenticação JWT antes do filtro de autenticação de
        // usuário e senha
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        // Retorna a configuração de segurança construída
        return http.build();
    }

    // Define o bean para o codificador de senhas (BCrypt)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Define o bean para o gerenciador de autenticação
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    // Define o bean para o serviço de detalhes do usuário
    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }
}