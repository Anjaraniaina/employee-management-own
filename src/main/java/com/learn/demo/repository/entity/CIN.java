package com.learn.demo.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "\"cin\"")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CIN {
    @Id
    private String number;
    private LocalDate issueDate;
    private String delivranceLocation;
}
