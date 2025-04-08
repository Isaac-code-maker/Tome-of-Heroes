package com.tomeofheroes.tome_of_heroes.repository;

import com.tomeofheroes.tome_of_heroes.models.Character;
import com.tomeofheroes.tome_of_heroes.models.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, UUID> {

    // Método para encontrar um item pelo ID
    @SuppressWarnings("null")
    Optional<Inventory> findById(UUID id);

    // Método para encontrar itens de um personagem específico
    List<Inventory> findByCharacter(Character character);

    // Método para encontrar itens de um personagem com base no nome (busca parcial)
    List<Inventory> findByCharacterAndNameContaining(Character character, String name);

    // Método para encontrar itens de um personagem com base no tipo
    List<Inventory> findByCharacterAndTipo(Character character, String tipo);

    // Método para encontrar itens de um personagem com base no nome e tipo
    List<Inventory> findByCharacterAndNameContainingAndTipo(Character character, String name, String tipo);

    // Método para encontrar um item específico pelo nome e personagem
    Optional<Inventory> findByNameAndCharacter(String name, Character character);
}