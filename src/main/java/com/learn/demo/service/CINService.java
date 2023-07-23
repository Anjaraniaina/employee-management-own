package com.learn.demo.service;

import com.learn.demo.repository.CINRepository;
import com.learn.demo.repository.entity.CIN;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class CINService {
    private CINRepository cinRepository;

    public CIN getByNumber(String number){
        Optional<CIN> repository = cinRepository.findById(number);
        if(repository.isPresent()){
            return repository.get();
        } else {
            throw new RuntimeException();
        }
    }

    public List<CIN> getAllCIN(){
        return cinRepository.findAll();
    }

    public List<CIN> createOrUpdateCINs(List<CIN> toUpdate){
        return cinRepository.saveAll(toUpdate);
    }
}
