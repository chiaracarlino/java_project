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
                    rs.getLong("id"),
                    rs.getString("name"),
                    rs.getInt("width"),
                    rs.getInt("height"),
                    rs.getInt("difficultyLevel")
            );

    public MapsRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Maps save(Maps map) {
        String sql = "INSERT INTO maps (name, width, height) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, map.getName(), map.getWidth(), map.getHeight(), map.getDifficultyLevel());
        return map;
    }

    public Optional<Maps> findById(Long id) {
        String sql = "SELECT * FROM maps WHERE id = ?";
        return jdbcTemplate.query(sql, mapRowMapper, id).stream().findFirst();
    }

    public List<Maps> findAll() {
        String sql = "SELECT * FROM maps";
        return jdbcTemplate.query(sql, mapRowMapper);
    }

    public void update(Maps map) {
        String sql = "UPDATE maps SET name = ?, width = ?, height = ? WHERE id = ?";
        jdbcTemplate.update(sql, map.getName(), map.getWidth(), map.getHeight(), map.getId());
    }

    public void deleteById(Long id) {
        String sql = "DELETE FROM maps WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}

