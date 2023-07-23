package com.learn.demo.service;

import com.learn.demo.repository.EntrepriseRepository;
import com.learn.demo.repository.entity.EntrepriseEntity;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.Random;

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
