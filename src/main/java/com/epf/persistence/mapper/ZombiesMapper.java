package com.epf.persistence.mapper;

import com.epf.persistence.model.Zombies;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ZombiesMapper implements RowMapper<Zombies> {

    @Override
    public Zombies mapRow(ResultSet rs, int rowNum) throws SQLException {
        Zombies zombie = new Zombies();
        zombie.setId(rs.getLong("id"));
        zombie.setNom(rs.getString("nom"));
        zombie.setPointDeVie(rs.getInt("point_de_vie"));
        zombie.setAttaqueParSeconde(rs.getInt("attaque_par_seconde"));
        zombie.setDegatAttaque(rs.getInt("degat_attaque"));
        zombie.setVitesseDeDeplacement(rs.getInt("vitesse_de_deplacement"));
        zombie.setCheminImage(rs.getString("chemin_image"));

        Long idMap = rs.getObject("id_map") != null ? rs.getLong("id_map") : null;
        zombie.setIdMap(idMap);

        return zombie;
    }
}
