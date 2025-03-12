package com.tomeofheroes.tome_of_heroes.controllers;

import com.tomeofheroes.tome_of_heroes.dto.CharacterDTO; // Importe o DTO
import com.tomeofheroes.tome_of_heroes.models.Character;
import com.tomeofheroes.tome_of_heroes.services.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/characters")
public class CharacterController {

    @Autowired
    private CharacterService characterService;

    @GetMapping
    public ResponseEntity<List<Character>> getCharacters() {
        List<Character> characters = characterService.getCharacters();
        return ResponseEntity.ok(characters);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Character> getCharacter(@PathVariable UUID id) {
        Character character = characterService.getCharacter(id);
        if (character == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(character);
    }

    @PostMapping("/create")
    public ResponseEntity<CharacterDTO> createCharacter(@RequestBody Character character) {
        CharacterDTO createdCharacter = characterService.createCharacter(character);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCharacter);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Character> updateCharacter(@PathVariable UUID id, @RequestBody Character character) {
        Character updatedCharacter = characterService.updateCharacter(id, character);
        if (updatedCharacter == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedCharacter);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCharacter(@PathVariable UUID id) {
        characterService.deleteCharacter(id);
        return ResponseEntity.noContent().build();
    }
}