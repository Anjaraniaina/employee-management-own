package com.learn.demo.utils;

import com.learn.demo.repository.entity.EntrepriseEntity;
import com.learn.demo.repository.entity.FiscalIdentity;
import com.learn.demo.service.EntrepriseService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EntrepriseInfo {
    private static EntrepriseService entrepriseService;
    static EntrepriseEntity entreprise = entrepriseService.getById(1L);
    public static String entrepriseName = entreprise.getName();
    public static String entrepriseDescription = entreprise.getDescription();
    public static String entrepriseEmail = entreprise.getEmail();
    public static String entrepriseMotto = entreprise.getMotto();
    public static String entrepriseLogo = entreprise.getLogo();
    public static String entrepriseAddress = entreprise.getAddress();
    public static FiscalIdentity entrepriseFiscalIdentity = entreprise.getFiscalIdentity();

}
