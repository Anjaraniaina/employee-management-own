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
    private String countryCode;
    private String number;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private EmployeeEntity employee;
}
