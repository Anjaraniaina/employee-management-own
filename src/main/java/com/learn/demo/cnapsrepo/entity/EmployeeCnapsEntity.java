package com.learn.demo.cnapsrepo.entity;

import com.learn.demo.model.Employee;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "\"employee\"")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String matricule;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "first_name")
    private String firstName;
    private LocalDate birthDate;
    @Column(columnDefinition = "TEXT")
    private String image;
    private String address;
    @Column(name = "\"phone_number\"")
    private String phoneNumbers;
    @Enumerated(EnumType.STRING)
    private Employee.Sex sex;
    private String emailPro;
    private String emailPerso;
    @Column(name = "\"cin_issue_date\"")
    private LocalDate CINIssueDate;
    @Column(name = "\"cin_number\"")
    private String CINNumber;
    @Column(name = "\"cin_delivrance_location\"")
    private String CINDelivranceLocation;
    private Integer children;
    private String function;
    @Column(name = "hiring_date")
    private LocalDate hiringDate;
    @Column(name = "departure_date")
    private LocalDate departureDate;
    private String cnapsNumber;
    @Enumerated(EnumType.STRING)
    private Employee.CSP socioProCategory;
}
