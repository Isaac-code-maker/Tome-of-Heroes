package com.tomeofheroes.tome_of_heroes.models;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "spells")
public class Spells {

    private UUID id;
    private String name;
    private String description;
    private String level;
    private String school;

    public Spells(UUID id, String name, String description, String level, String school) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.level = level;
        this.school = school;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getLevel() {
        return level;
    }

    public String getSchool() {
        return school;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setSchool(String school) {
        this.school = school;
    }

}
