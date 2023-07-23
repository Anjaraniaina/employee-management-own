package com.learn.demo.service;

import com.learn.demo.repository.EmployeeRepository;
import com.learn.demo.repository.entity.EmployeeEntity;
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
public class EmployeeService {
    private EmployeeRepository employeeRepository;

    public EmployeeEntity getById(Long id){
        Optional<EmployeeEntity> employee = employeeRepository.findById(id);
        if(employee.isPresent()){
            return employee.get();
        } else {
            throw new RuntimeException();
        }
    }

    public EmployeeEntity addEmployee(EmployeeEntity employee){
        LocalDate today = LocalDate.now();
        Random rand = new Random();
        employee.setMatricule("USER-"+today+"-"+rand.nextInt(10000));
        return employeeRepository.save(employee);
    }

    public List<EmployeeEntity> getAllEmployee(){
        return employeeRepository.findAll();
    }

    public List<EmployeeEntity> createOrUpdateEmployees(List<EmployeeEntity> toUpdate){
        return employeeRepository.saveAll(toUpdate);
    }

    public String imageFileToBase64(MultipartFile file) throws IOException {
        log.info("Encoded Base 64 image");
        byte [] fileByte = file.getBytes();
        System.out.println(Base64.getEncoder().encodeToString(fileByte));
        return Base64.getEncoder().encodeToString(fileByte);
    }
}
