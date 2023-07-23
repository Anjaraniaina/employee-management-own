package com.learn.demo.model;

import com.learn.demo.repository.entity.FiscalIdentity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Entreprise {
    private String name;
    private String description;
    private String motto;
    private String address;
    private String email;
    private FiscalIdentity identityFiscal;
    private String logo;
}
