package com.epf.persistence.mapper;

import com.epf.persistence.model.Zombies;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ZombiesMapper implements RowMapper<Zombies> {
    @Override
    public Zombies mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Zombies(
                rs.getLong("id"),
                rs.getString("nom"),
                rs.getInt("vie"),
                rs.getInt("attaque"),
                rs.getLong("map_id")
        );
    }
}