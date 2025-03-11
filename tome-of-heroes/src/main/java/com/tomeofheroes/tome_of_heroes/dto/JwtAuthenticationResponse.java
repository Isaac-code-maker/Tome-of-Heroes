package com.tomeofheroes.tome_of_heroes.dto;

public class JwtAuthenticationResponse {
    private String accessToken; // Armazena o token de acesso JWT
    private String tokenType = "Bearer"; // Define o tipo de token como "Bearer"

    // Construtor que inicializa o token de acesso
    public JwtAuthenticationResponse(String accessToken) {
        this.accessToken = accessToken;
    }

    // Getter para o token de acesso
    public String getAccessToken() {
        return accessToken;
    }

    // Setter para o token de acesso
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    // Getter para o tipo de token
    public String getTokenType() {
        return tokenType;
    }

    // Setter para o tipo de token
    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }
}
