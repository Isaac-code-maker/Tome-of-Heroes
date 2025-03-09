package com.tomeofheroes.tome_of_heroes.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.tomeofheroes.tome_of_heroes.repository.CharacterRepository;
import com.tomeofheroes.tome_of_heroes.models.Character;

public class CharacterService {

    private final CharacterRepository characterRepository;

    public CharacterService(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    public List<Character> findAll() {
        return characterRepository.findAll();
    }

    public Optional<Character> findById(UUID id) {
        return characterRepository.findById(id);
    }

    public Character save(Character character) {
        return characterRepository.save(character);
    }

    public void delete(UUID id) {
        characterRepository.deleteById(id);
    }

    

}
