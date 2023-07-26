package com.learn.demo.service;

import com.learn.demo.repository.EmployeeRepository;
import com.learn.demo.repository.entity.EmployeeEntity;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class CSVService {

    private EmployeeRepository employeeRepository;

    public void writeEmployeesToCsv(Writer writer, List<EmployeeEntity> employeeEntities) {
        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
            csvPrinter.printRecord("ID", "First Name",
                    "Last Name","Address","Children",
                    "Birthdate","Email Perso","Email Pro",
                    "CIN Delivrance Location","CIN Issue Date","CIN Number",
                    "CNAPS Number","Function","Hiring Date",
                    "Departure Date","Phone Nu,bers","Sex",
                    "Socio Pro Category","Matricule","Image");
            for (EmployeeEntity employee : employeeEntities) {
                csvPrinter.printRecord(employee.getId(), employee.getFirstName(),
                        employee.getLastName(), employee.getAddress(), employee.getChildren(),
                        employee.getBirthDate(), employee.getEmailPerso(), employee.getEmailPro(),
                        employee.getCINDelivranceLocation(), employee.getCINIssueDate(), employee.getCINNumber(),
                        employee.getCnapsNumber(), employee.getFunction(), employee.getHiringDate(),
                        employee.getDepartureDate(), employee.getPhoneNumber(), employee.getSex(),
                        employee.getSocioProCategory(), employee.getMatricule(), employee.getImage());
            }
        } catch (IOException e) {
            log.error("Error While writing CSV ", e);
        }
    }
}