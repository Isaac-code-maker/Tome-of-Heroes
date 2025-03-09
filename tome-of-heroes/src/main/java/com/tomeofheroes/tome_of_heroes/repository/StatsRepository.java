package com.tomeofheroes.tome_of_heroes.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.tomeofheroes.tome_of_heroes.models.Stats;

public interface StatsRepository extends JpaRepository<Stats, UUID> {
}