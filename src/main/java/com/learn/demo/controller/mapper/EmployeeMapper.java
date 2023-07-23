package com.learn.demo.controller.mapper;

import com.learn.demo.model.Employee;
import com.learn.demo.repository.entity.CIN;
import com.learn.demo.repository.entity.EmployeeEntity;
import com.learn.demo.service.CINService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class EmployeeMapper {
    private CINService cinService;
    public Employee toModel(EmployeeEntity entity){
        return Employee.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .CINIssueDate(entity.getCin().getIssueDate())
                .CINDelivranceLocation(entity.getCin().getDelivranceLocation())
                .CINNumber(entity.getCin().getNumber())
                .emailPerso(entity.getEmailPerso())
                .address(entity.getAddress())
                .emailPro(entity.getEmailPro())
                .function(entity.getFunction())
                .hiringDate(entity.getHiringDate())
                .departureDate(entity.getDepartureDate())
                .image(entity.getImage())
                .matricule(entity.getMatricule())
                .sex(entity.getSex())
                .phones(entity.getPhones())
                .children(entity.getChildren())
                .cnapsNumber(entity.getCnapsNumber())
                .birthDate(entity.getBirthDate())
                .socioProCategory(entity.getSocioProCategory())
                .build();
    }

    public EmployeeEntity toDomain(Employee model){
        return EmployeeEntity.builder()
                .firstName(model.getFirstName())
                .lastName(model.getLastName())
                .cin(CIN.builder()
                        .number(model.getCINNumber())
                        .delivranceLocation(model.getCINDelivranceLocation())
                        .issueDate(model.getCINIssueDate())
                        .build())
                .emailPerso(model.getEmailPerso())
                .address(model.getAddress())
                .emailPro(model.getEmailPro())
                .function(model.getFunction())
                .hiringDate(model.getHiringDate())
                .departureDate(model.getDepartureDate())
                .image(model.getImage())
                .matricule(model.getMatricule())
                .sex(model.getSex())
                .phones(model.getPhones())
                .children(model.getChildren())
                .cnapsNumber(model.getCnapsNumber())
                .birthDate(model.getBirthDate())
                .socioProCategory(model.getSocioProCategory())
                .build();
    }
}
