package com.tomeofheroes.tome_of_heroes.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.tomeofheroes.tome_of_heroes.repository.InventoryRepository;
import com.tomeofheroes.tome_of_heroes.models.Inventory;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    // Construtor que injeta o repositório InventoryRepository
    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    // Método para obter todos os itens de inventário
    public List<Inventory> getInventory() {
        return inventoryRepository.findAll();
    }

    // Método para obter um item de inventário pelo ID
    public Inventory getInventory(UUID id) {
        return inventoryRepository.findById(id).orElse(null);
    }

    // Método para criar um novo item de inventário
    public Inventory createInventory(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    // Método para atualizar um item de inventário existente
    public Inventory updateInventory(UUID id, Inventory inventory) {
        Inventory inventoryToUpdate = inventoryRepository.findById(id).orElse(null);
        if (inventoryToUpdate == null) {
            return null;
        }
        inventoryToUpdate.setGold(inventory.getGold());
        inventoryToUpdate.setSilver(inventory.getSilver());
        inventoryToUpdate.setCopper(inventory.getCopper());
        return inventoryRepository.save(inventoryToUpdate);
    }

    // Método para deletar um item de inventário pelo ID
    public void deleteInventory(UUID id_item) {
        inventoryRepository.deleteById(id_item);
    }
}