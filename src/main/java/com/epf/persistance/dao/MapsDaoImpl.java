package com.epf.persistance.dao;
import com.epf.persistance.dao.MapsDao;

import com.epf.persistance.Maps;
import com.epf.persistance.MapsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MapsDaoImpl implements MapsDao {


    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<Maps> mapRowMapper = (rs, rowNum) -> {
        Maps map = new Maps();
        map.setId(rs.getLong("id"));
        map.setName(rs.getString("name"));
        return map;
    };

    @Override
    public List<Maps> findAll() {
        String sql = "SELECT * FROM maps";
        return jdbcTemplate.query(sql, mapRowMapper);
    }

    @Override
    public void update(Maps map) {

    }

    @Override
    public Optional<Maps> findById(Long id) {
        String sql = "SELECT * FROM maps WHERE id = ?";
        List<Maps> maps = jdbcTemplate.query(sql, mapRowMapper, id);
        return maps.stream().findFirst();
    }

    @Override
    public Maps save(Maps map) {
        String sql = "INSERT INTO maps (name) VALUES (?)";
        jdbcTemplate.update(sql, map.getName());

        // Récupérer l'id du dernier insert
        String getLastIdSql = "SELECT LAST_INSERT_ID()";
        Long id = jdbcTemplate.queryForObject(getLastIdSql, Long.class);
        map.setId(id);
        return map;
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM maps WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
