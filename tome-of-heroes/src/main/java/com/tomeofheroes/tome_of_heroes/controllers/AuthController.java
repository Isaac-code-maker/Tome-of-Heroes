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

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@Valid @RequestBody RegisterRequestDTO registerDTO,
                                                        BindingResult result) {
        if (result.hasErrors()) {
            // Retorna erros de validação em JSON
            Map<String, String> erros = new HashMap<>();
            result.getFieldErrors()
                    .forEach(fieldError -> erros.put(fieldError.getField(), fieldError.getDefaultMessage()));
            return ResponseEntity.badRequest().body(erros);
        }

        Optional<User> existingUser = userRepository.findByUsername(registerDTO.getUsername());
        if (existingUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("erro", "Usuário já existe com este nome de usuário"));
        }

        Set<Role> roles = registerDTO.getRoles();
        if (roles == null || roles.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("erro", "Pelo menos um papel deve ser atribuído ao usuário"));
        }

        User user = new User();
        user.setUsername(registerDTO.getUsername());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        user.setRoles(roles);

        userRepository.save(user);

        // Retorna uma resposta de sucesso em JSON
        Map<String, String> successResponse = new HashMap<>();
        successResponse.put("mensagem", "Usuário registrado com sucesso!");
        return ResponseEntity.ok(successResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequestDTO loginDTO, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(Map.of("erro", result.getFieldError().getDefaultMessage()));
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDTO.getUsername(),
                        loginDTO.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken(authentication);

        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }
}
