package com.epf.persistence.dao;

import com.epf.persistence.model.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MapsDaoImpl implements MapsDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<Maps> mapRowMapper = (rs, rowNum) -> {
        Maps map = new Maps();
        map.setIdMap(rs.getLong("id_map"));
        map.setLigne(rs.getInt("ligne"));
        map.setColonne(rs.getInt("colonne"));
        map.setCheminImage(rs.getString("chemin_image"));
        return map;
    };

    @Override
    public List<Maps> findAll() {
        String sql = "SELECT * FROM maps";
        return jdbcTemplate.query(sql, mapRowMapper);
    }

    @Override
    public Optional<Maps> findById(Long id) {
        String sql = "SELECT * FROM maps WHERE id_map = ?";
        List<Maps> maps = jdbcTemplate.query(sql, mapRowMapper, id);
        return maps.stream().findFirst();
    }

    @Override
    public Maps save(Maps map) {
        String sql = "INSERT INTO maps (ligne, colonne, chemin_image) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, map.getLigne(), map.getColonne(), map.getCheminImage());

        // Récupération de l'ID généré automatiquement
        String getLastIdSql = "SELECT LAST_INSERT_ID()";
        Long id = jdbcTemplate.queryForObject(getLastIdSql, Long.class);
        map.setIdMap(id);
        return map;
    }

    @Override
    public void update(Maps map) {
        String sql = "UPDATE maps SET ligne = ?, colonne = ?, chemin_image = ? WHERE id_map = ?";
        jdbcTemplate.update(sql, map.getLigne(), map.getColonne(), map.getCheminImage(), map.getIdMap());
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM maps WHERE id_map = ?";
        jdbcTemplate.update(sql, id);
    }
}

