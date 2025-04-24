package com.epf.persistence.dao;

import com.epf.persistence.model.Zombies;

import java.util.List;
import java.util.Optional;

public interface ZombiesDao {
    Zombies save(Zombies zombie);
    Optional<Zombies> findById(int id);
    List<Zombies> findAll();
    List<Zombies> findByMapId(int mapId); // 🔥 Zombies d'une map
    void update(Zombies zombie);
    void delete(int id);
}

