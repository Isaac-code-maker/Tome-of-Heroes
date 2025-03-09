package com.tomeofheroes.tome_of_heroes.models;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "spells")
public class Spells {

    @Id
    private UUID id_spells;
    private String name;
    private String description;
    private String level;
    private String school;

    public Spells(UUID id_spells, String name, String description, String level, String school) {
        this.id_spells = id_spells;
        this.name = name;
        this.description = description;
        this.level = level;
        this.school = school;
    }

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
}
