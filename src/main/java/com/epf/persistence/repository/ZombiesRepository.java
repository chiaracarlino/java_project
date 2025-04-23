package com.epf.persistence.repository;

import com.epf.persistence.model.Zombies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ZombiesRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<Zombies> zombieRowMapper = (rs, rowNum) -> new Zombies(
            rs.getLong("id"),
            rs.getString("nom"),
            rs.getInt("point_de_vie"),
            rs.getInt("degat_attaque"),
            rs.getLong("id_map"),
            rs.getInt("attaque_par_seconde"),
            rs.getInt("vitesse_de_deplacement"),
            rs.getString("chemin_image")
    );

    public List<Zombies> findByMapId(Long idMap) {
        String sql = "SELECT * FROM zombies WHERE id_map = ?";
        return jdbcTemplate.query(sql, zombieRowMapper, idMap);
    }

    // 💾 Enregistrer un zombie
    public Zombies save(Zombies zombie) {
        String sql = "INSERT INTO zombies (nom, point_de_vie, degat_attaque, id_map, attaque_par_seconde, vitesse_de_deplacement, chemin_image) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                zombie.getNom(),
                zombie.getPointDeVie(),
                zombie.getDegatAttaque(),
                zombie.getIdMap(),
                zombie.getAttaqueParSeconde(),
                zombie.getVitesseDeDeplacement(),
                zombie.getCheminImage()
        );
        return zombie;
    }

    public List<Zombies> findAll() {
        String sql = "SELECT * FROM zombies";
        return jdbcTemplate.query(sql, zombieRowMapper);
    }

    public Optional<Zombies> findById(Long id) {
        String sql = "SELECT * FROM zombies WHERE id = ?";
        return jdbcTemplate.query(sql, zombieRowMapper, id).stream().findFirst();
    }

    public void update(Zombies zombie) {
        String sql = "UPDATE zombies SET nom = ?, point_de_vie = ?, degat_attaque = ?, id_map = ?, " +
                "attaque_par_seconde = ?, vitesse_de_deplacement = ?, chemin_image = ? WHERE id = ?";
        jdbcTemplate.update(sql,
                zombie.getNom(),
                zombie.getPointDeVie(),
                zombie.getDegatAttaque(),
                zombie.getIdMap(),
                zombie.getAttaqueParSeconde(),
                zombie.getVitesseDeDeplacement(),
                zombie.getCheminImage(),
                zombie.getId()
        );
    }

    public void delete(Long id) {
        String sql = "DELETE FROM zombies WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}


