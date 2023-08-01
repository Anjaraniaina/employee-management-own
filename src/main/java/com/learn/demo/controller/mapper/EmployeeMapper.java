package com.learn.demo.controller.mapper;

import com.learn.demo.model.Employee;
import com.learn.demo.repository.entity.EmployeeEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@AllArgsConstructor
public class EmployeeMapper {
    private PhoneNumberMapper phoneNumberMapper;
    public Employee toModel(EmployeeEntity entity){
        return Employee.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .CINIssueDate(entity.getCINIssueDate())
                .CINNumber(entity.getCINNumber())
                .CINDelivranceLocation(entity.getCINDelivranceLocation())
                .emailPerso(entity.getEmailPerso())
                .address(entity.getAddress())
                .emailPro(entity.getEmailPro())
                .function(entity.getFunction())
                .hiringDate(entity.getHiringDate())
                .departureDate(entity.getDepartureDate())
                .image(entity.getImage())
                .matricule(entity.getMatricule())
                .sex(entity.getSex())
                .phoneNumber(phoneNumberMapper.toModel(entity.getPhoneNumbers()))
                .children(entity.getChildren())
                .cnapsNumber(entity.getCnapsNumber())
                .birthDate(entity.getBirthDate())
                .socioProCategory(entity.getSocioProCategory())
                .build();
    }

    public EmployeeEntity toDomain(Employee model){
        return EmployeeEntity.builder()
                .id(model.getId())
                .firstName(model.getFirstName())
                .lastName(model.getLastName())
                        .CINNumber(model.getCINNumber())
                        .CINDelivranceLocation(model.getCINDelivranceLocation())
                        .CINIssueDate(model.getCINIssueDate())
                .emailPerso(model.getEmailPerso())
                .address(model.getAddress())
                .emailPro(model.getEmailPro())
                .phoneNumbers(phoneNumberMapper.toDomain(model.getPhoneNumber(), model.getId()))
                .function(model.getFunction())
                .hiringDate(model.getHiringDate())
                .departureDate(model.getDepartureDate())
                .image(model.getImage())
                .matricule(model.getMatricule())
                .sex(model.getSex())
                .children(model.getChildren())
                .cnapsNumber(model.getCnapsNumber())
                .birthDate(model.getBirthDate())
                .socioProCategory(model.getSocioProCategory())
                .build();
    }
}
