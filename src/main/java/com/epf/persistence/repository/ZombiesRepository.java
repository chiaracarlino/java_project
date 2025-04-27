package com.epf.persistence.repository;

import com.epf.persistence.dao.ZombiesDao;
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
public class ZombiesRepository implements ZombiesDao {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Zombies> zombieRowMapper = (rs, rowNum) -> new Zombies(
        rs.getInt("id_zombie"),         
        rs.getString("nom"),             
        rs.getInt("point_de_vie"),       
        rs.getInt("degat_attaque"),      
        rs.getObject("id_map", Integer.class), 
        rs.getDouble("attaque_par_seconde"),   
        rs.getDouble("vitesse_de_deplacement"), 
        rs.getString("chemin_image")    
    );

    @Autowired
    public ZombiesRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Zombies createZombie(Zombies zombie) {
        String sql = "INSERT INTO zombie (nom, point_de_vie, degat_attaque, attaque_par_seconde, vitesse_de_deplacement, chemin_image, id_map) VALUES (?, ?, ?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, zombie.getNom());
            ps.setInt(2, zombie.getPointDeVie());
            ps.setInt(3, zombie.getDegatAttaque());
            ps.setDouble(4, zombie.getAttaqueParSeconde());
            ps.setDouble(5, zombie.getVitesseDeDeplacement());
            ps.setString(6, zombie.getCheminImage());
            ps.setInt(7, zombie.getIdMap());
            return ps;
        }, keyHolder);

        Number key = keyHolder.getKey();
        if (key != null) {
            zombie.setIdZombie(key.intValue());
        }
        return zombie;
    }

    @Override
    public Zombies updateZombie(Zombies zombie) {
        String sql = "UPDATE zombie SET nom = ?, point_de_vie = ?, degat_attaque = ?, attaque_par_seconde = ?, vitesse_de_deplacement = ?, chemin_image = ?, id_map = ? WHERE id_zombie = ?";
        jdbcTemplate.update(sql,
            zombie.getNom(),
            zombie.getPointDeVie(),
            zombie.getDegatAttaque(),
            zombie.getAttaqueParSeconde(),
            zombie.getVitesseDeDeplacement(),
            zombie.getCheminImage(),
            zombie.getIdMap(),
            zombie.getIdZombie()
        );
        return zombie;
    }

    @Override
    public void deleteZombie(int id) {
        String sql = "DELETE FROM zombie WHERE id_zombie = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public Optional<Zombies> findById(int id) {
        String sql = "SELECT * FROM zombie WHERE id_zombie = ?";
        List<Zombies> zombies = jdbcTemplate.query(sql, zombieRowMapper, id);
        return zombies.stream().findFirst();
    }

    @Override
    public List<Zombies> findAll() {
        String sql = "SELECT * FROM zombie";
        return jdbcTemplate.query(sql, zombieRowMapper);
    }

    @Override
    public List<Zombies> findByMapId(int mapId) {
        String sql = "SELECT * FROM zombie WHERE id_map = ?";
        return jdbcTemplate.query(sql, zombieRowMapper, mapId);
    }
}



