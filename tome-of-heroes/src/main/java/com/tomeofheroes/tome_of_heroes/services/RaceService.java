package com.tomeofheroes.tome_of_heroes.services;

import com.tomeofheroes.tome_of_heroes.models.Race;
import com.tomeofheroes.tome_of_heroes.repository.RaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RaceService {

    @Autowired
    private RaceRepository raceRepository;

    // Método para obter todas as raças
    public List<Race> getRaces() {
        return raceRepository.findAll();
    }

    // Método para obter uma raça pelo ID
    public Race getRace(UUID id) {
        return raceRepository.findById(id).orElse(null);
    }

    // Método para criar uma nova raça
    public Race createRace(Race race) {
        return raceRepository.save(race);
    }

    // Método para criar várias raças de uma vez
    public List<Race> createRaces(List<Race> races) {
        return raceRepository.saveAll(races);
    }

    // Método para atualizar uma raça existente
    public Race updateRace(UUID id, Race race) {
        Race raceToUpdate = raceRepository.findById(id).orElse(null);
        if (raceToUpdate == null) {
            return null;
        }
        raceToUpdate.setName(race.getName());
        raceToUpdate.setDescription(race.getDescription());
        raceToUpdate.setBonus(race.getBonus());
        return raceRepository.save(raceToUpdate);
    }

    // Método para deletar uma raça pelo ID
    public void deleteRace(UUID id) {
        raceRepository.deleteById(id);
    }
}