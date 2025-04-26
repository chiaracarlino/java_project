package com.epf.API.mapper;

import com.epf.API.dto.ZombiesDto;
import com.epf.persistence.model.Zombies;
import org.springframework.stereotype.Component;

@Component
public class ZombiesMapper {

    public ZombiesDto toDto(Zombies zombie) {
        return new ZombiesDto(
            zombie.getIdZombie(),
            zombie.getNom(),
            zombie.getPointDeVie(),
            zombie.getDegatAttaque(),
            zombie.getIdMap(),
            zombie.getAttaqueParSeconde(),
            zombie.getVitesseDeDeplacement(),
            zombie.getCheminImage()
        );
    }

    public Zombies toEntity(ZombiesDto dto) {
        Zombies zombie = new Zombies();
        zombie.setIdZombie(dto.getId_zombie());
        zombie.setNom(dto.getNom());
        zombie.setPointDeVie(dto.getPoint_de_vie());
        zombie.setDegatAttaque(dto.getDegat_attaque());
        zombie.setIdMap(dto.getId_map());
        zombie.setAttaqueParSeconde(dto.getAttaque_par_seconde());
        zombie.setVitesseDeDeplacement(dto.getVitesse_de_deplacement());
        zombie.setCheminImage(dto.getChemin_image());
        return zombie;
    }
}
