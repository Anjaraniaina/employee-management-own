package com.learn.demo.cnapsrepo.entity;

import com.learn.demo.model.Employee;
import com.learn.demo.repository.entity.PhoneNumberEntity;
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
public class EmployeeCnapsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "end_to_end_id")
    private Long endToEndId;
    private String matricule;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "birth_date")
    private LocalDate birthDate;
    @Column(columnDefinition = "TEXT")
    private String image;
    private String address;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private Employee.Sex sex;
    @Column(name = "email_pro")
    private String emailPro;
    @Column(name = "email_perso")
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
    @Column(name = "cnaps_number")
    private String cnapsNumber;
    @Enumerated(EnumType.STRING)
    @Column(name = "socio_pro_category")
    private Employee.CSP socioProCategory;
}
