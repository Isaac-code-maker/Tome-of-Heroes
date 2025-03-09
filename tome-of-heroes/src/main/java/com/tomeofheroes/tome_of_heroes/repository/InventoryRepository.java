package com.tomeofheroes.tome_of_heroes.repository;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.tomeofheroes.tome_of_heroes.models.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, UUID> {
    // Método personalizado para encontrar inventários pelo ID
    Optional<Inventory> findById(UUID id);
}