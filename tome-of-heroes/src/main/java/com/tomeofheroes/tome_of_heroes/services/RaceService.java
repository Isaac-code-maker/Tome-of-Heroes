package com.tomeofheroes.tome_of_heroes.services;

import org.springframework.stereotype.Service;

import com.tomeofheroes.tome_of_heroes.repository.RaceRepository;

@Service
public class RaceService {

    private final RaceRepository raceRepository;

    public RaceService(RaceRepository raceRepository) {
        this.raceRepository = raceRepository;
    }





    

}
