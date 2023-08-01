package com.learn.demo.controller.mapper;

import com.learn.demo.repository.entity.PhoneNumberEntity;
import com.learn.demo.service.EmployeeService;
import com.learn.demo.service.PhoneNumberService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class PhoneNumberMapper {
    private PhoneNumberService phoneNumberService;
    private EmployeeService employeeService;
    public String toModel(List<PhoneNumberEntity> phoneNumberEntityList){
            StringBuilder phoneNumbersBuilder = new StringBuilder();
            for (PhoneNumberEntity phoneNumberEntity : phoneNumberEntityList) {
                phoneNumbersBuilder.append("+").append(phoneNumberEntity.getCountryCode()).append(" ")
                        .append(phoneNumberEntity.getNumber()).append(",");
            }
            if (phoneNumbersBuilder.length() > 0) {
                phoneNumbersBuilder.deleteCharAt(phoneNumbersBuilder.length() - 1); // Remove the trailing comma
            }
            return phoneNumbersBuilder.toString();

    }

    public List<PhoneNumberEntity> toDomain(String phoneNumbersModel, Long employeeId){

        String[] phoneNumbersArray = phoneNumbersModel.split(",");
        List<PhoneNumberEntity> phoneNumberEntities = new ArrayList<>();
        for (String phoneNumber : phoneNumbersArray) {
            String[] parts = phoneNumber.trim().split("\\s+", 2);
            if (parts.length == 2) {
                String countryCode = parts[0].substring(1); // Remove the leading "+"
                String number = parts[1];
                 PhoneNumberEntity saved = phoneNumberService.addPhoneNumber(PhoneNumberEntity.builder()
                        .employee(employeeService.getById(employeeId))
                        .countryCode(countryCode)
                        .number(number)
                        .build());
                phoneNumberEntities.add(saved);
            }
        }
        return phoneNumberEntities;
    }
}
