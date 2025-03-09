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

public List<Character> getALLcharacters() {
    return characterService.findAll();
}

public ResponseEntity<Character> getCharacterbyId(@PathVariable UUID id) {
    Optional<Character> character = characterService.findById(id);
    return character.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity
                    .notFound()
                    .build());
}

@PostMapping("/createCharacter")
public Character createCharacter(@RequestBody Character character){
return characterService.save(character);
}

public ResponseEntity<Character> updateCharacter(@PathVariable UUID I   Id, @RequestBody Character character) {
    return characterservice.save(character);
}

public ResponseEntity<Character> updateCharacter(@PathVariable UUID id, @RequestBody Character characterDatails) {
    return characterService.findById(id)
    map(character -> {
        character.setName(characterDetails.getName());
        character.setRace(characterDetails.getRace());

        character.setCharacterClass(characterDetails)
    })
}
}


