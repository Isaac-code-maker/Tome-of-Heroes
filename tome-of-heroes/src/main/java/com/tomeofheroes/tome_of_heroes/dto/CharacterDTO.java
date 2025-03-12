package com.tomeofheroes.tome_of_heroes.dto;

import java.util.UUID;

public class CharacterDTO {
    private UUID id;
    private String name;
    private int level;
    private int experience;
    private int strength;
    private int dexterity;
    private int constitution;
    private int intelligence;
    private int wisdom;
    private int charisma;
    private ClasseDTO classe;
    private RaceDTO race;

    
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
    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }
    public int getExperience() {
        return experience;
    }
    public void setExperience(int experience) {
        this.experience = experience;
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
    public ClasseDTO getClasse() {
        return classe;
    }
    public void setClasse(ClasseDTO classe) {
        this.classe = classe;
    }
    public RaceDTO getRace() {
        return race;
    }
    public void setRace(RaceDTO race) {
        this.race = race;
    }

    // Getters e Setters

    
}
