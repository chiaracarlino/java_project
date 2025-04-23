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

    // RowMapper qui mappe les résultats de la base de données à un objet Plants
    private final RowMapper<Plants> plantRowMapper = (rs, rowNum) ->
            new Plants(
                    rs.getLong("idPlante"),
                    rs.getString("nom"),
                    rs.getInt("pointDeVie"),
                    rs.getInt("attaqueParSeconde"),
                    rs.getInt("degatAttaque"),
                    rs.getInt("cout"),
                    rs.getInt("soleilParSeconde"),
                    rs.getString("effet"),
                    rs.getString("cheminImage")
            );

    // Méthode pour sauvegarder une plante
    public Plants save(Plants plant) {
        String sql = "INSERT INTO plants (nom, pointDeVie, attaqueParSeconde, degatAttaque, cout, soleilParSeconde, effet, cheminImage) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, plant.getNom(), plant.getPointDeVie(), plant.getAttaqueParSeconde(),
                plant.getDegatAttaque(), plant.getCout(), plant.getSoleilParSeconde(),
                plant.getEffet(), plant.getCheminImage());
        return plant;
    }

    // Méthode pour rechercher une plante par son ID
    public Optional<Plants> findById(Long id) {
        String sql = "SELECT * FROM plants WHERE idPlante = ?";
        return jdbcTemplate.query(sql, plantRowMapper, id).stream().findFirst();
    }

    // Méthode pour trouver toutes les plantes
    public List<Plants> findAll() {
        String sql = "SELECT * FROM plants";
        return jdbcTemplate.query(sql, plantRowMapper);
    }

    // Méthode pour mettre à jour une plante
    public void update(Plants plant) {
        String sql = "UPDATE plants SET nom = ?, pointDeVie = ?, attaqueParSeconde = ?, degatAttaque = ?, " +
                "cout = ?, soleilParSeconde = ?, effet = ?, cheminImage = ? WHERE idPlante = ?";
        jdbcTemplate.update(sql, plant.getNom(), plant.getPointDeVie(), plant.getAttaqueParSeconde(),
                plant.getDegatAttaque(), plant.getCout(), plant.getSoleilParSeconde(),
                plant.getEffet(), plant.getCheminImage(), plant.getIdPlante());
    }

    // Méthode pour supprimer une plante par son ID
    public void deleteById(Long id) {
        String sql = "DELETE FROM plants WHERE idPlante = ?";
        jdbcTemplate.update(sql, id);
    }
}


