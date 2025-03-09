package com.tomeofheroes.tome_of_heroes.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.tomeofheroes.tome_of_heroes.models.Stats;
import com.tomeofheroes.tome_of_heroes.repository.StatsRepository;

@Service
public class StatsService {

    private final StatsRepository statsRepository;

    public StatsService(StatsRepository statsRepository) {
        this.statsRepository = statsRepository;
    }

    // Método para obter todas as estatísticas
    public List<Stats> getAllStats() {
        return statsRepository.findAll();
    }

    // Método para obter uma estatística pelo ID
    public Stats getStatsById(UUID id) {
        return statsRepository.findById(id).orElse(null);
    }

    // Método para criar uma nova estatística
    public Stats createStats(Stats stats) {
        return statsRepository.save(stats);
    }

    // Método para atualizar uma estatística existente
    public Stats updateStats(UUID id, Stats statsDetails) {
        Stats statsToUpdate = statsRepository.findById(id).orElse(null);
        if (statsToUpdate == null) {
            return null;
        }
        statsToUpdate.setStrength(statsDetails.getStrength());
        statsToUpdate.setDexterity(statsDetails.getDexterity());
        statsToUpdate.setConstitution(statsDetails.getConstitution());
        statsToUpdate.setIntelligence(statsDetails.getIntelligence());
        statsToUpdate.setWisdom(statsDetails.getWisdom());
        statsToUpdate.setCharisma(statsDetails.getCharisma());
        return statsRepository.save(statsToUpdate);
    }

    // Método para deletar uma estatística pelo ID
    public void deleteStats(UUID id) {
        statsRepository.deleteById(id);
    }
}