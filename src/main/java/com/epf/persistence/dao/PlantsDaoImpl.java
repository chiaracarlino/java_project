package com.epf.persistence.dao;

import com.epf.persistence.model.Plants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class PlantsDaoImpl implements PlantsDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<Plants> plantsRowMapper = (rs, rowNum) -> {
        Plants plant = new Plants();
        plant.setId(rs.getLong("id"));
        plant.setName(rs.getString("name"));
        plant.setDamage(rs.getInt("damage"));
        return plant;
    };

    @Override
    public Plants save(Plants plant) {
        String sql = "INSERT INTO plants (name, damage) VALUES (?, ?)";
        jdbcTemplate.update(sql, plant.getName(), plant.getDamage());

        String getLastIdSql = "SELECT LAST_INSERT_ID()";
        Long id = jdbcTemplate.queryForObject(getLastIdSql, Long.class);
        plant.setId(id);
        return plant;
    }

    @Override
    public Optional<Plants> findById(Long id) {
        String sql = "SELECT * FROM plants WHERE id = ?";
        List<Plants> plants = jdbcTemplate.query(sql, plantsRowMapper, id);
        return plants.stream().findFirst();
    }

    @Override
    public List<Plants> findAll() {
        String sql = "SELECT * FROM plants";
        return jdbcTemplate.query(sql, plantsRowMapper);
    }

    @Override
    public void update(Plants plant) {
        String sql = "UPDATE plants SET name = ?, damage = ? WHERE id = ?";
        jdbcTemplate.update(sql, plant.getName(), plant.getDamage(), plant.getId());
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM plants WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}


