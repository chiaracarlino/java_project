package com.epf.persistence.repository;

import com.epf.persistence.model.Zombies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ZombiesRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Mapper pour convertir le résultat SQL en objet Zombie
    private RowMapper<Zombies> zombieRowMapper = (rs, rowNum) -> {
        Zombies zombie = new Zombies();
        zombie.setId(rs.getLong("id"));
        zombie.setName(rs.getString("name"));
        zombie.setHealth(rs.getInt("health"));
        zombie.setMapId(rs.getLong("map_id"));
        return zombie;
    };

    // 🔥 Récupérer les zombies d'une carte via map_id
    public List<Zombies> findByMapId(Long mapId) {
        String sql = "SELECT * FROM zombies WHERE map_id = ?";
        return jdbcTemplate.query(sql, zombieRowMapper, mapId);
    }

    // Autres méthodes CRUD
    public Zombies save(Zombies zombie) {
        String sql = "INSERT INTO zombies (name, health, map_id) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, zombie.getName(), zombie.getHealth(), zombie.getMapId());
        return zombie;
    }

    public List<Zombies> findAll() {
        String sql = "SELECT * FROM zombies";
        return jdbcTemplate.query(sql, zombieRowMapper);
    }

    public Zombies findById(Long id) {
        String sql = "SELECT * FROM zombies WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, zombieRowMapper, id);
    }

    public void update(Zombies zombie) {
        String sql = "UPDATE zombies SET name = ?, health = ?, map_id = ? WHERE id = ?";
        jdbcTemplate.update(sql, zombie.getName(), zombie.getHealth(), zombie.getMapId(), zombie.getId());
    }

    public void deleteById(Long id) {
        String sql = "DELETE FROM zombies WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}

