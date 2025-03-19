package com.tomeofheroes.tome_of_heroes.services;

import com.tomeofheroes.tome_of_heroes.models.Spells;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class DnDSpellService {

    private static final String API_URL = "https://api.open5e.com/spells/";

    private final RestTemplate restTemplate;

    public DnDSpellService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // Método para buscar todas as magias da API e mapear para o modelo Spells
    public List<Spells> getAllSpellsFromAPI() {
        try {
            SpellResponse response = restTemplate.getForObject(API_URL, SpellResponse.class);
            if (response != null && response.results() != null) {
                return Arrays.stream(response.results())
                        .map(this::mapToSpells)
                        .toList();
            }
        } catch (Exception e) {
            System.err.println("Erro ao buscar magias da API: " + e.getMessage());
        }
        return List.of();
    }

    // Método para mapear os dados da API para o modelo Spells
    private Spells mapToSpells(SpellApiResponse spellApiResponse) {
        if (spellApiResponse == null) {
            throw new IllegalArgumentException("A resposta da API não pode ser nula.");
        }

        Spells spell = new Spells();
        spell.setName(spellApiResponse.name()); // Acessando o campo diretamente
        spell.setDescription(spellApiResponse.desc()); // Acessando o campo diretamente
        spell.setLevel(spellApiResponse.level()); // Acessando o campo diretamente
        spell.setSchool(spellApiResponse.school()); // Acessando o campo diretamente
        return spell;
    }

    // Método para buscar uma magia pelo nome na API
    public Spells getSpellByNameFromAPI(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("O nome da magia não pode ser nulo ou vazio.");
        }

        try {
            String url = API_URL + "?name=" + name;
            SpellResponse response = restTemplate.getForObject(url, SpellResponse.class);
            if (response != null && response.results() != null && response.results().length > 0) {
                return mapToSpells(response.results()[0]);
            }
        } catch (Exception e) {
            System.err.println("Erro ao buscar magia por nome: " + e.getMessage());
        }
        return null;
    }

    // Classe para representar a resposta da API
    private record SpellResponse(SpellApiResponse[] results) {}

    // Classe para representar os dados de uma magia da API
    private record SpellApiResponse(String name, String desc, String level, String school) {}
}