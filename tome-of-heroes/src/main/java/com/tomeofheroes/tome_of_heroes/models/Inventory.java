package com.tomeofheroes.tome_of_heroes.models;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "inventory")
public class Inventory {

    @Id
    private UUID id_inventory;
    private String name;
    private String tipo;
    private float peso;

    public Inventory(UUID id_inventory, String name, String tipo, float peso) {
        this.id_inventory = id_inventory;
        this.name = name;
        this.tipo = tipo;
        this.peso = peso;
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
}
