package com.epf.persistance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PlantsRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<Plants> plantRowMapper = (rs, rowNum) ->
            new Plants(
                    rs.getLong("id"),
                    rs.getString("name"),
                    rs.getInt("health"),
                    rs.getInt("damage"),
                    rs.getInt("cost")
            );

    public Plants save(Plants plant) {
        String sql = "INSERT INTO plants (name, health, damage, cost) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, plant.getName(), plant.getHealth(), plant.getDamage(), plant.getCost());
        return plant;
    }

    public Optional<Plants> findById(Long id) {
        String sql = "SELECT * FROM plants WHERE id = ?";
        return jdbcTemplate.query(sql, plantRowMapper, id).stream().findFirst();
    }

    public List<Plants> findAll() {
        String sql = "SELECT * FROM plants";
        return jdbcTemplate.query(sql, plantRowMapper);
    }

    public void update(Plants plant) {
        String sql = "UPDATE plants SET name = ?, health = ?, damage = ?, cost = ? WHERE id = ?";
        jdbcTemplate.update(sql, plant.getName(), plant.getHealth(), plant.getDamage(), plant.getCost(), plant.getId());
    }

    public void deleteById(Long id) {
        String sql = "DELETE FROM plants WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}

