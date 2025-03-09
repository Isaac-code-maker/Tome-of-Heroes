package com.tomeofheroes.tome_of_heroes.models;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "race")
public class Race {

    @Id
    private UUID id_raca;
    private String name;
    private String bonus;
    private String description;

    public Race(UUID id_raca, String name, String bonus, String description) {
        this.id_raca = id_raca;
        this.name = name;
        this.bonus = bonus;
        this.description = description;
    }

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
}
