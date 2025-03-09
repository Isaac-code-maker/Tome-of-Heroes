package com.tomeofheroes.tome_of_heroes.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.tomeofheroes.tome_of_heroes.models.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, UUID> {

    // Exemplo de método personalizado para encontrar inventários por nome
    List<Inventory> findByName(String name);

    // Exemplo de método personalizado para encontrar inventários por id_inventory
    List<Inventory> findById_inventory(UUID id_inventory);
}