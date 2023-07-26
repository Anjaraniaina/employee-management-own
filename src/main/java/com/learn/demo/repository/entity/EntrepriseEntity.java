package com.learn.demo.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "\"entreprise\"")
public class EntrepriseEntity {
    @Id
    private Long id;
    private String name;
    private String description;
    private String motto;
    private String address;
    private String email;
    @OneToOne
    @JoinColumn(name="fiscal_identity_id", unique=true)
    private FiscalIdentity fiscalIdentity;
    @Column(columnDefinition = "TEXT")
    private String logo;
}
