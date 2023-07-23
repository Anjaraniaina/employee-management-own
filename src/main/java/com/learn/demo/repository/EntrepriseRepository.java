package com.learn.demo.repository;

import com.learn.demo.repository.entity.EmployeeEntity;
import com.learn.demo.repository.entity.EntrepriseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntrepriseRepository extends JpaRepository<EntrepriseEntity, Long> {
}
