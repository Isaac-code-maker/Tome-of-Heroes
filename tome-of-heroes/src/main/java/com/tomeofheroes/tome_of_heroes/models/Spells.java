package com.tomeofheroes.tome_of_heroes.models;

import java.util.Set;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "spells")
public class Spells {

    // Identificador único para cada feitiço
    @Id
    private UUID id_spells;
    
    // Nome do feitiço
    private String name;
    
    // Descrição do feitiço
    private String description;
    
    // Nível do feitiço
    private String level;
    
    // Escola de magia do feitiço
    private String school;

    // Relacionamento muitos-para-muitos com a classe Character
    @ManyToMany(mappedBy = "spells")
    private Set<Character> characters;

    // Construtor padrão
    public Spells() {
    }

    // Construtor com parâmetros
    public Spells(UUID id_spells, String name, String description, String level, String school) {
        this.id_spells = id_spells;
        this.name = name;
        this.description = description;
        this.level = level;
        this.school = school;
    }

    // Getters e setters para os campos

    public UUID getId_spells() {
        return id_spells;
    }

    public void setId_spells(UUID id_spells) {
        this.id_spells = id_spells;
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

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public Set<Character> getCharacters() {
        return characters;
    }

    public void setCharacters(Set<Character> characters) {
        this.characters = characters;
    }
}