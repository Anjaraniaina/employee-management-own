package com.learn.demo.repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "\"fiscal_identity\"")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FiscalIdentity {
    @Id
    private Long id;
    private String nif;
    private String stat;
    private String rcs;
}
