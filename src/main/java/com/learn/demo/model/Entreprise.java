package com.learn.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Entreprise {
    private String name;
    private String description;
    private String motto;
    private String address;
    private String email;
    private String nif;
    private String stat;
    private String rcs;
    private String logo;
}
