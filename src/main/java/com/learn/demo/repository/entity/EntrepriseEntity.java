package com.learn.demo.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Columns;

@Entity
@Table(name = "\"entreprise\"")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EntrepriseEntity {
    @Id
    private Long id;
    private String name;
    private String description;
    private String motto;
    private String address;
    private String email;
    @OneToOne
    @PrimaryKeyJoinColumn(name = "fiscal_identity")
    private FiscalIdentity fiscalIdentity;
    private String logo;
}
