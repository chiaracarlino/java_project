package com.epf.core.services;

import com.epf.persistence.model.Zombies;
import java.util.List;
import java.util.Optional;

public interface ZombiesServices {
    List<Zombies> findAll();
    Optional<Zombies> findById(int id);
    List<Zombies> findByMapId(int mapId);
    Zombies save(Zombies zombie);
    Zombies update(Zombies zombie);
    void delete(int id);
}

