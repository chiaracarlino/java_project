package com.epf.persistence.repository;

import com.epf.persistence.model.Zombies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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
public class ZombiesRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<Zombies> zombieRowMapper = (rs, rowNum) -> {
        Zombies zombie = new Zombies();
        zombie.setId(rs.getInt("id_zombie"));
        zombie.setNom(rs.getString("nom"));
        zombie.setPointDeVie(rs.getInt("point_de_vie"));
        zombie.setDegatAttaque(rs.getInt("degat_attaque"));
        zombie.setAttaqueParSeconde(rs.getDouble("attaque_par_seconde"));
        zombie.setVitesseDeDeplacement(rs.getDouble("vitesse_de_deplacement"));
        zombie.setCheminImage(rs.getString("chemin_image"));
        zombie.setIdMap(rs.getInt("id_map"));
        return zombie;
    };

    public Zombies createZombie(Zombies zombie) {
        String sql = "INSERT INTO zombie (nom, point_de_vie, degat_attaque, attaque_par_seconde, " +
                "vitesse_de_deplacement, chemin_image, id_map) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, zombie.getNom());
            ps.setInt(2, zombie.getPointDeVie());
            ps.setInt(3, zombie.getDegatAttaque());
            ps.setDouble(4, zombie.getAttaqueParSeconde());
            ps.setDouble(5, zombie.getVitesseDeDeplacement());
            ps.setString(6, zombie.getCheminImage());
            ps.setObject(7, zombie.getIdMap());
            return ps;
        }, keyHolder);

        Number key = keyHolder.getKey();
        if (key != null) {
            zombie.setId(key.intValue()); 
        }

        return zombie;
    }

    public Optional<Zombies> findById(int id) {
        String sql = "SELECT * FROM zombie WHERE id_zombie = ?";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, zombieRowMapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public List<Zombies> findAll() {
        String sql = "SELECT * FROM zombie";
        return jdbcTemplate.query(sql, zombieRowMapper);
    }

    public Zombies updateZombie(Zombies zombie) {
        String sql = "UPDATE zombie SET nom = ?, point_de_vie = ?, degat_attaque = ?, attaque_par_seconde = ?, " +
                "vitesse_de_deplacement = ?, chemin_image = ?, id_map = ? WHERE id_zombie = ?";
        jdbcTemplate.update(sql, zombie.getNom(), zombie.getPointDeVie(), zombie.getDegatAttaque(),
                zombie.getAttaqueParSeconde(), zombie.getVitesseDeDeplacement(), zombie.getCheminImage(),
                zombie.getIdMap(), zombie.getId());
        return zombie;
    }

    public void deleteZombie(int id) {
        String sql = "DELETE FROM zombie WHERE id_zombie = ?";
        jdbcTemplate.update(sql, id);
    }

    public List<Zombies> findByMapId(int mapId) {
        String sql = "SELECT * FROM zombie WHERE id_map = ?";
        return jdbcTemplate.query(sql, zombieRowMapper, mapId);
    }
}


