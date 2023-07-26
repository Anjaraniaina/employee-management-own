package com.learn.demo.service;

import com.learn.demo.model.SearchForm;
import com.learn.demo.repository.EmployeeRepository;
import com.learn.demo.repository.entity.EmployeeEntity;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

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

    public List<String> getAllPhoneNumbers(){
        return employeeRepository.findAllPhoneNumber();
    }

    public List<String> filterPhoneNumbers(String phoneNumber){
        return employeeRepository.filterPhoneNumber(phoneNumber);
    }

    public boolean isUnique(List<String> phoneNumbers){
        try {
            phoneNumbers.forEach((phoneNumber)-> {
                if (!Objects.equals(employeeRepository.filterPhoneNumber(phoneNumber),List.of())){
                    throw new RuntimeException();
                }
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    public String imageFileToBase64(MultipartFile file) throws IOException {
        log.info("Encoded Base 64 image");
        byte [] fileByte = file.getBytes();
        System.out.println(Base64.getEncoder().encodeToString(fileByte));
        return Base64.getEncoder().encodeToString(fileByte);
    }

    public List<EmployeeEntity> filter(SearchForm searchForm) {
        if(searchForm.getHiringDate() != null){
            if (!Objects.equals(searchForm.getSex(), "All"))
                return employeeRepository.findEmployeeByHiringAndDepartureDateAndSex(
                        searchForm.getHiringDate().toString(),
                        searchForm.getDepartureDate().toString(),
                        searchForm.getSex()
                );
            else
                return employeeRepository.findAllEmployeeByHiringAndDepartureDate(
                        searchForm.getHiringDate().toString(),
                        searchForm.getDepartureDate().toString()
                );
        } else if(!Objects.equals(searchForm.getFilterBy(), null)){
            switch (searchForm.getFilterBy()) {
                case "firstName" -> {
                    if (!Objects.equals(searchForm.getSex(), "All"))
                        return employeeRepository.findEmployeeByFirstNameAndSex(searchForm.getKeyword(), searchForm.getSex());
                    else
                        return employeeRepository.findAllEmployeeByFirstName(searchForm.getKeyword());

                }
                case "lastName" -> {
                    if (!Objects.equals(searchForm.getSex(), "All"))
                        return employeeRepository.findEmployeeByLastNameAndSex(searchForm.getKeyword(), searchForm.getSex());
                    else
                        return employeeRepository.findAllEmployeeByLastName(searchForm.getKeyword());

                }
                case "function" -> {
                    if (!Objects.equals(searchForm.getSex(), "All"))
                        return employeeRepository.findEmployeeByFunctionAndSex(searchForm.getKeyword(), searchForm.getSex());
                    else
                        return employeeRepository.findAllEmployeeByFunction(searchForm.getKeyword());
                }
            }
        }
        return employeeRepository.findAllEmployeeOrderedById();
    }
}
