package com.tomeofheroes.tome_of_heroes.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.tomeofheroes.tome_of_heroes.models.User;

public interface UserRepository extends JpaRepository<User, UUID> {
    // Método personalizado para encontrar um usuário pelo nome de usuário
    User findByUsername(String username);
}
