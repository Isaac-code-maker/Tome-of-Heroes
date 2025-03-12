package com.tomeofheroes.tome_of_heroes.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tomeofheroes.tome_of_heroes.repository.CharacterRepository;
import com.tomeofheroes.tome_of_heroes.repository.InventoryRepository;
import com.tomeofheroes.tome_of_heroes.models.Inventory;

@Service
public class InventoryService {

    @Autowired
    private final CharacterRepository characterRepository;

    @Autowired
    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository, CharacterRepository characterRepository) {
        this.inventoryRepository = inventoryRepository;
        this.characterRepository = characterRepository;
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

    public void removeItem(UUID itemId, int quantity) {
        Optional<Inventory> itemOptional = inventoryRepository.findById(itemId);
        if (itemOptional.isPresent()) {
            Inventory item = itemOptional.get();
            if (item.getQuantidade() > quantity) {
                // Diminui a quantidade
                item.setQuantidade(item.getQuantidade() - quantity);
                inventoryRepository.save(item);
            } else {
                // Remove o item se a quantidade for zero
                inventoryRepository.delete(item);
            }
        }
    }

    public void convertCurrency(UUID inventoryId, String fromCurrency, String toCurrency, int amount) {
        Inventory inventory = inventoryRepository.findById(inventoryId).orElse(null);
        if (inventory == null) {
            throw new RuntimeException("Inventário não encontrado.");
        }

        switch (fromCurrency) {
            case "copper":
                if (inventory.getCopper() < amount) {
                    throw new RuntimeException("Saldo de cobre insuficiente.");
                }
                if (toCurrency.equals("silver")) {
                    inventory.setCopper(inventory.getCopper() - amount);
                    inventory.setSilver(inventory.getSilver() + (amount / 10));
                } else if (toCurrency.equals("gold")) {
                    inventory.setCopper(inventory.getCopper() - amount);
                    inventory.setGold(inventory.getGold() + (amount / 100));
                }
                break;
            case "silver":
                if (inventory.getSilver() < amount) {
                    throw new RuntimeException("Saldo de prata insuficiente.");
                }
                if (toCurrency.equals("copper")) {
                    inventory.setSilver(inventory.getSilver() - amount);
                    inventory.setCopper(inventory.getCopper() + (amount * 10));
                } else if (toCurrency.equals("gold")) {
                    inventory.setSilver(inventory.getSilver() - amount);
                    inventory.setGold(inventory.getGold() + (amount / 10));
                }
                break;
            case "gold":
                if (inventory.getGold() < amount) {
                    throw new RuntimeException("Saldo de ouro insuficiente.");
                }
                if (toCurrency.equals("copper")) {
                    inventory.setGold(inventory.getGold() - amount);
                    inventory.setCopper(inventory.getCopper() + (amount * 100));
                } else if (toCurrency.equals("silver")) {
                    inventory.setGold(inventory.getGold() - amount);
                    inventory.setSilver(inventory.getSilver() + (amount * 10));
                }
                break;
            default:
                throw new RuntimeException("Moeda de origem inválida.");
        }
        inventoryRepository.save(inventory);
    }

    public void addItem(Inventory newItem) {
        Optional<Inventory> existingItem = inventoryRepository.findByNameAndCharacter(newItem.getName(),
                newItem.getCharacter());
        if (existingItem.isPresent()) {
            // Se o item já existir, aumenta a quantidade
            Inventory item = existingItem.get();
            item.setQuantidade(item.getQuantidade() + newItem.getQuantidade());
            inventoryRepository.save(item);
        } else {
            // Se não existir, cria um novo item
            inventoryRepository.save(newItem);
        }
    }

    public List<Inventory> searchItems(UUID characterId, String name, String tipo) {
        Optional<com.tomeofheroes.tome_of_heroes.models.Character> characterOptional = characterRepository.findById(characterId);
        if (characterOptional.isEmpty()) {
            throw new RuntimeException("Personagem não encontrado.");
        }
        com.tomeofheroes.tome_of_heroes.models.Character character = characterOptional.get();

        if (name != null && tipo != null) {
            return inventoryRepository.findByCharacterAndNameContainingAndTipo(character, name, tipo);
        } else if (name != null) {
            return inventoryRepository.findByCharacterAndNameContaining(character, name);
        } else if (tipo != null) {
            return inventoryRepository.findByCharacterAndTipo(character, tipo);
        } else {
            return inventoryRepository.findByCharacter(character);
        }
    }
}