package com.tomeofheroes.tome_of_heroes.models;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "stats")
public class Stats {

    private UUID id;
    private int strength;
    private int dexterity;
    private int constitution;
    private int intelligence;
    private int wisdom;
    private int charisma;


    public Stats(UUID id, int strength, int dexterity, int constitution, int intelligence, int wisdom, int charisma) {
        this.id = id;
        this.strength = strength;
        this.dexterity = dexterity;
        this.constitution = constitution;
        this.intelligence = intelligence;
        this.wisdom = wisdom;
        this.charisma = charisma;
    }


    public UUID getId() {
        return id;
    }


    public void setId(UUID id) {
        this.id = id;
    }


    public int getStrength() {
        return strength;
    }


    public void setStrength(int strength) {
        this.strength = strength;
    }


    public int getDexterity() {
        return dexterity;
    }


    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }


    public int getConstitution() {
        return constitution;
    }


    public void setConstitution(int constitution) {
        this.constitution = constitution;
    }


    public int getIntelligence() {
        return intelligence;
    }


    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }


    public int getWisdom() {
        return wisdom;
    }


    public void setWisdom(int wisdom) {
        this.wisdom = wisdom;
    }


    public int getCharisma() {
        return charisma;
    }


    public void setCharisma(int charisma) {
        this.charisma = charisma;
    }
    
    
    

}
