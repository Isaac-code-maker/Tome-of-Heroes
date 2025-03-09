package com.tomeofheroes.tome_of_heroes.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.tomeofheroes.tome_of_heroes.models.Spells;

public interface SpellsRepository extends JpaRepository<Spells, UUID> {
}