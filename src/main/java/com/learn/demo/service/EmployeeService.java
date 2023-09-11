package com.learn.demo.service;

import com.learn.demo.model.Employee;
import com.learn.demo.repository.EmployeeInternRepository;
import com.learn.demo.repository.entity.EmployeeEntity;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
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
    private EmployeeInternRepository employeeInternRepository;

    public EmployeeEntity getById(Long id) {
        Optional<EmployeeEntity> employee = employeeInternRepository.findById(id);
        if (employee.isPresent()) {
            return employee.get();
        } else {
            throw new RuntimeException();
        }
    }


    public List<EmployeeEntity> getFilteredAndSortedEmployees(String firstName, String lastName, String function, Employee.Sex sex, Sort.Order order) {

        Sort sort = Sort.by(order);

        Specification<EmployeeEntity> spec = Specification.where(null);

        if (firstName != null) {
            spec = spec.and(EmployeeSpecifications.firstNameContains(firstName));
        }

        if (lastName != null) {
            spec = spec.and(EmployeeSpecifications.lastNameContains(lastName));
        }

        if (function != null) {
            spec = spec.and(EmployeeSpecifications.functionContains(function));
        }

        if (sex != null) {
            spec = spec.and(EmployeeSpecifications.sexEqual(sex));
        }

        return employeeInternRepository.findAll(spec, sort);
    }

    public EmployeeEntity addEmployee(EmployeeEntity employee) {
        LocalDate today = LocalDate.now();
        Random rand = new Random();
        employee.setMatricule("USER-" + today + "-" + rand.nextInt(10000));
        return employeeInternRepository.save(employee);
    }


    public List<EmployeeEntity> getAllEmployee() {
        return employeeInternRepository.findAll();
    }

    public List<EmployeeEntity> createOrUpdateEmployees(List<EmployeeEntity> toUpdate) {
        return employeeInternRepository.saveAll(toUpdate);
    }


    public String imageFileToBase64(MultipartFile file) throws IOException {
        log.info("Encoded Base 64 image");
        byte[] fileByte = file.getBytes();
        System.out.println(Base64.getEncoder().encodeToString(fileByte));
        return Base64.getEncoder().encodeToString(fileByte);
    }

}
