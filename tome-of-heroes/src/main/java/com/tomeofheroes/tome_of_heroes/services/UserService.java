package com.tomeofheroes.tome_of_heroes.services;

import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import com.tomeofheroes.tome_of_heroes.models.User;
import com.tomeofheroes.tome_of_heroes.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Método para obter todos os usuários
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Método para obter um usuário pelo ID
    public User getUserById(UUID id) {
        return userRepository.findById(id).orElse(null);
    }

    // Método para criar um novo usuário
    public User createUser(User user) {
        return userRepository.save(user);
    }

    // Método para atualizar um usuário existente
    public User updateUser(UUID id, User userDetails) {
        User userToUpdate = userRepository.findById(id).orElse(null);
        if (userToUpdate == null) {
            return null;
        }
        userToUpdate.setUsername(userDetails.getUsername());
        userToUpdate.setPassword(userDetails.getPassword());
        userToUpdate.setRoles(userDetails.getRoles());
        return userRepository.save(userToUpdate);
    }

    // Método para deletar um usuário pelo ID
    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }
}
