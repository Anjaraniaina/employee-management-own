package com.learn.demo.repository.entity;

import com.learn.demo.model.Employee;
import com.learn.demo.repository.entity.CIN;
import com.learn.demo.repository.entity.PhoneNumber;
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
    private Long id;
    private String matricule;
    private String lastName;
    private String firstName;
    private LocalDate birthDate;
    @Column(columnDefinition = "TEXT")
    private String image;
    private String address;
    @OneToMany(mappedBy = "employee")
    private List<PhoneNumber> phones;
    private Employee.Sex sex;
    private String emailPro;
    private String emailPerso;
    @OneToOne
    @JoinColumn(name="cin_number", unique=true)
    private CIN cin;
    private Integer children;
    private String function;
    private LocalDate hiringDate;
    private LocalDate departureDate;
    private String cnapsNumber;
    private Employee.CSP socioProCategory;
}
