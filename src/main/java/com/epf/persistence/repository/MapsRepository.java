package com.epf.persistence.repository;

import com.epf.persistence.dao.MapsDao;
import com.epf.persistence.model.Maps;
import com.epf.persistence.model.Zombies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
public class MapsRepository implements MapsDao {

    private final JdbcTemplate jdbcTemplate;
    private ZombiesRepository zombiesRepository;

    private final RowMapper<Maps> mapRowMapper = (rs, rowNum) -> {
        Maps map = new Maps(
            rs.getInt("id_map"),
            rs.getInt("ligne"),
            rs.getInt("colonne"),
            rs.getString("chemin_image")
        );

        if (map.getIdMap() != 0) {
            map.setZombies(zombiesRepository.findByMapId(map.getIdMap()));
        }
        return map;
    };

    @Autowired
    public MapsRepository(JdbcTemplate jdbcTemplate, ZombiesRepository zombiesRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.zombiesRepository = zombiesRepository;
    }

    @Override
    public Maps save(Maps map) {
        return createMap(map);
    }

    public Maps createMap(Maps map) {
        String sql = "INSERT INTO map (ligne, colonne, chemin_image) VALUES (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, map.getLigne());
            ps.setInt(2, map.getColonne());
            ps.setString(3, map.getCheminImage());
            return ps;
        }, keyHolder);

        Number key = keyHolder.getKey();
        if (key != null) {
            map.setIdMap(key.intValue());
        }
        return map;
    }

    @Override
    public Optional<Maps> findById(int id) {
        String sql = "SELECT * FROM map WHERE id_map = ?";
        List<Maps> maps = jdbcTemplate.query(sql, mapRowMapper, id);
        return maps.stream().findFirst();
    }

    @Override
    public List<Maps> findAll() {
        String sql = "SELECT * FROM map";
        return jdbcTemplate.query(sql, mapRowMapper);
    }

    @Override
    public void update(Maps map) {
        updateMap(map);
    }

    public Maps updateMap(Maps map) {
        String sql = "UPDATE map SET ligne = ?, colonne = ?, chemin_image = ? WHERE id_map = ?";
        jdbcTemplate.update(sql, 
            map.getLigne(), 
            map.getColonne(), 
            map.getCheminImage(), 
            map.getIdMap()
        );
        return map;
    }

    @Override
    public void delete(int id) {
        deleteMap(id);
    }

    public void deleteMap(int id) {
        List<Zombies> zombies = zombiesRepository.findByMapId(id);
        if (!zombies.isEmpty()) {
            throw new RuntimeException("Impossible de supprimer la map car elle est liée à " + zombies.size() + " zombies");
        }

        String sql = "DELETE FROM map WHERE id_map = ?";
        jdbcTemplate.update(sql, id);
    }
}
