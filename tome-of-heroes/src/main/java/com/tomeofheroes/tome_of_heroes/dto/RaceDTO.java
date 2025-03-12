package com.tomeofheroes.tome_of_heroes.dto;

import java.util.UUID;

public class RaceDTO {
    private UUID id_raca;
    private String name;
    private String description;

    
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
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    // Getters e Setters

    
}
