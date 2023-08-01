package com.learn.demo.repository;

import com.learn.demo.repository.entity.EmployeeEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
//    @Query(value = "SELECT (phone_number) FROM employee e ", nativeQuery=true)
//    List<String> findAllPhoneNumber();
//
//    @Query(value = "SELECT e.phone_number FROM employee e WHERE e.phone_number LIKE %1?%", nativeQuery=true)
//    List<String> filterPhoneNumber( String phoneNumber);
//
//    @Query(value = "SELECT e FROM employee e WHERE e.last_name LIKE %1?% AND e.sex=?2 ORDER BY e.last_name ?3", nativeQuery = true)
//    List<EmployeeEntity> findEmployeeByLastNameAndSex(String lastName, String sex, String order);
//
//    @Query(value = "SELECT e FROM employee e WHERE e.last_name LIKE %1?% ORDER BY e.last_name ?2", nativeQuery=true)
//    List<EmployeeEntity> findAllEmployeeByLastName(String lastName, String order);
//
//    @Query(value = "SELECT e FROM employee e WHERE e.first_name LIKE %1?% ORDER BY e.first_name ?2", nativeQuery = true)
//    List<EmployeeEntity> findAllEmployeeByFirstName(String firstName, String order);
//
//    @Query(value = "SELECT e FROM employee e WHERE e.first_name LIKE %1?% AND e.sex=?2 ORDER BY e.first_name ?3", nativeQuery=true)
//    List<EmployeeEntity> findEmployeeByFirstNameAndSex(String firstName, String sex, String order);
//
//    @Query(value = "SELECT e FROM employee e WHERE e.function LIKE %1?% AND e.sex=?2 ORDER BY e.function ?3", nativeQuery=true)
//    List<EmployeeEntity> findEmployeeByFunctionAndSex(String function, String sex, String order);
//
//    @Query(value = "SELECT e FROM employee e WHERE e.function LIKE %1?% ORDER BY e.function ?2", nativeQuery = true)
//    List<EmployeeEntity> findAllEmployeeByFunction(String function, String order);
//
//    @Query(value = "SELECT e FROM employee e ORDER BY e.id" + " ?1", nativeQuery=true)
//    List<EmployeeEntity> findAllEmployeeOrderedById(String order);
//
//    // after provided hiring date and before provided departure date
//    @Query(value = "SELECT e FROM employee e WHERE e.hiring_date >= ?1 AND e.departure_date <= ?2 AND e.sex=?3 ORDER BY e.id ?4", nativeQuery=true)
//    List<EmployeeEntity> findEmployeeByHiringAndDepartureDateAndSex(String hiringDate, String departureDate, String sex, String order);
//
//    @Query(value = "SELECT e FROM employee e WHERE e.hiring_date >= ?1 AND e.departure_date <= ?2 ORDER BY e.id ?4", nativeQuery=true)
//    List<EmployeeEntity> findAllEmployeeByHiringAndDepartureDate(String hiringDate, String departureDate, String order);

}