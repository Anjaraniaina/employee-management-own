package com.learn.demo.service;

import com.learn.demo.repository.EntrepriseRepository;
import com.learn.demo.repository.entity.EntrepriseEntity;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class EntrepriseService {
    private EntrepriseRepository entrepriseRepository;

    public EntrepriseEntity getById(Long id){
        Optional<EntrepriseEntity> entreprise = entrepriseRepository.findById(id);
        if(entreprise.isPresent()){
            return entreprise.get();
        } else {
            throw new RuntimeException();
        }
    }

    public EntrepriseEntity addEntreprise(EntrepriseEntity entreprise){
        return entrepriseRepository.save(entreprise);
    }
}
