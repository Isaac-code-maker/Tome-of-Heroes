package com.tomeofheroes.tome_of_heroes.services;

import com.tomeofheroes.tome_of_heroes.dto.CharacterDTO;
import com.tomeofheroes.tome_of_heroes.dto.ClasseDTO;
import com.tomeofheroes.tome_of_heroes.dto.RaceDTO;
import com.tomeofheroes.tome_of_heroes.models.Character;
import com.tomeofheroes.tome_of_heroes.models.Classe;
import com.tomeofheroes.tome_of_heroes.models.Race;
import com.tomeofheroes.tome_of_heroes.models.User;
import com.tomeofheroes.tome_of_heroes.repository.CharacterRepository;
import com.tomeofheroes.tome_of_heroes.repository.ClasseRepository;
import com.tomeofheroes.tome_of_heroes.repository.RaceRepository;
import com.tomeofheroes.tome_of_heroes.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class CharacterService {

    @Autowired
    private CharacterRepository characterRepository;

    @Autowired
    private RaceRepository raceRepository;

    @Autowired
    private ClasseRepository classeRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Character> getCharacters() {
        return characterRepository.findAll();
    }

    public Character getCharacter(UUID id) {
        return characterRepository.findById(id).orElse(null);
    }

    public CharacterDTO createCharacter(Character character) {
        validateCharacterData(character);
        
        character.setClasse(loadClasse(character.getClasse().getId_classe()));
        character.setRace(loadRace(character.getRace().getId_raca()));
        character.setUser(loadUser(character.getUser().getId()));

        distributeStatsBasedOnClass(character);
        adjustStatsBasedOnRace(character);
        updateSkillsBasedOnAttributes(character);
        
        Character savedCharacter = characterRepository.save(character);
        return convertToDTO(savedCharacter);
    }

    public Character updateCharacter(UUID id, Character character) {
        Character characterToUpdate = characterRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Character not found"));
            
        updateCharacterData(characterToUpdate, character);
        distributeStatsBasedOnClass(characterToUpdate);
        adjustStatsBasedOnRace(characterToUpdate);
        updateSkillsBasedOnAttributes(characterToUpdate);
        
        return characterRepository.save(characterToUpdate);
    }

    public void deleteCharacter(UUID id) {
        characterRepository.deleteById(id);
    }

    private void validateCharacterData(Character character) {
        if (character == null) {
            throw new IllegalArgumentException("Character cannot be null");
        }
        if (character.getUser() == null || character.getUser().getId() == null) {
            throw new IllegalArgumentException("User and User ID must be provided");
        }
        if (character.getClasse() == null || character.getClasse().getId_classe() == null) {
            throw new IllegalArgumentException("Class and Class ID must be provided");
        }
        if (character.getRace() == null || character.getRace().getId_raca() == null) {
            throw new IllegalArgumentException("Race and Race ID must be provided");
        }
    }

    private Classe loadClasse(UUID id) {
        return classeRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Class not found with id: " + id));
    }

    private Race loadRace(UUID id) {
        return raceRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Race not found with id: " + id));
    }

    private User loadUser(UUID id) {
        return userRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));
    }

    private void updateCharacterData(Character characterToUpdate, Character newData) {
        characterToUpdate.setName(newData.getName());
        characterToUpdate.setRace(newData.getRace());
        characterToUpdate.setClasse(newData.getClasse());
    }

    private void distributeStatsBasedOnClass(Character character) {
        if (character.getClasse() == null || character.getClasse().getName() == null) {
            throw new IllegalArgumentException("Class and Class name must be provided");
        }

        String characterClass = character.getClasse().getName().toLowerCase();

        switch (characterClass) {
            case "bárbaro":
            case "guerreiro":
                setStats(character, 15, 13, 14, 8, 12, 10);
                break;
            case "paladino":
                setStats(character, 15, 10, 13, 8, 12, 14);
                break;
            case "ladino":
                setStats(character, 8, 15, 12, 14, 13, 10);
                break;
            case "monge":
            case "patrulheiro":
                setStats(character, 8, 15, 13, 12, 14, 10);
                break;
            case "mago":
            case "artífice":
                setStats(character, 8, 13, 14, 15, 12, 10);
                break;
            case "clérigo":
                setStats(character, 13, 8, 14, 12, 15, 10);
                break;
            case "druida":
                setStats(character, 8, 13, 14, 12, 15, 10);
                break;
            case "feiticeiro":
            case "bruxo":
            case "bardo":
                setStats(character, 8, 13, 14, 10, 12, 15);
                break;
            default:
                throw new IllegalArgumentException("Invalid class: " + characterClass);
        }
    }

    private void setStats(Character character, int str, int dex, int con, int intel, int wis, int cha) {
        character.setStrength(str);
        character.setDexterity(dex);
        character.setConstitution(con);
        character.setIntelligence(intel);
        character.setWisdom(wis);
        character.setCharisma(cha);
    }

    private void adjustStatsBasedOnRace(Character character) {
        if (character.getRace() == null || character.getRace().getName() == null) return;

        switch (character.getRace().getName().toLowerCase()) {
            case "humano":
                adjustAllStats(character, 1);
                break;
            case "anão":
                character.setConstitution(character.getConstitution() + 2);
                break;
            case "elfo":
            case "halfling":
                character.setDexterity(character.getDexterity() + 2);
                break;
            case "draconato":
                character.setStrength(character.getStrength() + 2);
                character.setCharisma(character.getCharisma() + 1);
                break;
            case "gnomo":
                character.setIntelligence(character.getIntelligence() + 2);
                break;
            case "meio-elfo":
                character.setCharisma(character.getCharisma() + 2);
                break;
            case "meio-orc":
                character.setStrength(character.getStrength() + 2);
                character.setConstitution(character.getConstitution() + 1);
                break;
            case "tiefling":
                character.setCharisma(character.getCharisma() + 2);
                character.setIntelligence(character.getIntelligence() + 1);
                break;
        }
    }

    private void adjustAllStats(Character character, int value) {
        character.setStrength(character.getStrength() + value);
        character.setDexterity(character.getDexterity() + value);
        character.setConstitution(character.getConstitution() + value);
        character.setIntelligence(character.getIntelligence() + value);
        character.setWisdom(character.getWisdom() + value);
        character.setCharisma(character.getCharisma() + value);
    }

    private void updateSkillsBasedOnAttributes(Character character) {
        int strMod = calculateModifier(character.getStrength());
        int dexMod = calculateModifier(character.getDexterity());
        int intMod = calculateModifier(character.getIntelligence());
        int wisMod = calculateModifier(character.getWisdom());
        int chaMod = calculateModifier(character.getCharisma());

        character.setAthletics(strMod);
        setDexteritySkills(character, dexMod);
        setIntelligenceSkills(character, intMod);
        setWisdomSkills(character, wisMod);
        setCharismaSkills(character, chaMod);
    }

    private void setDexteritySkills(Character character, int mod) {
        character.setAcrobatics(mod);
        character.setSleightOfHand(mod);
        character.setStealth(mod);
    }

    private void setIntelligenceSkills(Character character, int mod) {
        character.setArcana(mod);
        character.setHistory(mod);
        character.setInvestigation(mod);
        character.setNature(mod);
        character.setReligion(mod);
    }

    private void setWisdomSkills(Character character, int mod) {
        character.setAnimalHandling(mod);
        character.setInsight(mod);
        character.setMedicine(mod);
        character.setPerception(mod);
        character.setSurvival(mod);
    }

    private void setCharismaSkills(Character character, int mod) {
        character.setDeception(mod);
        character.setIntimidation(mod);
        character.setPerformance(mod);
        character.setPersuasion(mod);
    }

    private int calculateModifier(int attributeValue) {
        return (attributeValue - 10) / 2;
    }

    private CharacterDTO convertToDTO(Character character) {
        CharacterDTO dto = new CharacterDTO();
        dto.setId(character.getId());
        dto.setName(character.getName());
        dto.setLevel(character.getLevel());
        dto.setExperience(character.getExperience());
        dto.setStrength(character.getStrength());
        dto.setDexterity(character.getDexterity());
        dto.setConstitution(character.getConstitution());
        dto.setIntelligence(character.getIntelligence());
        dto.setWisdom(character.getWisdom());
        dto.setCharisma(character.getCharisma());

        ClasseDTO classeDTO = new ClasseDTO();
        classeDTO.setId_classe(character.getClasse().getId_classe());
        classeDTO.setName(character.getClasse().getName());
        classeDTO.setDescription(character.getClasse().getDescription());
        classeDTO.setDado_de_vida(character.getClasse().getDado_de_vida());
        dto.setClasse(classeDTO);

        RaceDTO raceDTO = new RaceDTO();
        raceDTO.setId_raca(character.getRace().getId_raca());
        raceDTO.setName(character.getRace().getName());
        raceDTO.setDescription(character.getRace().getDescription());
        dto.setRace(raceDTO);

        return dto;
    }
}