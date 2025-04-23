package com.epf.API.mapper;

import com.epf.API.dto.MapsDto;
import com.epf.persistence.model.Maps;

public class MapsMapper {

    public static MapsDto toDTO(Maps map) {
        return new MapsDto(
                map.getIdMap(),        // id
                map.getLigne(),        // height
                map.getColonne(),      // width
                map.getCheminImage()   // image path
        );
    }

    public static Maps toEntity(MapsDto dto) {
        Maps map = new Maps();
        map.setIdMap(dto.getId_map());
        map.setLigne(dto.getLigne());
        map.setColonne(dto.getColonne());
        map.setCheminImage(dto.getChemin_image());
        return map;
    }
}


