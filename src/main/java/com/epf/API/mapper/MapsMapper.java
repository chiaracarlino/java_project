package com.epf.API.mapper;

import com.epf.API.dto.MapsDto;
import com.epf.persistence.model.Maps;

public class MapsMapper {

    public static MapsDto toDTO(Maps map) {
        return new MapsDto(
                map.getId(),
                map.getName(),
                map.getDifficultyLevel()
        );
    }

    public static Maps toEntity(MapsDto dto) {
        return new Maps(
                dto.getId(),
                dto.getName(),
                dto.getDifficultyLevel()
        );
    }
}
