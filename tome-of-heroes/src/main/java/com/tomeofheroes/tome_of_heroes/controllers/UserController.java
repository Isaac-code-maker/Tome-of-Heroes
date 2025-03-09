package com.tomeofheroes.tome_of_heroes.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tomeofheroes.tome_of_heroes.models.User;
import com.tomeofheroes.tome_of_heroes.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    // Construtor que injeta o serviço UserService
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Método para obter todos os usuários
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // Método para obter um usuário pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable UUID id) {
        User user = userService.getUserById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Método para criar um novo usuário
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    // Método para atualizar um usuário existente
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable UUID id, @RequestBody User userDetails) {
        User updatedUser = userService.updateUser(id, userDetails);
        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Método para deletar um usuário pelo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
