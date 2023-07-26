package com.learn.demo.controller.mapper;

import com.learn.demo.model.Entreprise;
import com.learn.demo.repository.entity.EntrepriseEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class EntrepriseMapper {
    public Entreprise toModel(EntrepriseEntity entity){
        return Entreprise.builder()
                .name(entity.getName())
                .address(entity.getAddress())
                .motto(entity.getMotto())
                .email(entity.getEmail())
                .description(entity.getDescription())
                .nif(entity.getFiscalIdentity().getNif())
                .stat(entity.getFiscalIdentity().getStat())
                .rcs(entity.getFiscalIdentity().getRcs())
                .logo(entity.getLogo()).build();
    }
}
