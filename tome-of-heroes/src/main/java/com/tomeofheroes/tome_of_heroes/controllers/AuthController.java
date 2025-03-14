package com.tomeofheroes.tome_of_heroes.controllers;

import com.tomeofheroes.tome_of_heroes.Enum.Role;
import com.tomeofheroes.tome_of_heroes.dto.LoginRequestDTO;
import com.tomeofheroes.tome_of_heroes.dto.RegisterRequestDTO;
import com.tomeofheroes.tome_of_heroes.dto.JwtAuthenticationResponse;
import com.tomeofheroes.tome_of_heroes.models.User;
import com.tomeofheroes.tome_of_heroes.repository.UserRepository;
import com.tomeofheroes.tome_of_heroes.security.JwtTokenProvider;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;



@RestController // Indica que esta classe é um controlador REST do Spring
@RequestMapping("/auth") // Define o mapeamento base para os endpoints deste controlador
public class AuthController {

    @Autowired // Injeta automaticamente uma instância do UserRepository, que é usado para
               // buscar e salvar usuários no banco de dados
    private UserRepository userRepository;

    @Autowired // Injeta automaticamente uma instância do PasswordEncoder, que é usado para
               // codificar senhas
    private PasswordEncoder passwordEncoder;

    @Autowired // Injeta automaticamente uma instância do AuthenticationManager, que é usado
               // para autenticar usuários
    private AuthenticationManager authenticationManager;

    @Autowired // Injeta automaticamente uma instância do JwtTokenProvider, que é usado para
               // gerar tokens JWT
    private JwtTokenProvider tokenProvider;

   
   

    @PostMapping("/register") // Define o mapeamento para o endpoint de registro de usuários
    public ResponseEntity<Map<String, String>> register(@Valid @RequestBody RegisterRequestDTO registerDTO,
            BindingResult result) {
        if (result.hasErrors()) {
            // Retorna erros de validação em JSON
            Map<String, String> erros = new HashMap<>();
            result.getFieldErrors()
                    .forEach(fieldError -> erros.put(fieldError.getField(), fieldError.getDefaultMessage()));
            return ResponseEntity.badRequest().body(erros);
        }

        // Verifica se o nome de usuário já existe
        Optional<User> existingUser = userRepository.findByUsername(registerDTO.getUsername());
        if (existingUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of("erro", "Usuário já existe com este nome de usuário"));
        }

        // Verifica se pelo menos um papel (role) foi atribuído ao usuário
        Set<Role> roles = registerDTO.getRoles();
        if (roles == null || roles.isEmpty()) {
            return ResponseEntity.badRequest()
                    .body(Map.of("erro", "Pelo menos um papel deve ser atribuído ao usuário"));
        }

        // Cria um novo usuário e define seus atributos
        User user = new User();
        user.setUsername(registerDTO.getUsername());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        user.setRoles(roles);

        // Salva o usuário no banco de dados
        userRepository.save(user);

        // Retorna uma resposta de sucesso em JSON
        Map<String, String> successResponse = new HashMap<>();
        successResponse.put("mensagem", "Usuário registrado com sucesso!");
        return ResponseEntity.ok(successResponse);
    }

    @PostMapping("/login") // Define o mapeamento para o endpoint de login de usuários
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequestDTO loginDTO, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(Map.of("erro", result.getFieldError().getDefaultMessage()));
        }

        // Autentica o usuário com base no nome de usuário e senha fornecidos
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDTO.getUsername(),
                        loginDTO.getPassword()));

        // Define o objeto de autenticação no contexto de segurança
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Gera um token JWT para o usuário autenticado
        String jwt = tokenProvider.generateToken(authentication);

        // Retorna o token JWT em uma resposta de sucesso
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }
}
