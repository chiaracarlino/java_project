package com.epf.persistence.mapper;

import com.epf.persistence.model.Plants;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlantsMapper implements RowMapper<Plants> {
    @Override
    public Plants mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Plants(
                rs.getLong("id"),
                rs.getString("nom"),
                rs.getInt("vie"),
                rs.getInt("attaque")
                /*rs.getBoolean("est_defensive")*/
        );
    }
}