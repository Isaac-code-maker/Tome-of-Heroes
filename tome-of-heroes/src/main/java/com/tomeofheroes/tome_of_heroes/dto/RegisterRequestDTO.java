package com.tomeofheroes.tome_of_heroes.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.Set;

import com.tomeofheroes.tome_of_heroes.Enum.Role;

public class RegisterRequestDTO {

    // Nome de usuário
    @NotBlank(message = "Nome de usuário é obrigatório")
    private String username;

    // Senha do usuário
    @NotBlank(message = "Senha é obrigatória")
    @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
    private String password;

    // Papéis do usuário
    private Set<Role> roles;

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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
