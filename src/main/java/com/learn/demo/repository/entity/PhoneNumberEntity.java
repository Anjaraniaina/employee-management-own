package com.learn.demo.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "\"phone_number\"")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class PhoneNumberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "country_code")
    private String countryCode;
    @Column(length = 10)
    private String number;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private EmployeeEntity employee;
}
