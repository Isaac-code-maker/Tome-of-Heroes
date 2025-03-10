package com.tomeofheroes.tome_of_heroes.controllers;

import com.tomeofheroes.tome_of_heroes.models.Race;
import com.tomeofheroes.tome_of_heroes.services.RaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/races")
public class RaceController {

    @Autowired
    private RaceService raceService;

    // Endpoint para obter todas as raças
    @GetMapping
    public ResponseEntity<List<Race>> getAllRaces() {
        List<Race> races = raceService.getAllRaces();
        return ResponseEntity.ok(races);
    }

    // Endpoint para obter uma raça pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Race> getRaceById(@PathVariable UUID id) {
        Race race = raceService.getRaceById(id);
        if (race == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(race);
    }

    // Endpoint para criar uma nova raça
    @PostMapping
    public ResponseEntity<Race> createRace(@RequestBody Race race) {
        Race createdRace = raceService.createRace(race);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRace);
    }

    // Endpoint para atualizar uma raça existente
    @PutMapping("/{id}")
    public ResponseEntity<Race> updateRace(@PathVariable UUID id, @RequestBody Race raceDetails) {
        Race updatedRace = raceService.updateRace(id, raceDetails);
        if (updatedRace == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedRace);
    }

    // Endpoint para deletar uma raça pelo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRace(@PathVariable UUID id) {
        raceService.deleteRace(id);
        return ResponseEntity.noContent().build();
    }
}
