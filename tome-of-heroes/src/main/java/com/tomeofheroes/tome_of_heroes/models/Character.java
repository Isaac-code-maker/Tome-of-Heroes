package com.tomeofheroes.tome_of_heroes.models;

import java.util.Set;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "characters")
public class Character {
    
    // Identificador único para cada personagem, gerado automaticamente
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    
    // Nome do personagem
    private String name;
    
    // Nível do personagem
    private int level;
    
    // Experiência do personagem
    private int experience;

    // Relacionamento muitos-para-um com a classe Classe
    @ManyToOne
    @JoinColumn(name = "id_classe")
    private Classe classe;

    // Relacionamento um-para-muitos com a classe Inventory
    @OneToMany(mappedBy = "character")
    private Set<Inventory> inventory;

    // Relacionamento muitos-para-um com a classe Race
    @ManyToOne
    @JoinColumn(name = "id_raca")
    private Race race;

    // Relacionamento muitos-para-muitos com a classe Spells
    @ManyToMany
    @JoinTable(
        name = "personagem_magia",
        joinColumns = @JoinColumn(name = "character_id"),
        inverseJoinColumns = @JoinColumn(name = "spell_id")
    )
    private Set<Spells> spells;

    // Relacionamento um-para-um com a classe Stats
    @OneToOne
    @JoinColumn(name = "id_stats")
    private Stats stats;

    // Classe do personagem
    private String characterClass;

    // Construtor padrão
    public Character() {
    }

    // Construtor com parâmetros
    public Character(UUID id, String name, int level, int experience) {
        this.id = id;
        this.name = name;
        this.level = level;
        this.experience = experience;
    }

    // Getters e setters para os campos

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

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public Set<Inventory> getInventory() {
        return inventory;
    }

    public void setInventory(Set<Inventory> inventory) {
        this.inventory = inventory;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public Set<Spells> getSpells() {
        return spells;
    }

    public void setSpells(Set<Spells> spells) {
        this.spells = spells;
    }

    public Stats getStats() {
        return stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }

    public String getCharacterClass() {
        return characterClass;
    }

    public void setCharacterClass(String characterClass) {
        this.characterClass = characterClass;
    }
}