package com.epf.API.mapper;

import com.epf.API.dto.PlantsDto;
import com.epf.persistence.model.Plants;
import org.springframework.stereotype.Component;


@Component
public class PlantsMapper {

    public PlantsDto toDTO(Plants plant) {
        if (plant == null) return null;
        
        PlantsDto dto = new PlantsDto();
        dto.setId_plante(plant.getIdPlante());
        dto.setNom(plant.getNom());
        dto.setPoint_de_vie(plant.getPointDeVie());
        dto.setAttaque_par_seconde(plant.getAttaqueParSeconde());  // No conversion needed
        dto.setDegat_attaque(plant.getDegatAttaque());
        dto.setCout(plant.getCout());
        dto.setSoleil_par_seconde(plant.getSoleilParSeconde());    // No conversion needed
        dto.setEffet(plant.getEffet());
        dto.setChemin_image(plant.getCheminImage());
        return dto;
    }

    public Plants toEntity(PlantsDto dto) {
        if (dto == null) return null;
        
        Plants plant = new Plants();
        plant.setIdPlante(dto.getId_plante());
        plant.setNom(dto.getNom());
        plant.setPointDeVie(dto.getPoint_de_vie());
        plant.setAttaqueParSeconde(dto.getAttaque_par_seconde());  // No conversion needed
        plant.setDegatAttaque(dto.getDegat_attaque());
        plant.setCout(dto.getCout());
        plant.setSoleilParSeconde(dto.getSoleil_par_seconde());    // No conversion needed
        plant.setEffet(dto.getEffet());
        plant.setCheminImage(dto.getChemin_image());
        return plant;
    }
}

