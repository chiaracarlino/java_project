package com.epf.persistence.mapper;

import com.epf.persistence.model.Plants;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PlantsMapper implements RowMapper<Plants> {
    @Override
    public Plants mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Plants(
                rs.getLong("id_plante"),
                rs.getString("nom"),
                rs.getInt("point_de_vie"),
                rs.getInt("attaque_par_seconde"),
                rs.getInt("degat_attaque"),
                rs.getInt("cout"),
                rs.getInt("soleil_par_seconde"),
                rs.getString("effet"),
                rs.getString("chemin_image")
        );
    }
}
