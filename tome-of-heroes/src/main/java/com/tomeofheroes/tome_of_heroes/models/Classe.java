package com.tomeofheroes.tome_of_heroes.models;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "classes")
public class Classe {

    // Identificador único para cada classe
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id_classe;

    // Nome da classe
    private String name;

    // Descrição da classe
    private String description;

    // Dado de vida da classe
    private String dado_de_vida;

    // Relacionamento um-para-muitos com a classe Character
    @OneToMany(mappedBy = "classe")
    @JsonIgnore
    private List<Character> characters;

    // Construtor padrão
    public Classe() {
    }

    // Construtor com parâmetros
    public Classe(String name, String description, String dado_de_vida) {
        this.name = name;
        this.description = description;
        this.dado_de_vida = dado_de_vida;
    }

    // Getters e setters para os campos

    public UUID getId_classe() {
        return id_classe;
    }

    public void setId_classe(UUID id_classe) {
        this.id_classe = id_classe;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDado_de_vida() {
        return dado_de_vida;
    }

    public void setDado_de_vida(String dado_de_vida) {
        this.dado_de_vida = dado_de_vida;
    }

    public List<Character> getCharacters() {
        return characters;
    }

    public void setCharacters(List<Character> characters) {
        this.characters = characters;
    }
}