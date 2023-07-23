package com.learn.demo.repository.entity;

import com.learn.demo.model.Employee;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "\"phone_number\"")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhoneNumber {
    @Id
    private Long id;
    private String number;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private EmployeeEntity employee;
}
