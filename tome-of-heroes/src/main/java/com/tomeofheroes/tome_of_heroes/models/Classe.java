package com.tomeofheroes.tome_of_heroes.models;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "classes")
public class Classe {

    @Id
    private UUID id_classe;
    private String name;
    private String description;
    private String dado_de_vida;

    public Classe(UUID id_classe, String name, String description, String dado_de_vida) {
        this.id_classe = id_classe;
        this.name = name;
        this.description = description;
        this.dado_de_vida = dado_de_vida;
    }

    public UUID getId_classe() {
        return id_classe;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getDado_de_vida() {
        return dado_de_vida;
    }

    public void setId_classe(UUID id_classe) {
        this.id_classe = id_classe;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDado_de_vida(String dado_de_vida) {
        this.dado_de_vida = dado_de_vida;
    }
}
