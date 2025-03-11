package com.tomeofheroes.tome_of_heroes.models;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "stats")
public class Stats {

    // Identificador único para cada conjunto de estatísticas
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id_stats;
    
    // Força do personagem
    private int strength;
    
    // Destreza do personagem
    private int dexterity;
    
    // Constituição do personagem
    private int constitution;
    
    // Inteligência do personagem
    private int intelligence;
    
    // Sabedoria do personagem
    private int wisdom;
    
    // Carisma do personagem
    private int charisma;

    // Construtor padrão
    public Stats() {
    }
    
    // Construtor com parâmetros
    public Stats(UUID id_stats, int strength, int dexterity, int constitution, int intelligence, int wisdom, int charisma) {
        this.id_stats = id_stats;
        this.strength = strength;
        this.dexterity = dexterity;
        this.constitution = constitution;
        this.intelligence = intelligence;
        this.wisdom = wisdom;
        this.charisma = charisma;
    }

    // Getters e setters para os campos

    public UUID getId_stats() {
        return id_stats;
    }

    public void setId_stats(UUID id_stats) {
        this.id_stats = id_stats;
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