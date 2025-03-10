package com.tomeofheroes.tome_of_heroes.controllers;

import com.tomeofheroes.tome_of_heroes.models.Classe;
import com.tomeofheroes.tome_of_heroes.services.ClasseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/classes")
public class ClasseController {

    @Autowired
    private ClasseService classeService;

    // Endpoint para obter todas as classes
    @GetMapping
    public ResponseEntity<List<Classe>> getClasses() {
        List<Classe> classes = classeService.getClasses();
        return ResponseEntity.ok(classes);
    }

    // Endpoint para obter uma classe pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Classe> getClasse(@PathVariable UUID id) {
        Classe classe = classeService.getClasse(id);
        if (classe == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(classe);
    }

    // Endpoint para criar uma nova classe
    @PostMapping
    public ResponseEntity<Classe> createClasse(@RequestBody Classe classe) {
        Classe createdClasse = classeService.createClasse(classe);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdClasse);
    }

    // Endpoint para atualizar uma classe existente
    @PutMapping("/{id}")
    public ResponseEntity<Classe> updateClasse(@PathVariable UUID id, @RequestBody Classe classe) {
        Classe updatedClasse = classeService.updateClasse(id, classe);
        if (updatedClasse == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedClasse);
    }

    // Endpoint para deletar uma classe pelo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClasse(@PathVariable UUID id) {
        classeService.deleteClasse(id);
        return ResponseEntity.noContent().build();
    }
}
