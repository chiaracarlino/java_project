package com.epf.API.mapper;

import com.epf.API.dto.ZombiesDto;
import com.epf.persistence.model.Zombies;
import org.springframework.stereotype.Component;

@Component
public class ZombiesMapper {

    public ZombiesDto toDto(Zombies zombie) {
        return new ZombiesDto(
                zombie.getId(),
                zombie.getNom(),
                zombie.getPointDeVie(),
                zombie.getDegatAttaque(),
                zombie.getIdMap(),
                zombie.getAttaqueParSeconde(),
                zombie.getVitesseDeDeplacement(),
                zombie.getCheminImage()
        );
    }

    public Zombies toModel(ZombiesDto zombieDto) {
        return new Zombies(
                zombieDto.getId_zombie(),
                zombieDto.getNom(),
                zombieDto.getPoint_de_vie(),
                zombieDto.getDegat_attaque(),
                zombieDto.getId_map(),
                zombieDto.getAttaque_par_seconde(),
                zombieDto.getVitesse_de_deplacement(),
                zombieDto.getChemin_image()
        );
    }
}
