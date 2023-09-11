package com.learn.demo.cnapsrepo;

import com.learn.demo.cnapsrepo.entity.EmployeeCnapsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeCnapsRepository extends JpaRepository<EmployeeCnapsEntity, Long> {
}