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
//    @Query(value = "SELECT (phone_number) FROM employee e", nativeQuery=true)
//    List<String> findAllPhoneNumber();
//
//    @Query(value = "SELECT (phone_number) FROM employee e WHERE e.phone_number LIKE %:phonenumber%", nativeQuery=true)
//    List<String> filterPhoneNumber(@Param("phonenumber") String phoneNumber);
//
//    @Query(value = "SELECT e FROM employee e WHERE e.lastName LIKE '%' || :firstname || '%' AND e.sex=:sex ORDER BY e.lastName", nativeQuery=true)
//    List<EmployeeEntity> findEmployeeByLastNameAndSex(@Param("lastname") String lastName, @Param("sex") String sex);
//
//    @Query(value = "SELECT e FROM employee e WHERE e.lastName LIKE '%' || :firstname || '%' ORDER BY e.lastName", nativeQuery=true)
//    List<EmployeeEntity> findAllEmployeeByLastName(@Param("lastname") String lastName);
//
//    @Query(value = "SELECT e FROM employee e WHERE e.firstName LIKE '%' || :firstname || '%' ORDER BY e.firstName", nativeQuery=true)
//    List<EmployeeEntity> findAllEmployeeByFirstName(@Param("firstname") String firstName);
//
//    @Query(value = "SELECT e FROM employee e WHERE e.firstName LIKE '%' || :firstname || '%' AND e.sex=:sex ORDER BY e.firstName", nativeQuery=true)
//    List<EmployeeEntity> findEmployeeByFirstNameAndSex(@Param("firstname") String firstName, @Param("sex") String sex);
//
//    @Query(value = "SELECT e FROM employee e WHERE e.function LIKE %:function% AND e.sex=:sex ORDER BY e.function", nativeQuery=true)
//    List<EmployeeEntity> findEmployeeByFunctionAndSex(@Param("function") String function, @Param("sex") String sex);
//
//    @Query(value = "SELECT e FROM employee e WHERE e.function LIKE %:function% ORDER BY e.function", nativeQuery=true)
//    List<EmployeeEntity> findAllEmployeeByFunction(@Param("function") String function);
//
//    @Query(value = "SELECT e FROM employee e ORDER BY e.id", nativeQuery=true)
//    List<EmployeeEntity> findAllEmployeeOrderedById(@Param("order") String order);
//
//    // after provided hiring date and before provided departure date
//    @Query(value = "SELECT e FROM employee e WHERE e.hiringDate >= :hiringdate AND e.departureDate <= :departuredate AND e.sex=:sex ORDER BY e.id", nativeQuery=true)
//    List<EmployeeEntity> findEmployeeByHiringAndDepartureDateAndSex(@Param("hiringdate") String hiringDate, @Param("departuredate") String departureDate, @Param("sex") String sex);
//
//    @Query(value = "SELECT e FROM employee e WHERE e.hiringDate >= :hiringdate AND e.departureDate <= :departuredate ORDER BY e.id", nativeQuery=true)
//    List<EmployeeEntity> findAllEmployeeByHiringAndDepartureDate(@Param("hiringdate") String hiringDate, @Param("departuredate") String departureDate);

    @Query(value = "SELECT phone_number FROM employee e", nativeQuery=true)
    List<String> findAllPhoneNumber();

    @Query(value = "SELECT e.phone_number FROM employee e WHERE e.phone_number LIKE CONCAT('%',:phonenumber,'%') ", nativeQuery = true)
    List<String> filterPhoneNumber(@Param("phonenumber") String phoneNumber);

    @Query(value = "SELECT e FROM EmployeeEntity e WHERE e.lastName LIKE '%' || :lastname || '%' AND e.sex = :sex ORDER BY e.lastName")
    List<EmployeeEntity> findEmployeeByLastNameAndSex(@Param("lastname") String lastName, @Param("sex") String sex);

    @Query(value = "SELECT e FROM EmployeeEntity e WHERE e.lastName LIKE '%' || :lastname || '%' ORDER BY e.lastName")
    List<EmployeeEntity> findAllEmployeeByLastName(@Param("lastname") String lastName);

    @Query(value = "SELECT e FROM EmployeeEntity e WHERE e.firstName LIKE '%' || :firstname || '%' ORDER BY e.firstName")
    List<EmployeeEntity> findAllEmployeeByFirstName(@Param("firstname") String firstName);

    @Query(value = "SELECT e FROM EmployeeEntity e WHERE e.firstName LIKE '%' || :firstname || '%' AND e.sex = :sex ORDER BY e.firstName")
    List<EmployeeEntity> findEmployeeByFirstNameAndSex(@Param("firstname") String firstName, @Param("sex") String sex);

    @Query(value = "SELECT e FROM EmployeeEntity e WHERE e.function LIKE '%' || :function || '%' AND e.sex = :sex ORDER BY e.function")
    List<EmployeeEntity> findEmployeeByFunctionAndSex(@Param("function") String function, @Param("sex") String sex);

    @Query(value = "SELECT e FROM EmployeeEntity e WHERE e.function LIKE '%' || :function || '%' ORDER BY e.function")
    List<EmployeeEntity> findAllEmployeeByFunction(@Param("function") String function);

    @Query(value = "SELECT e FROM EmployeeEntity e ORDER BY e.id")
    List<EmployeeEntity> findAllEmployeeOrderedById();

    // after provided hiring date and before provided departure date
    @Query(value = "SELECT e FROM EmployeeEntity e WHERE e.hiringDate >= :hiringdate AND e.departureDate <= :departuredate AND e.sex = :sex ORDER BY e.id")
    List<EmployeeEntity> findEmployeeByHiringAndDepartureDateAndSex(@Param("hiringdate") String hiringDate, @Param("departuredate") String departureDate, @Param("sex") String sex);

    @Query(value = "SELECT e FROM EmployeeEntity e WHERE e.hiringDate >= :hiringdate AND e.departureDate <= :departuredate ORDER BY e.id")
    List<EmployeeEntity> findAllEmployeeByHiringAndDepartureDate(@Param("hiringdate") String hiringDate, @Param("departuredate") String departureDate);

}