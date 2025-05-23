package com.epf.persistence.repository;

import com.epf.persistence.dao.PlantsDao;
import com.epf.persistence.model.Plants;
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
public class PlantsRepository implements PlantsDao {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Plants> plantsRowMapper = (rs, rowNum) -> new Plants(
            rs.getInt("id_plante"),
            rs.getString("nom"),
            rs.getInt("point_de_vie"),
            rs.getDouble("attaque_par_seconde"),
            rs.getInt("degat_attaque"),
            rs.getInt("cout"),
            rs.getDouble("soleil_par_seconde"),
            rs.getString("effet"),
            rs.getString("chemin_image")
    );

    @Autowired
    public PlantsRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Plants save(Plants plant) {
        String sql = "INSERT INTO plante (nom, point_de_vie, attaque_par_seconde, degat_attaque, cout, soleil_par_seconde, effet, chemin_image) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, plant.getNom());
            ps.setInt(2, plant.getPointDeVie());
  
            if (plant.getAttaqueParSeconde() == null) {
                ps.setNull(3, java.sql.Types.DOUBLE);
            } else {
                ps.setDouble(3, plant.getAttaqueParSeconde());
            }
            ps.setInt(4, plant.getDegatAttaque());
            ps.setInt(5, plant.getCout());
            if (plant.getSoleilParSeconde() == null) {
                ps.setNull(6, java.sql.Types.DOUBLE);
            } else {
                ps.setDouble(6, plant.getSoleilParSeconde());
            }
            ps.setString(7, plant.getEffet());
            ps.setString(8, plant.getCheminImage());
            return ps;
        }, keyHolder);

        Number key = keyHolder.getKey();
        if (key != null) {
            plant.setIdPlante(key.intValue());
        }
        return plant;
    }

    @Override
    public Optional<Plants> findById(int id) {
        List<Plants> result = jdbcTemplate.query(
                "SELECT * FROM plante WHERE id_plante = ?",
                plantsRowMapper,
                id
        );
        return result.stream().findFirst();
    }

    @Override
    public List<Plants> findAll() {
        return jdbcTemplate.query("SELECT * FROM plante", plantsRowMapper);
    }

    @Override
    public void update(Plants plant) {
        jdbcTemplate.update(
                "UPDATE plante SET nom = ?, point_de_vie = ?, attaque_par_seconde = ?, degat_attaque = ?, cout = ?, soleil_par_seconde = ?, effet = ?, chemin_image = ? WHERE id_plante = ?",
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
    public void delete(int id) {
        String sql = "DELETE FROM plante WHERE id_plante = ?";
        jdbcTemplate.update(sql, id);
    }
}


