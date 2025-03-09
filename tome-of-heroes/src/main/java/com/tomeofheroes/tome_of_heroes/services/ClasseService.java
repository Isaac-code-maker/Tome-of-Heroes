package com.tomeofheroes.tome_of_heroes.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.tomeofheroes.tome_of_heroes.models.Classe;
import com.tomeofheroes.tome_of_heroes.repository.ClasseRepository;

@Service
public class ClasseService {

    private final ClasseRepository classeRepository;

    // Construtor que injeta o repositório ClasseRepository
    public ClasseService(ClasseRepository classeRepository) {
        this.classeRepository = classeRepository;
    }

    // Método para obter todas as classes
    public List<Classe> getClasses() {
        return classeRepository.findAll();
    }

    // Método para obter uma classe pelo ID
    public Classe getClasse(UUID id) {
        return classeRepository.findById(id).orElse(null);
    }

    // Método para criar uma nova classe
    public Classe createClasse(Classe classe) {
        return classeRepository.save(classe);
    }

    // Método para atualizar uma classe existente
    public Classe updateClasse(UUID id, Classe classe) {
        Classe classeToUpdate = classeRepository.findById(id).orElse(null);
        if (classeToUpdate == null) {
            return null;
        }
        classeToUpdate.setName(classe.getName());
        classeToUpdate.setDescription(classe.getDescription());
        classeToUpdate.setDado_de_vida(classe.getDado_de_vida());
        return classeRepository.save(classeToUpdate);
    }

    // Método para deletar uma classe pelo ID
    public void deleteClasse(UUID id) {
        classeRepository.deleteById(id);
    }
}