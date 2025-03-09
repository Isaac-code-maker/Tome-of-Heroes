package com.tomeofheroes.tome_of_heroes.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.tomeofheroes.tome_of_heroes.models.Race;
import com.tomeofheroes.tome_of_heroes.repository.RaceRepository;

@Service
public class RaceService {

    private final RaceRepository raceRepository;

    public RaceService(RaceRepository raceRepository) {
        this.raceRepository = raceRepository;
    }

    // Método para obter todas as raças
    public List<Race> getAllRaces() {
        return raceRepository.findAll();
    }

    // Método para obter uma raça pelo ID
    public Race getRaceById(UUID id) {
        return raceRepository.findById(id).orElse(null);
    }

    // Método para criar uma nova raça
    public Race createRace(Race race) {
        return raceRepository.save(race);
    }

    // Método para atualizar uma raça existente
    public Race updateRace(UUID id, Race raceDetails) {
        Race raceToUpdate = raceRepository.findById(id).orElse(null);
        if (raceToUpdate == null) {
            return null;
        }
        raceToUpdate.setName(raceDetails.getName());
        raceToUpdate.setBonus(raceDetails.getBonus());
        raceToUpdate.setDescription(raceDetails.getDescription());
        return raceRepository.save(raceToUpdate);
    }

    // Método para deletar uma raça pelo ID
    public void deleteRace(UUID id) {
        raceRepository.deleteById(id);
    }
}