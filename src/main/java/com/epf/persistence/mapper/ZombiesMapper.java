package com.epf.persistence.mapper;

import com.epf.persistence.model.Zombies;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ZombiesMapper implements RowMapper<Zombies> {

    @Override
    public Zombies mapRow(ResultSet rs, int rowNum) throws SQLException {
        return toModel(rs);
    }

    public Zombies toModel(ResultSet rs) throws SQLException {
        Zombies zombie = new Zombies();
        zombie.setId(rs.getInt("id_zombie"));
        zombie.setNom(rs.getString("nom"));
        zombie.setPointDeVie(rs.getInt("point_de_vie"));
        zombie.setDegatAttaque(rs.getInt("degat_attaque"));
        zombie.setAttaqueParSeconde(rs.getDouble("attaque_par_seconde"));  // Changed from getInt
        zombie.setVitesseDeDeplacement(rs.getDouble("vitesse_de_deplacement")); // Changed from getInt
        zombie.setCheminImage(rs.getString("chemin_image"));
        zombie.setIdMap(rs.getInt("id_map"));
        return zombie;
    }
}
