package com.learn.demo.integration;

import com.learn.demo.cnapsrepo.EmployeeCnapsRepository;
import com.learn.demo.repository.EmployeeInternRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmployeeIT {
    @Autowired
    private EmployeeInternRepository employeeInternRepository;
    @Autowired
    private EmployeeCnapsRepository employeeCnapsRepository;
    @Test
    public void check_if_database_ok(){
        Assertions.assertNotNull(employeeInternRepository.findAll());
    }
    @Test
    public void check_if_cnaps_database_ok(){
        Assertions.assertNotNull(employeeCnapsRepository.findAll());
    }
}
