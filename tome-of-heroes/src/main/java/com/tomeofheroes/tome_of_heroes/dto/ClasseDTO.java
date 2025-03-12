package com.tomeofheroes.tome_of_heroes.dto;

import java.util.UUID;

public class ClasseDTO {
    private UUID id_classe;
    private String name;
    private String description;
    private String dado_de_vida;

    
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

    // Getters e Setters

    
}
