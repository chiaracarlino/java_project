package com.epf.persistence.dao;

import com.epf.persistence.model.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class MapsDaoImpl implements MapsDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<Maps> mapRowMapper = (rs, rowNum) -> {
        Maps map = new Maps();
        map.setIdMap(rs.getInt("id_map"));
        map.setLigne(rs.getInt("ligne"));
        map.setColonne(rs.getInt("colonne"));
        map.setCheminImage(rs.getString("chemin_image"));
        return map;
    };

    @Override
    public List<Maps> findAll() {
        String sql = "SELECT * FROM map";
        return jdbcTemplate.query(sql, mapRowMapper);
    }

    @Override
    public Optional<Maps> findById(int id) {
        String sql = "SELECT * FROM map WHERE id_map = ?";
        List<Maps> maps = jdbcTemplate.query(sql, mapRowMapper, id);
        return maps.stream().findFirst();
    }

    @Override
    public Maps save(Maps map) {
        String sql = "INSERT INTO map (ligne, colonne, chemin_image) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, map.getLigne(), map.getColonne(), map.getCheminImage());

        // Récupération de l'ID généré automatiquement
        String getLastIdSql = "SELECT LAST_INSERT_ID()";
        Integer idObject = jdbcTemplate.queryForObject(getLastIdSql, Integer.class);
        int id = Objects.requireNonNull(idObject, "Generated ID must not be null");
        map.setIdMap(id);
        return map;
    }

    @Override
    public void update(Maps map) {
        String sql = "UPDATE map SET ligne = ?, colonne = ?, chemin_image = ? WHERE id_map = ?";
        jdbcTemplate.update(sql, map.getLigne(), map.getColonne(), map.getCheminImage(), map.getIdMap());
    }

    @Override
    public void delete(int id) {
        String updateZombiesSql = "DELETE FROM zombie WHERE id_map = ?";
        jdbcTemplate.update(updateZombiesSql, id);

        String deleteMapSql = "DELETE FROM map WHERE id_map = ?";
        jdbcTemplate.update(deleteMapSql, id);
    }
}

