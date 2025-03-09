package com.tomeofheroes.tome_of_heroes.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.tomeofheroes.tome_of_heroes.repository.InventoryRepository;
import com.tomeofheroes.tome_of_heroes.models.Inventory;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public List<Inventory> getInventory() {
        return inventoryRepository.findAll();
    }

    public Inventory getInventory(UUID id) {
        return inventoryRepository.findById(id).orElse(null);
    }

    public Inventory createInventory(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    public Inventory updateInventory(UUID id, Inventory inventory) {
        List<Inventory> inventoryList = inventoryRepository.findById_inventory(id);
        if (inventoryList.isEmpty()) {
            return null;
        }
        Inventory inventoryToUpdate = inventoryList.get(0);
        if (inventoryToUpdate == null) {
            return null;
        }
        inventoryToUpdate.setGold(inventory.getGold());
        inventoryToUpdate.setSilver(inventory.getSilver());
        inventoryToUpdate.setCopper(inventory.getCopper());
        return inventoryRepository.save(inventoryToUpdate);
    }

}
