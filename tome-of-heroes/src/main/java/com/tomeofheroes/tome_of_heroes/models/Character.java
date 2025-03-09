package com.tomeofheroes.tome_of_heroes.models;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "characters")
public class Character {

    private UUID id;
    private String name;
    private int level;
    private int experience;

    public Character(UUID id, String name, int level, int experience) {
        this.id = id;
        this.name = name;
        this.level = level;
        this.experience = experience;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public int getExperience() {
        return experience;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    

}
