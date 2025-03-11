package com.tomeofheroes.tome_of_heroes.controllers;

import com.tomeofheroes.tome_of_heroes.models.Stats;
import com.tomeofheroes.tome_of_heroes.services.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/stats")
public class StatsController {


    @Autowired
    private StatsService statsService;

    // Endpoint para obter todas as estatísticas
    @GetMapping
    public ResponseEntity<List<Stats>> getAllStats() {
        List<Stats> stats = statsService.getAllStats();
        return ResponseEntity.ok(stats);
    }

    // Endpoint para obter uma estatística pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Stats> getStatsById(@PathVariable UUID id) {
        Stats stats = statsService.getStatsById(id);
        if (stats == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(stats);
    }

    // Endpoint para criar uma nova estatística
    @PostMapping
    public ResponseEntity<Stats> createStats(@RequestBody Stats stats) {
        Stats createdStats = statsService.createStats(stats);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStats);
    }

    // Endpoint para atualizar uma estatística existente
    @PutMapping("/{id}")
    public ResponseEntity<Stats> updateStats(@PathVariable UUID id, @RequestBody Stats statsDetails) {
        Stats updatedStats = statsService.updateStats(id, statsDetails);
        if (updatedStats == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedStats);
    }

    // Endpoint para deletar uma estatística pelo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStats(@PathVariable UUID id) {
        statsService.deleteStats(id);
        return ResponseEntity.noContent().build();
    }
}
