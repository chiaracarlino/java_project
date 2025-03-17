package com.epf.API.mapper;

import com.epf.API.dto.ZombiesDto;
import com.epf.persistence.model.Zombies;
import org.springframework.stereotype.Component;

@Component
public class ZombiesMapper {

    public ZombiesDto toDto(Zombies zombie) {
        return new ZombiesDto(
                zombie.getId(),
                zombie.getName(),
                zombie.getHealth(),
                zombie.getMapId()
        );  /*zombie.getAttaque()*/
    }

    public Zombies toModel(ZombiesDto zombieDto) {
        return new Zombies(
                zombieDto.getId(),
                zombieDto.getName(),
                zombieDto.getHealth(),
                zombieDto.getMapId()
        ); /* zombieDto.getAttaque() */
    }
}
