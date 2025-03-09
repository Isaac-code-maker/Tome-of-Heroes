package com.tomeofheroes.tome_of_heroes.dto;

import jakarta.validation.constraints.NotBlank;

public class LoginRequestDTO {

    // Nome de usuário
    @NotBlank(message = "Nome de usuário é obrigatório")
    private String username;

    // Senha do usuário
    @NotBlank(message = "Senha é obrigatória")
    private String password;

    // Getters e setters para os campos

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
