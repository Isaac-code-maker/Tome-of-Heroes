package com.tomeofheroes.tome_of_heroes.models;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "race")
public class Race {

    // Identificador único para cada raça
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id_raca;
    
    // Nome da raça
    private String name;
    
    // Bônus da raça
    private String bonus;
    
    // Descrição da raça
    private String description;

    // Relacionamento um-para-muitos com a classe Character
    @OneToMany(mappedBy = "race")
    private List<Character> characters;

    // Construtor padrão
    public Race() {
    }

    // Construtor com parâmetros
    public Race(UUID id_raca, String name, String bonus, String description) {
        this.id_raca = id_raca;
        this.name = name;
        this.bonus = bonus;
        this.description = description;
    }

    // Getters e setters para os campos

    public UUID getId_raca() {
        return id_raca;
    }

    public void setId_raca(UUID id_raca) {
        this.id_raca = id_raca;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBonus() {
        return bonus;
    }

    public void setBonus(String bonus) {
        this.bonus = bonus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Character> getCharacters() {
        return characters;
    }

    public void setCharacters(List<Character> characters) {
        this.characters = characters;
    }
}