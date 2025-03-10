package com.epf.persistance.dao;

import com.epf.persistance.Zombies;

import java.util.List;
import java.util.Optional;

public interface ZombiesDao {
    Zombies save(Zombies zombie);
    Optional<Zombies> findById(Long id);
    List<Zombies> findAll();
    List<Zombies> findByMapId(Long mapId); // 🔥 Zombies d'une map
    void update(Zombies zombie);
    void delete(Long id);
}

