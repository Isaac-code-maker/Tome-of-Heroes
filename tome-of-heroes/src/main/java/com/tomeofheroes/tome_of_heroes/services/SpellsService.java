package com.tomeofheroes.tome_of_heroes.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.tomeofheroes.tome_of_heroes.models.Spells;
import com.tomeofheroes.tome_of_heroes.repository.SpellsRepository;

@Service
public class SpellsService {

    private final SpellsRepository spellsRepository;

    public SpellsService(SpellsRepository spellsRepository) {
        this.spellsRepository = spellsRepository;
    }

    // Método para obter todas as magias
    public List<Spells> getAllSpells() {
        return spellsRepository.findAll();
    }

    // Método para obter uma magia pelo ID
    public Spells getSpellsById(UUID id) {
        return spellsRepository.findById(id).orElse(null);
    }

    // Método para criar uma nova magia
    public Spells createSpells(Spells spells) {
        return spellsRepository.save(spells);
    }

    // Método para atualizar uma magia existente
    public Spells updateSpells(UUID id, Spells spellsDetails) {
        Spells spellsToUpdate = spellsRepository.findById(id).orElse(null);
        if (spellsToUpdate == null) {
            return null;
        }
        spellsToUpdate.setName(spellsDetails.getName());
        spellsToUpdate.setDescription(spellsDetails.getDescription());
        spellsToUpdate.setLevel(spellsDetails.getLevel());
        spellsToUpdate.setSchool(spellsDetails.getSchool());
        return spellsRepository.save(spellsToUpdate);
    }

    // Método para deletar uma magia pelo ID
    public void deleteSpells(UUID id) {
        spellsRepository.deleteById(id);
    }
}