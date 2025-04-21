package com.epf.persistence.dao;

import com.epf.persistence.mapper.ZombiesMapper;
import com.epf.persistence.model.Zombies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ZombiesDaoImpl implements ZombiesDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Mapper pour convertir le résultat SQL en objet Zombie
    private RowMapper<Zombies> zombieRowMapper = (rs, rowNum) -> {
        Zombies zombie = new Zombies();
        zombie.setId(rs.getLong("id"));
        zombie.setNom(rs.getString("name"));
        zombie.setPointDeVie(rs.getInt("health"));
        zombie.setDegatAttaque(rs.getInt("degat_attaque"));
        zombie.setAttaqueParSeconde(rs.getInt("attaque_par_seconde"));
        zombie.setVitesseDeDeplacement(rs.getInt("vitesse_de_deplacement"));
        zombie.setCheminImage(rs.getString("chemin_image"));
        zombie.setIdMap(rs.getLong("map_id"));
        return zombie;
    };

    @Override
    public Zombies save(Zombies zombie) {
        String sql = "INSERT INTO zombies (name, health, degat_attaque, attaque_par_seconde, vitesse_de_deplacement, chemin_image, map_id) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, zombie.getNom(), zombie.getPointDeVie(), zombie.getDegatAttaque(),
                zombie.getAttaqueParSeconde(), zombie.getVitesseDeDeplacement(), zombie.getCheminImage(),
                zombie.getIdMap());
        return zombie; // Retourne le zombie après l'avoir sauvegardé
    }

    @Override
    public Optional<Zombies> findById(Long id) {
        String sql = "SELECT * FROM zombies WHERE id = ?";
        try {
            Zombies zombie = jdbcTemplate.queryForObject(sql, new ZombiesMapper(), id);
            return Optional.ofNullable(zombie);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }


    @Override
    public List<Zombies> findAll() {
        String sql = "SELECT * FROM zombies";
        return jdbcTemplate.query(sql, zombieRowMapper);
    }

    @Override
    public void update(Zombies zombie) {
        String sql = "UPDATE zombies SET name = ?, health = ?, degat_attaque = ?, attaque_par_seconde = ?, " +
                "vitesse_de_deplacement = ?, chemin_image = ?, map_id = ? WHERE id = ?";
        jdbcTemplate.update(sql, zombie.getNom(), zombie.getPointDeVie(), zombie.getDegatAttaque(),
                zombie.getAttaqueParSeconde(), zombie.getVitesseDeDeplacement(), zombie.getCheminImage(),
                zombie.getIdMap(), zombie.getId());
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM zombies WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Zombies> findByMapId(Long mapId) {
        String sql = "SELECT * FROM zombies WHERE map_id = ?";
        return jdbcTemplate.query(sql, zombieRowMapper, mapId);
    }
}

