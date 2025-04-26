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

    @Override
    public Zombies createZombie(Zombies zombie) {
        String sql = "INSERT INTO zombie (nom, point_de_vie, degat_attaque, attaque_par_seconde, vitesse_de_deplacement, chemin_image, id_map) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, zombie.getNom(), zombie.getPointDeVie(), zombie.getDegatAttaque(),
                zombie.getAttaqueParSeconde(), zombie.getVitesseDeDeplacement(), zombie.getCheminImage(),
                zombie.getIdMap());
        return zombie; 
    }

    @Override
    public Optional<Zombies> findById(int id) {
        String sql = "SELECT * FROM zombie WHERE id_zombie = ?";
        try {
            Zombies zombie = jdbcTemplate.queryForObject(sql, new ZombiesMapper(), id);
            return Optional.ofNullable(zombie);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }


    @Override
    public List<Zombies> findAll() {
        String sql = "SELECT * FROM zombie";
        return jdbcTemplate.query(sql, zombieRowMapper);
    }

    @Override
    public Zombies updateZombie(Zombies zombie) {
        String sql = "UPDATE zombie SET nom = ?, point_de_vie = ?, degat_attaque = ?, attaque_par_seconde = ?, " +
                "vitesse_de_deplacement = ?, chemin_image = ?, id_map = ? WHERE id_zombie = ?";
        jdbcTemplate.update(sql, zombie.getNom(), zombie.getPointDeVie(), zombie.getDegatAttaque(),
                zombie.getAttaqueParSeconde(), zombie.getVitesseDeDeplacement(), zombie.getCheminImage(),
                zombie.getIdMap(), zombie.getId());
        return zombie;
    }

    @Override
    public void deleteZombie(int id) {
        String sql = "DELETE FROM zombie WHERE id_zombie = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Zombies> findByMapId(int mapId) {
        String sql = "SELECT * FROM zombie WHERE id_map = ?";
        return jdbcTemplate.query(sql, zombieRowMapper, mapId);
    }
}

