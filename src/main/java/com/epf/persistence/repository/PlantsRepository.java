package com.epf.persistence.repository;

import com.epf.persistence.model.Plants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class PlantsRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<Plants> plantRowMapper = (rs, rowNum) ->
            new Plants(
                    rs.getInt("id_plante"),
                    rs.getString("nom"),
                    rs.getInt("point_de_vie"),
                    rs.getInt("attaque_par_seconde"),
                    rs.getInt("degat_attaque"),
                    rs.getInt("cout"),
                    rs.getInt("soleil_par_seconde"),
                    rs.getString("effet"),
                    rs.getString("chemin_image")
            );

    public Plants save(Plants plant) {
        String sql = "INSERT INTO plante (nom, point_de_vie, attaque_par_seconde, " +
                "degat_attaque, cout, soleil_par_seconde, effet, chemin_image) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
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

    public Optional<Plants> findById(int id) {
        String sql = "SELECT * FROM plante WHERE id_plante = ?";
        return jdbcTemplate.query(sql, plantRowMapper, id).stream().findFirst();
    }

    public List<Plants> findAll() {
        String sql = "SELECT * FROM plante";
        return jdbcTemplate.query(sql, plantRowMapper);
    }

    public void update(Plants plant) {
        String sql = "UPDATE plante SET nom = ?, point_de_vie = ?, attaque_par_seconde = ?, " +
                "degat_attaque = ?, cout = ?, soleil_par_seconde = ?, effet = ?, " +
                "chemin_image = ? WHERE id_plante = ?";
        jdbcTemplate.update(sql,
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

    public void deleteById(int id) {
        String sql = "DELETE FROM plante WHERE id_plante = ?";
        jdbcTemplate.update(sql, id);
    }
}


