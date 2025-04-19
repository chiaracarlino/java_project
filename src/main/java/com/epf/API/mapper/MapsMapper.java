package com.epf.API.mapper;

import com.epf.API.dto.MapsDto;
import com.epf.persistence.model.Maps;

public class MapsMapper {

    public static MapsDto toDTO(Maps map) {
        return new MapsDto(
                map.getId(),
                map.getHeight(), // ligne
                map.getWidth(),  // colonne
                map.getImagePath()
        );
    }

    public static Maps toEntity(MapsDto dto) {
        Maps map = new Maps();
        map.setId(dto.getId_map());
        map.setHeight(dto.getLigne());
        map.setWidth(dto.getColonne());
        map.setImagePath(dto.getChemin_image());
        return map;
    }
}

