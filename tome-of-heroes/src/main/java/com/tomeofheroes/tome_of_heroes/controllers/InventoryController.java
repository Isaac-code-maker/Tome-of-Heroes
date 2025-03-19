package com.tomeofheroes.tome_of_heroes.controllers;

import com.tomeofheroes.tome_of_heroes.models.Inventory;
import com.tomeofheroes.tome_of_heroes.services.InventoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/inventories")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    // Endpoint para obter todos os itens de inventário
    @GetMapping
    public ResponseEntity<List<Inventory>> getInventory() {
        List<Inventory> inventory = inventoryService.getInventory();
        return ResponseEntity.ok(inventory);
    }

    // Endpoint para obter um item de inventário pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Inventory> getInventoryById(@PathVariable UUID id) {
        Inventory inventory = inventoryService.getInventory(id);
        if (inventory != null) {
            return ResponseEntity.ok(inventory);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint para criar um novo item de inventário
    @PostMapping
    public ResponseEntity<Inventory> createInventory(@RequestBody Inventory inventory) {
        Inventory newInventory = inventoryService.createInventory(inventory);
        return ResponseEntity.ok(newInventory);
    }

    // Endpoint para atualizar um item de inventário existente
    @PutMapping("/{id}")
    public ResponseEntity<Inventory> updateInventory(@PathVariable UUID id, @RequestBody Inventory inventory) {
        Inventory updatedInventory = inventoryService.updateInventory(id, inventory);
        if (updatedInventory != null) {
            return ResponseEntity.ok(updatedInventory);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint para remover um item do inventário
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeItem(@PathVariable UUID itemId, @RequestParam int quantity) {
        inventoryService.removeItem(itemId, quantity);
        return ResponseEntity.noContent().build();
    }

    // Endpoint para converter moeda (cobre, prata, ouro)
    @PostMapping("/{inventoryId}/convert-currency")
    public ResponseEntity<Void> convertCurrency(
            @PathVariable UUID inventoryId,
            @RequestParam String fromCurrency,
            @RequestParam String toCurrency,
            @RequestParam int amount) {
        inventoryService.convertCurrency(inventoryId, fromCurrency, toCurrency, amount);
        return ResponseEntity.noContent().build();
    }

    // Endpoint para adicionar um item ao inventário
    @PostMapping("/add-item")
    public ResponseEntity<Void> addItem(@RequestBody Inventory newItem) {
        inventoryService.addItem(newItem);
        return ResponseEntity.noContent().build();
    }

    // Endpoint para buscar itens no inventário de um personagem
    @GetMapping("/search")
    public ResponseEntity<List<Inventory>> searchItems(
            @RequestParam UUID characterId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String tipo) {
        List<Inventory> items = inventoryService.searchItems(characterId, name, tipo);
        return ResponseEntity.ok(items);
    }
}