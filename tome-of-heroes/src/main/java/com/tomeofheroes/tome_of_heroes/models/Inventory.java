package com.tomeofheroes.tome_of_heroes.models;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "inventory")
public class Inventory {

    @Id
    private UUID id_inventory;
    private String name;
    private String tipo;
    private float peso;

    @ManyToOne
    @JoinColumn(name = "character_id")
    private Character character;

    public Inventory() {
    }

    public Inventory(UUID id_inventory, String name, String tipo, float peso, Character character) {
        this.id_inventory = id_inventory;
        this.name = name;
        this.tipo = tipo;
        this.peso = peso;
        this.character = character;
    }

    public UUID getId_inventory() {
        return id_inventory;
    }

    public void setId_inventory(UUID id_inventory) {
        this.id_inventory = id_inventory;
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

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }
}
