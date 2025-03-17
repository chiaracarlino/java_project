package com.epf.persistence.dao;

import com.epf.persistence.model.Zombies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

    @Repository
    public class ZombiesDaoImpl implements ZombiesDao {

        @Autowired
        private JdbcTemplate jdbcTemplate;

        private final RowMapper<Zombies> zombieRowMapper = (rs, rowNum) -> {
            Zombies zombie = new Zombies();
            zombie.setId(rs.getLong("id"));
            zombie.setName(rs.getString("name"));
            zombie.setHealth(rs.getInt("health"));
            zombie.setMapId(rs.getLong("map_id"));
            return zombie;
        };

        @Override
        public List<Zombies> findAll() {
            String sql = "SELECT * FROM zombies";
            return jdbcTemplate.query(sql, zombieRowMapper);
        }

        @Override
        public Optional<Zombies> findById(Long id) {
            String sql = "SELECT * FROM zombies WHERE id = ?";
            List<Zombies> zombies = jdbcTemplate.query(sql, zombieRowMapper, id);
            return zombies.stream().findFirst();  // 🔍 Évite NullPointerException
        }

        @Override
        public List<Zombies> findByMapId(Long mapId) {
            String sql = "SELECT * FROM zombies WHERE map_id = ?";
            return jdbcTemplate.query(sql, zombieRowMapper, mapId);
        }

        @Override
        public Zombies save(Zombies zombie) {
            String sql = "INSERT INTO zombies (name, health, map_id) VALUES (?, ?, ?)";
            jdbcTemplate.update(sql, zombie.getName(), zombie.getHealth(), zombie.getMapId());

            // Récupérer l'id du dernier insert
            String getLastIdSql = "SELECT LAST_INSERT_ID()";
            Long id = jdbcTemplate.queryForObject(getLastIdSql, Long.class);
            zombie.setId(id);
            return zombie;
        }

        @Override
        public void update(Zombies zombie) {
            String sql = "UPDATE zombies SET name = ?, health = ?, map_id = ? WHERE id = ?";
            jdbcTemplate.update(sql, zombie.getName(), zombie.getHealth(), zombie.getMapId(), zombie.getId());
        }

        @Override
        public void delete(Long id) { // ✅ Correction du nom de la méthode
            String sql = "DELETE FROM zombies WHERE id = ?";
            jdbcTemplate.update(sql, id);
        }
    }
