package com.learn.demo.repository;

import com.learn.demo.repository.entity.EmployeeEntity;
import com.learn.demo.repository.entity.PhoneNumberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneNumberRepository extends JpaRepository<PhoneNumberEntity, Long> {
}