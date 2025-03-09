package com.tomeofheroes.tome_of_heroes.models;

import java.util.Set;
import java.util.UUID;

import com.tomeofheroes.tome_of_heroes.Enum.Role;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

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
    @ElementCollection(targetClass = Role.class)
    @JoinTable(
        name = "user_roles",
        joinColumns = @JoinColumn(name = "user_id")
    )
    private Set<Role> roles;

    // Relacionamento um-para-muitos com a classe Character
    @OneToMany(mappedBy = "user")
    private Set<Character> characters;

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

    public Set<Character> getCharacters() {
        return characters;
    }

    public void setCharacters(Set<Character> characters) {
        this.characters = characters;
    }
}