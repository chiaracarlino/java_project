package com.epf.persistence.repository;

import com.epf.persistence.model.Maps;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MapsRepository {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Maps> mapRowMapper = (rs, rowNum) ->
            new Maps(
                    rs.getLong("id_map"),
                    rs.getInt("ligne"),
                    rs.getInt("colonne"),
                    rs.getString("chemin_image")
            );

    public MapsRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Maps save(Maps map) {
        String sql = "INSERT INTO maps (ligne, colonne, chemin_image) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, map.getLigne(), map.getColonne(), map.getCheminImage());
        return map;
    }

    public Optional<Maps> findById(Long id) {
        String sql = "SELECT * FROM maps WHERE id_map = ?";
        return jdbcTemplate.query(sql, mapRowMapper, id).stream().findFirst();
    }

    public List<Maps> findAll() {
        String sql = "SELECT * FROM maps";
        return jdbcTemplate.query(sql, mapRowMapper);
    }

    public void update(Maps map) {
        String sql = "UPDATE maps SET ligne = ?, colonne = ?, chemin_image = ? WHERE id_map = ?";
        jdbcTemplate.update(sql, map.getLigne(), map.getColonne(), map.getCheminImage(), map.getIdMap());
    }

    public void deleteById(Long id) {
        String sql = "DELETE FROM maps WHERE id_map = ?";
        jdbcTemplate.update(sql, id);
    }
}
