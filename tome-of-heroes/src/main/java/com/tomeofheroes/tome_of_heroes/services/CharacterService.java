package com.tomeofheroes.tome_of_heroes.services;

import com.tomeofheroes.tome_of_heroes.models.Character;
import com.tomeofheroes.tome_of_heroes.models.Race;
import com.tomeofheroes.tome_of_heroes.models.Stats;
import com.tomeofheroes.tome_of_heroes.repository.CharacterRepository;
import com.tomeofheroes.tome_of_heroes.repository.RaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CharacterService {

    @Autowired
    private CharacterRepository characterRepository;

    @Autowired
    private RaceRepository raceRepository;

    public List<Character> getCharacters() {
        return characterRepository.findAll();
    }

    public Character getCharacter(UUID id) {
        return characterRepository.findById(id).orElse(null);
    }

    public Character createCharacter(Character character) {
        // Distribuir os pontos de atributos com base na classe do personagem
        distributeStatsBasedOnClass(character);
        // Ajustar os atributos de Stats com base na raça do personagem
        adjustStatsBasedOnRace(character);
        return characterRepository.save(character);
    }

    public Character updateCharacter(UUID id, Character character) {
        Character characterToUpdate = characterRepository.findById(id).orElse(null);
        if (characterToUpdate == null) {
            return null;
        }
        characterToUpdate.setName(character.getName());
        characterToUpdate.setRace(character.getRace());
        characterToUpdate.setStats(character.getStats());
        characterToUpdate.setCharacterClass(character.getCharacterClass());
        // Distribuir os pontos de atributos com base na classe do personagem
        distributeStatsBasedOnClass(characterToUpdate);
        // Ajustar os atributos de Stats com base na raça do personagem
        adjustStatsBasedOnRace(characterToUpdate);
        return characterRepository.save(characterToUpdate);
    }

    public void deleteCharacter(UUID id) {
        characterRepository.deleteById(id);
    }

    private void distributeStatsBasedOnClass(Character character) {
        Stats stats = character.getStats();
        String characterClass = character.getCharacterClass().toLowerCase();

        if (stats != null) {
            switch (characterClass) {
                case "bárbaro":
                case "guerreiro":
                    stats.setStrength(15);
                    stats.setConstitution(14);
                    stats.setDexterity(13);
                    stats.setWisdom(12);
                    stats.setCharisma(10);
                    stats.setIntelligence(8);
                    break;
                case "paladino":
                    stats.setStrength(15);
                    stats.setCharisma(14);
                    stats.setConstitution(13);
                    stats.setWisdom(12);
                    stats.setDexterity(10);
                    stats.setIntelligence(8);
                    break;
                case "ladino":
                    stats.setDexterity(15);
                    stats.setIntelligence(14);
                    stats.setWisdom(13);
                    stats.setConstitution(12);
                    stats.setCharisma(10);
                    stats.setStrength(8);
                    break;
                case "monge":
                    stats.setDexterity(15);
                    stats.setWisdom(14);
                    stats.setConstitution(13);
                    stats.setIntelligence(12);
                    stats.setCharisma(10);
                    stats.setStrength(8);
                    break;
                case "patrulheiro":
                    stats.setDexterity(15);
                    stats.setWisdom(14);
                    stats.setConstitution(13);
                    stats.setIntelligence(12);
                    stats.setCharisma(10);
                    stats.setStrength(8);
                    break;
                case "mago":
                case "artífice":
                    stats.setIntelligence(15);
                    stats.setConstitution(14);
                    stats.setDexterity(13);
                    stats.setWisdom(12);
                    stats.setCharisma(10);
                    stats.setStrength(8);
                    break;
                case "clérigo":
                    stats.setWisdom(15);
                    stats.setConstitution(14);
                    stats.setStrength(13);
                    stats.setIntelligence(12);
                    stats.setCharisma(10);
                    stats.setDexterity(8);
                    break;
                case "druida":
                    stats.setWisdom(15);
                    stats.setConstitution(14);
                    stats.setDexterity(13);
                    stats.setIntelligence(12);
                    stats.setCharisma(10);
                    stats.setStrength(8);
                    break;
                case "feiticeiro":
                case "bruxo":
                case "bardo":
                    stats.setCharisma(15);
                    stats.setConstitution(14);
                    stats.setDexterity(13);
                    stats.setWisdom(12);
                    stats.setIntelligence(10);
                    stats.setStrength(8);
                    break;
                default:
                    break;
            }
        }
    }

    private void adjustStatsBasedOnRace(Character character) {
        Race race = character.getRace();
        Stats stats = character.getStats();

        if (race != null && stats != null && race.getName() != null) {
            switch (race.getName().toLowerCase()) {
                case "humano":
                    stats.setStrength(stats.getStrength() + 1);
                    stats.setDexterity(stats.getDexterity() + 1);
                    stats.setConstitution(stats.getConstitution() + 1);
                    stats.setIntelligence(stats.getIntelligence() + 1);
                    stats.setWisdom(stats.getWisdom() + 1);
                    stats.setCharisma(stats.getCharisma() + 1);
                    break;
                case "anão":
                    stats.setConstitution(stats.getConstitution() + 2);
                    break;
                case "elfo":
                    stats.setDexterity(stats.getDexterity() + 2);
                    break;
                case "halfling":
                    stats.setDexterity(stats.getDexterity() + 2);
                    break;
                case "draconato":
                    stats.setStrength(stats.getStrength() + 2);
                    stats.setCharisma(stats.getCharisma() + 1);
                    break;
                case "gnomo":
                    stats.setIntelligence(stats.getIntelligence() + 2);
                    break;
                case "meio-elfo":
                    stats.setCharisma(stats.getCharisma() + 2);
                    // Adicione lógica para +1 em dois outros atributos
                    break;
                case "meio-orc":
                    stats.setStrength(stats.getStrength() + 2);
                    stats.setConstitution(stats.getConstitution() + 1);
                    break;
                case "tiefling":
                    stats.setCharisma(stats.getCharisma() + 2);
                    stats.setIntelligence(stats.getIntelligence() + 1);
                    break;
                default:
                    break;
            }
        }
    }
}