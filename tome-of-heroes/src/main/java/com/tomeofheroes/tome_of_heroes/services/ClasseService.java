package com.tomeofheroes.tome_of_heroes.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.tomeofheroes.tome_of_heroes.models.Classe;
import com.tomeofheroes.tome_of_heroes.repository.ClasseRepository;

@Service
public class ClasseService {

    private final ClasseRepository classeRepository;

    public ClasseService(ClasseRepository classeRepository) {
        this.classeRepository = classeRepository;
    }

    public List<Classe> getClasses() {
        return classeRepository.findAll();
    }

    public Classe getClasse(UUID id) {
        return classeRepository.findById(id).orElse(null);
    }

    public Classe createClasse(Classe classe) {
        return classeRepository.save(classe);
    }

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

    public void deleteClasse(UUID id) {
        classeRepository.deleteById(id);
    }
}