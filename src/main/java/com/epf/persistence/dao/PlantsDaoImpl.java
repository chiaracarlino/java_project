package com.epf.persistence.dao;

import com.epf.persistence.model.Plants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.epf.persistence.dao.PlantsDao;
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

    private final RowMapper<Plants> plantsRowMapper = (rs, rowNum) -> new Plants(
            rs.getLong("id_plante"),
            rs.getString("nom"),
            rs.getInt("point_de_vie"),
            rs.getInt("attaque_par_seconde"),
            rs.getInt("degat_attaque"),
            rs.getInt("cout"),
            rs.getInt("soleil_par_seconde"),
            rs.getString("effet"),
            rs.getString("chemin_image")
    );

    @Override
    public List<Plants> findAll() {
        return jdbcTemplate.query("SELECT * FROM plantes", plantsRowMapper);
    }

    @Override
    public Optional<Plants> findById(Long id) {
        List<Plants> result = jdbcTemplate.query(
                "SELECT * FROM plantes WHERE id_plante = ?",
                plantsRowMapper,
                id
        );
        return result.stream().findFirst();
    }

    @Override
    public Plants save(Plants plant) {
        jdbcTemplate.update(
                "INSERT INTO plantes (nom, point_de_vie, attaque_par_seconde, degat_attaque, cout, soleil_par_seconde, effet, chemin_image) VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                plant.getNom(),
                plant.getPointDeVie(),
                plant.getAttaqueParSeconde(),
                plant.getDegatAttaque(),
                plant.getCout(),
                plant.getSoleilParSeconde(),
                plant.getEffet(),
                plant.getCheminImage()
        );
        return plant;
    }

    @Override
    public void update(Plants plant) {
        jdbcTemplate.update(
                "UPDATE plantes SET nom = ?, point_de_vie = ?, attaque_par_seconde = ?, degat_attaque = ?, cout = ?, soleil_par_seconde = ?, effet = ?, chemin_image = ? WHERE id_plante = ?",
                plant.getNom(),
                plant.getPointDeVie(),
                plant.getAttaqueParSeconde(),
                plant.getDegatAttaque(),
                plant.getCout(),
                plant.getSoleilParSeconde(),
                plant.getEffet(),
                plant.getCheminImage(),
                plant.getIdPlante()
        );
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update("DELETE FROM plantes WHERE id_plante = ?", id);
    }
}



