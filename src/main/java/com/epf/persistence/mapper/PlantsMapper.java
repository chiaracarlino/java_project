package com.epf.persistence.mapper;

import com.epf.persistence.model.Plants;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class PlantsMapper {
    private final RowMapper<Plants> plantRowMapper = (rs, rowNum) -> {
        Plants plant = new Plants();
        plant.setIdPlante(rs.getInt("id_plante"));
        plant.setNom(rs.getString("nom"));
        plant.setPointDeVie(rs.getInt("point_de_vie"));
        plant.setAttaqueParSeconde(rs.getDouble("attaque_par_seconde"));  // Changed from getBigDecimal
        plant.setDegatAttaque(rs.getInt("degat_attaque"));
        plant.setCout(rs.getInt("cout"));
        plant.setSoleilParSeconde(rs.getDouble("soleil_par_seconde"));    // Changed from getBigDecimal
        plant.setEffet(rs.getString("effet"));
        plant.setCheminImage(rs.getString("chemin_image"));
        return plant;
    };
}
