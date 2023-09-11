package com.learn.demo.service;

import com.learn.demo.model.Employee;
import com.learn.demo.repository.entity.EmployeeEntity;
import org.springframework.data.jpa.domain.Specification;

public class EmployeeSpecifications {

    public static Specification<EmployeeEntity> firstNameContains(String firstName) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("firstName")), "%" + firstName.toLowerCase() + "%");
    }

    public static Specification<EmployeeEntity> lastNameContains(String lastName) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("lastName")), "%" + lastName.toLowerCase() + "%");
    }

    public static Specification<EmployeeEntity> functionContains(String function) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("function")), "%" + function.toLowerCase() + "%");
    }

    public static Specification<EmployeeEntity> sexEqual(Employee.Sex sex) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("sex"), sex);
    }
}