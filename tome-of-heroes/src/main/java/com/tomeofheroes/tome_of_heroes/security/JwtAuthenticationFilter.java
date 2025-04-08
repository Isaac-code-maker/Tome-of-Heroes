package com.tomeofheroes.tome_of_heroes.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private UserDetailsService userDetailsService;

    @SuppressWarnings("null")
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // Obtém o token JWT do cabeçalho da requisição
        String token = getJwtFromRequest(request);

        // Valida o token JWT
        if (token != null && tokenProvider.validateToken(token)) {
            // Obtém o nome de usuário a partir do token JWT
            String username = tokenProvider.getUsernameFromToken(token);

            // Carrega os detalhes do usuário a partir do nome de usuário
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (userDetails != null) {
                // Cria um objeto de autenticação com os detalhes do usuário
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                // Define o objeto de autenticação no contexto de segurança
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        // Continua a cadeia de filtros
        filterChain.doFilter(request, response);
    }

    // Método para obter o token JWT do cabeçalho da requisição
    private String getJwtFromRequest(HttpServletRequest request) {
        // Obtém o valor do cabeçalho "Authorization"
        String bearerToken = request.getHeader("Authorization");
        // Verifica se o cabeçalho contém um token JWT
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            // Retorna o token JWT sem o prefixo "Bearer "
            return bearerToken.substring(7);
        }
        return null;
    }
}
