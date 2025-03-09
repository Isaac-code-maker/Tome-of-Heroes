package com.tomeofheroes.tome_of_heroes.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import com.tomeofheroes.tome_of_heroes.models.Character;
import com.tomeofheroes.tome_of_heroes.services.CharacterService;

@RestController
public class CharacterController {

    private final CharacterService characterService;

    // Construtor que injeta o serviço CharacterService
    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }

    // Método para obter todos os personagens
    @GetMapping("/characters")
    public List<Character> getALLcharacters() {
        return characterService.findAll();
    }

    // Método para obter um personagem pelo ID
    @GetMapping("/characters/{id}")
    public ResponseEntity<Character> getCharacterbyId(@PathVariable UUID id) {
        ResponseEntity<Character> responseEntity = characterService.findById(id);
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            return ResponseEntity.ok(responseEntity.getBody());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Método para criar um novo personagem
    @PostMapping("/createCharacter")
    public Character createCharacter(@RequestBody Character character) {
        return characterService.save(character);
    }

    // Método para atualizar um personagem existente
    @PutMapping("/characters/{id}")
    public ResponseEntity<Character> updateCharacter(@PathVariable UUID id, @RequestBody Character characterDetails) {
        ResponseEntity<Character> responseEntity = characterService.findById(id);
        Optional<Character> characterOptional = Optional.ofNullable(responseEntity.getBody());
        return characterOptional.map(character -> {
            character.setName(characterDetails.getName());
            character.setRace(characterDetails.getRace());
            character.setCharacterClass(characterDetails.getCharacterClass());
            character.setLevel(characterDetails.getLevel());
            Character updatedCharacter = characterService.save(character);
            return ResponseEntity.ok(updatedCharacter);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Método para deletar um personagem pelo ID
    @DeleteMapping("/characters/{id}")
    public ResponseEntity<Void> deleteCharacter(@PathVariable UUID id) {
        if (characterService.findById(id).getBody() != null) {
            characterService.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}