package com.learn.demo.repository.dao;

import com.learn.demo.repository.entity.EmployeeEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
@AllArgsConstructor
public class EmployeeDao {
    @PersistenceContext
    private EntityManager entityManager;

    public List<EmployeeEntity> filterEmployees(String filterBy, String order, String sex, String keyword, LocalDate hiringDate, LocalDate departureDate) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<EmployeeEntity> query = builder.createQuery(EmployeeEntity.class);
        Root<EmployeeEntity> root = query.from(EmployeeEntity.class);

        List<Predicate> predicates = new ArrayList<>();

        // Filter by the selected attribute (firstName, lastName, or function)
        if (!Objects.equals(filterBy, null)) {
            predicates.add(builder.like(root.get(filterBy), "%" + keyword + "%"));
        }

        // Filter by sex if it's provided and not equal to "All"
        if (!Objects.equals(sex, null) && !"All".equalsIgnoreCase(sex)) {
            predicates.add(builder.equal(root.get("sex"), sex));
        }

        // Filter by hiring date


        if (hiringDate != null) {
            predicates.add(builder.greaterThanOrEqualTo(root.get("hiringDate"), hiringDate));
        }

        // Filter by departure date
        if (departureDate != null) {
            predicates.add(builder.lessThanOrEqualTo(root.get("departureDate"), departureDate));
        }

        query.where(predicates.toArray(new Predicate[0]));

        // Order the result based on the 'order' parameter
        if (order != null && order.equalsIgnoreCase("DESC")) {
            query.orderBy(builder.desc(root.get("id")));
        } else {
            query.orderBy(builder.asc(root.get("id")));
        }

        return entityManager.createQuery(query).getResultList();
    }
}