package com.epf.API.mapper;

import com.epf.API.dto.PlantsDto;
import com.epf.persistence.model.Plants;
import org.springframework.stereotype.Component;

@Component
public class PlantsMapper {

    public PlantsDto toDTO(Plants plant) {
        if (plant == null) return null;
        
        PlantsDto dto = new PlantsDto();
        dto.setIdPlante(plant.getIdPlante());
        dto.setNom(plant.getNom());
        dto.setPointDeVie(plant.getPointDeVie());
        dto.setAttaqueParSeconde(plant.getAttaqueParSeconde());
        dto.setDegatAttaque(plant.getDegatAttaque());
        dto.setCout(plant.getCout());
        dto.setSoleilParSeconde(plant.getSoleilParSeconde());
        dto.setEffet(plant.getEffet());
        dto.setCheminImage(plant.getCheminImage());
        return dto;
    }

    public static Plants toEntity(PlantsDto dto) {
        if (dto == null) return null;
        
        Plants plant = new Plants();
        plant.setIdPlante(dto.getIdPlante());
        plant.setNom(dto.getNom());
        plant.setPointDeVie(dto.getPointDeVie());
        plant.setAttaqueParSeconde(dto.getAttaqueParSeconde());
        plant.setDegatAttaque(dto.getDegatAttaque());
        plant.setCout(dto.getCout());
        plant.setSoleilParSeconde(dto.getSoleilParSeconde());
        plant.setEffet(dto.getEffet());
        plant.setCheminImage(dto.getCheminImage());
        return plant;
    }
}

