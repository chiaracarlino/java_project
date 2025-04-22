package com.epf.API.mapper;

import com.epf.API.dto.PlantsDto;
import com.epf.persistence.model.Plants;
import org.springframework.stereotype.Component;

@Component
public class PlantsMapper {

    public PlantsDto toDto(Plants plant) {
        return new PlantsDto(
                plant.getIdPlante(),
                plant.getNom(),
                plant.getPointDeVie(),
                plant.getAttaqueParSeconde(),
                plant.getDegatAttaque(),
                plant.getCout(),
                plant.getSoleilParSeconde(),
                plant.getEffet(),
                plant.getCheminImage()
        );
    }

    public Plants toModel(PlantsDto dto) {
        return new Plants(
                dto.getIdPlante(),
                dto.getNom(),
                dto.getPointDeVie(),
                dto.getAttaqueParSeconde(),
                dto.getDegatAttaque(),
                dto.getCout(),
                dto.getSoleilParSeconde(),
                dto.getEffet(),
                dto.getCheminImage()
        );
    }
}

