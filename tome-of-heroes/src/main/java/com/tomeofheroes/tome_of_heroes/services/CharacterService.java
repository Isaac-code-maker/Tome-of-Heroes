package com.tomeofheroes.tome_of_heroes.services;

import com.tomeofheroes.tome_of_heroes.dto.CharacterDTO;
import com.tomeofheroes.tome_of_heroes.dto.ClasseDTO;
import com.tomeofheroes.tome_of_heroes.dto.RaceDTO;
import com.tomeofheroes.tome_of_heroes.models.Character;
import com.tomeofheroes.tome_of_heroes.models.Classe;
import com.tomeofheroes.tome_of_heroes.models.Race;
import com.tomeofheroes.tome_of_heroes.repository.CharacterRepository;
import com.tomeofheroes.tome_of_heroes.repository.ClasseRepository;
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

    @Autowired
    private ClasseRepository classeRepository;

    public List<Character> getCharacters() {
        return characterRepository.findAll();
    }

    public Character getCharacter(UUID id) {
        return characterRepository.findById(id).orElse(null);
    }

    public CharacterDTO createCharacter(Character character) {
        // Carregue a classe completa do banco de dados
        UUID idClasse = character.getClasse().getId_classe();
        Classe classe = classeRepository.findById(idClasse)
                .orElseThrow(() -> new IllegalArgumentException("Class not found with id: " + idClasse));
        character.setClasse(classe);

        // Carregue a raça completa do banco de dados (se necessário)
        UUID idRace = character.getRace().getId_raca();
        Race race = raceRepository.findById(idRace)
                .orElseThrow(() -> new IllegalArgumentException("Race not found with id: " + idRace));
        character.setRace(race);

        // Distribua os pontos de atributos e ajuste com base na raça
        distributeStatsBasedOnClass(character);
        adjustStatsBasedOnRace(character);

        // Salva o personagem no banco de dados
        Character savedCharacter = characterRepository.save(character);

        // Converte a entidade salva para DTO
        return convertToDTO(savedCharacter);
    }

    public Character updateCharacter(UUID id, Character character) {
        Character characterToUpdate = characterRepository.findById(id).orElse(null);
        if (characterToUpdate == null) {
            return null;
        }
        characterToUpdate.setName(character.getName());
        characterToUpdate.setRace(character.getRace());
        characterToUpdate.setClasse(character.getClasse()); // Atualize a classe do personagem
        // Distribuir os pontos de atributos com base na classe do personagem
        distributeStatsBasedOnClass(characterToUpdate);
        // Ajustar os atributos com base na raça do personagem
        adjustStatsBasedOnRace(characterToUpdate);
        return characterRepository.save(characterToUpdate);
    }

    public void deleteCharacter(UUID id) {
        characterRepository.deleteById(id);
    }

    private void distributeStatsBasedOnClass(Character character) {
        if (character.getClasse() == null || character.getClasse().getName() == null) {
            throw new IllegalArgumentException("Class and Class name must be provided");
        }

        String characterClass = character.getClasse().getName().toLowerCase();

        switch (characterClass) {
            case "bárbaro":
            case "guerreiro":
                character.setStrength(15);
                character.setConstitution(14);
                character.setDexterity(13);
                character.setWisdom(12);
                character.setCharisma(10);
                character.setIntelligence(8);
                break;
            case "paladino":
                character.setStrength(15);
                character.setCharisma(14);
                character.setConstitution(13);
                character.setWisdom(12);
                character.setDexterity(10);
                character.setIntelligence(8);
                break;
            case "ladino":
                character.setDexterity(15); // Corrigido aqui
                character.setIntelligence(14);
                character.setWisdom(13);
                character.setConstitution(12);
                character.setCharisma(10);
                character.setStrength(8);
                break;
            case "monge":
                character.setDexterity(15);
                character.setWisdom(14);
                character.setConstitution(13);
                character.setIntelligence(12);
                character.setCharisma(10);
                character.setStrength(8);
                break;
            case "patrulheiro":
                character.setDexterity(15);
                character.setWisdom(14);
                character.setConstitution(13);
                character.setIntelligence(12);
                character.setCharisma(10);
                character.setStrength(8);
                break;
            case "mago":
            case "artífice":
                character.setIntelligence(15);
                character.setConstitution(14);
                character.setDexterity(13);
                character.setWisdom(12);
                character.setCharisma(10);
                character.setStrength(8);
                break;
            case "clérigo":
                character.setWisdom(15);
                character.setConstitution(14);
                character.setStrength(13);
                character.setIntelligence(12);
                character.setCharisma(10);
                character.setDexterity(8);
                break;
            case "druida":
                character.setWisdom(15);
                character.setConstitution(14);
                character.setDexterity(13);
                character.setIntelligence(12);
                character.setCharisma(10);
                character.setStrength(8);
                break;
            case "feiticeiro":
            case "bruxo":
            case "bardo":
                character.setCharisma(15);
                character.setConstitution(14);
                character.setDexterity(13);
                character.setWisdom(12);
                character.setIntelligence(10);
                character.setStrength(8);
                break;
            default:
                throw new IllegalArgumentException("Invalid class: " + characterClass);
        }
    }

    private void adjustStatsBasedOnRace(Character character) {
        Race race = character.getRace();

        if (race != null && race.getName() != null) {
            switch (race.getName().toLowerCase()) {
                case "humano":
                    character.setStrength(character.getStrength() + 1);
                    character.setDexterity(character.getDexterity() + 1);
                    character.setConstitution(character.getConstitution() + 1);
                    character.setIntelligence(character.getIntelligence() + 1);
                    character.setWisdom(character.getWisdom() + 1);
                    character.setCharisma(character.getCharisma() + 1);
                    break;
                case "anão":
                    character.setConstitution(character.getConstitution() + 2);
                    break;
                case "elfo":
                    character.setDexterity(character.getDexterity() + 2);
                    break;
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
                    // Adicione lógica para +1 em dois outros atributos
                    break;
                case "meio-orc":
                    character.setStrength(character.getStrength() + 2);
                    character.setConstitution(character.getConstitution() + 1);
                    break;
                case "tiefling":
                    character.setCharisma(character.getCharisma() + 2);
                    character.setIntelligence(character.getIntelligence() + 1);
                    break;
                default:
                    break;
            }
        }
    }

    // public CharacterDTO createCharacter(Character character) {
    // // Salva o personagem no banco de dados
    // Character savedCharacter = characterRepository.save(character);

    // // Converte a entidade para DTO
    // return convertToDTO(savedCharacter);
    // }

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

        // Converte a classe para ClasseDTO
        ClasseDTO classeDTO = new ClasseDTO();
        classeDTO.setId_classe(character.getClasse().getId_classe());
        classeDTO.setName(character.getClasse().getName());
        classeDTO.setDescription(character.getClasse().getDescription());
        classeDTO.setDado_de_vida(character.getClasse().getDado_de_vida());
        dto.setClasse(classeDTO);

        // Converte a raça para RaceDTO
        RaceDTO raceDTO = new RaceDTO();
        raceDTO.setId_raca(character.getRace().getId_raca());
        raceDTO.setName(character.getRace().getName());
        raceDTO.setDescription(character.getRace().getDescription());
        dto.setRace(raceDTO);

        return dto;
    }
}