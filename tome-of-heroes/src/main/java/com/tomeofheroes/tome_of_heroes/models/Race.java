package com.tomeofheroes.tome_of_heroes.models;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "race")
public class Race {

    private UUID id;
    private String name;
    private String bonus;
    private String description;

    public Race(UUID id, String name, String bonus, String description) {
        this.id = id;
        this.name = name;
        this.bonus = bonus;
        this.description = description;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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
