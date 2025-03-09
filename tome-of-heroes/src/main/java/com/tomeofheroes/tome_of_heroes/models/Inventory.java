package com.tomeofheroes.tome_of_heroes.models;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "inventory")
public class Inventory {

    // Identificador único para cada item de inventário
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id_item;
    
    // Nome do item de inventário
    private String name;
    
    // Tipo do item de inventário
    private String tipo;
    
    // Peso do item de inventário
    private float peso;

    // Quantidade de ouro
    private int gold;

    // Quantidade de prata
    private int silver;

    // Quantidade de cobre
    private int copper;

    // Relacionamento muitos-para-um com a classe Character
    @ManyToOne
    @JoinColumn(name = "character_id")
    private Character character;

    // Construtor padrão
    public Inventory() {
    }

    // Construtor com parâmetros
    public Inventory(String name, String tipo, float peso, int gold, int silver, int copper, Character character) {
        this.name = name;
        this.tipo = tipo;
        this.peso = peso;
        this.gold = gold;
        this.silver = silver;
        this.copper = copper;
        this.character = character;
    }

    // Getters e setters para os campos

    public UUID getId_item() {
        return id_item;
    }

    public void setId_item(UUID id_item) {
        this.id_item = id_item;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getSilver() {
        return silver;
    }

    public void setSilver(int silver) {
        this.silver = silver;
    }

    public int getCopper() {
        return copper;
    }

    public void setCopper(int copper) {
        this.copper = copper;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }
}