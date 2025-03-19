package com.tomeofheroes.tome_of_heroes.controllers;

import com.tomeofheroes.tome_of_heroes.models.Spells;
import com.tomeofheroes.tome_of_heroes.services.DnDSpellService;
import com.tomeofheroes.tome_of_heroes.services.SpellsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/spells")
public class SpellsController {

    @Autowired
    private SpellsService spellsService;

    @Autowired
    private DnDSpellService dndSpellService;

    // Endpoint para obter todas as magias do banco de dados
    @GetMapping
    public ResponseEntity<List<Spells>> getAllSpells() {
        List<Spells> spells = spellsService.getAllSpells();
        return ResponseEntity.ok(spells);
    }

    // Endpoint para obter uma magia do banco de dados pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Spells> getSpellsById(@PathVariable UUID id) {
        Spells spells = spellsService.getSpellsById(id);
        if (spells == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(spells);
    }

    // Endpoint para criar uma nova magia no banco de dados
    @PostMapping
    public ResponseEntity<Spells> createSpells(@RequestBody Spells spells) {
        Spells createdSpells = spellsService.createSpells(spells);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSpells);
    }

    // Endpoint para atualizar uma magia existente no banco de dados
    @PutMapping("/{id}")
    public ResponseEntity<Spells> updateSpells(@PathVariable UUID id, @RequestBody Spells spellsDetails) {
        Spells updatedSpells = spellsService.updateSpells(id, spellsDetails);
        if (updatedSpells == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedSpells);
    }

    // Endpoint para deletar uma magia do banco de dados pelo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSpells(@PathVariable UUID id) {
        spellsService.deleteSpells(id);
        return ResponseEntity.noContent().build();
    }

    // Endpoint para listar todas as magias da API de D&D
    @GetMapping("/from-api")
    public ResponseEntity<List<Spells>> getAllSpellsFromAPI() {
        List<Spells> spells = dndSpellService.getAllSpellsFromAPI();
        return ResponseEntity.ok(spells);
    }

    // Endpoint para buscar uma magia da API de D&D pelo nome
    @GetMapping("/from-api/search")
    public ResponseEntity<Spells> getSpellByNameFromAPI(@RequestParam String name) {
        Spells spell = dndSpellService.getSpellByNameFromAPI(name);
        if (spell == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(spell);
    }
}