package com.tomeofheroes.tome_of_heroes.models;

import java.util.Set;
import java.util.UUID;

import com.tomeofheroes.tome_of_heroes.Enum.Role;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;


@Entity
@Table(name = "users")
public class User {

    // Identificador único para cada usuário
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    // Nome de usuário
    private String username;

    // Senha do usuário
    private String password;

    // Relacionamento muitos-para-muitos com a classe Role
    @ManyToMany
    @JoinTable(
        name = "user_roles",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    // Construtor padrão
    public User() {
    }

    // Construtor com parâmetros
    public User(String username, String password, Set<Role> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    // Getters e setters para os campos

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

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