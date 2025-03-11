package com.tomeofheroes.tome_of_heroes.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component // Indica que esta classe é um componente do Spring, permitindo que seja
           // injetada em outros componentes
public class JwtTokenProvider {

    @Value("${jwt.secret}") // Injeta o valor da propriedade jwt.secret do arquivo de configuração
    private String jwtSecret;

    @Value("${jwt.expiration}") // Injeta o valor da propriedade jwt.expiration do arquivo de configuração
    private long jwtExpiration;

    @SuppressWarnings("deprecation")
    public String generateToken(Authentication authentication) {
        // Obtém os detalhes do usuário a partir do objeto de autenticação
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpiration);

        // Constrói o token JWT com o nome de usuário, data de emissão, data de
        // expiração e assina com o segredo JWT
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String getUsernameFromToken(String token) {
        @SuppressWarnings("deprecation")
        // Analisa o token JWT e extrai as reivindicações (claims)
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        // Retorna o nome de usuário (subject) a partir das reivindicações
        return claims.getSubject();
    }

    @SuppressWarnings("deprecation")
    public boolean validateToken(String token) {
        try {
            // Valida o token JWT verificando sua assinatura e data de expiração
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            // Retorna false se o token não for válido
            return false;
        }
    }
}
