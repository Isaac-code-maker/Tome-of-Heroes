package com.tomeofheroes.tome_of_heroes.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tomeofheroes.tome_of_heroes.repository.CharacterRepository;
import com.tomeofheroes.tome_of_heroes.models.Character;

@Service
public class CharacterService {

    private final CharacterRepository characterRepository;

    // Construtor que injeta o repositório CharacterRepository
    public CharacterService(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    // Método para obter todos os personagens
    public List<Character> findAll() {
        return characterRepository.findAll();
    }

    // Método para obter um personagem pelo ID
    public ResponseEntity<Character> findById(UUID id) {
        Optional<Character> character = characterRepository.findById(id);
        return character.map(ResponseEntity::ok)
                        .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Método para salvar um novo personagem ou atualizar um existente
    public Character save(Character character) {
        return characterRepository.save(character);
    }

    // Método para deletar um personagem pelo ID
    public void delete(UUID id) {
        characterRepository.deleteById(id);
    }
}