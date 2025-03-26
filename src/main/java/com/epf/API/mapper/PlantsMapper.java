package com.epf.API.mapper;

import com.epf.API.dto.PlantsDto;
import com.epf.persistence.model.Plants;
import org.springframework.stereotype.Component;

@Component
public class PlantsMapper {

    public PlantsDto toDto(Plants plant) {
        return new PlantsDto(
                plant.getId(),
                plant.getName(),
                plant.getHealth(),
                plant.getDamage()
        );  /*zombie.getAttaque()*/
    }

    public Plants toModel(PlantsDto plantsDto) {
        return new Plants(
                plantsDto.getId(),
                plantsDto.getName(),
                plantsDto.getHealth(),
                plantsDto.getDamage()
        ); /* zombieDto.getAttaque() */
    }
}
