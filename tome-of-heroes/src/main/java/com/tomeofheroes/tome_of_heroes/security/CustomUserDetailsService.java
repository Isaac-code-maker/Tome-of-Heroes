package com.tomeofheroes.tome_of_heroes.security;

import com.tomeofheroes.tome_of_heroes.models.User;
import com.tomeofheroes.tome_of_heroes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service // Indica que esta classe é um serviço do Spring, permitindo que seja injetada
         // em outros componentes
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired // Injeta automaticamente uma instância do UserRepository, que é usado para
               // buscar os usuários no banco de dados
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Busca o usuário no repositório pelo nome de usuário
        User user = userRepository.findByUsername(username)
                // Lança uma exceção se o usuário não for encontrado
                .orElseThrow(
                        () -> new UsernameNotFoundException("Usuário não encontrado com nome de usuário: " + username));

        // Retorna um objeto UserDetails com as informações do usuário, incluindo o nome
        // de usuário, a senha e as autoridades (roles) do usuário
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                user.getAuthorities());
    }
}
