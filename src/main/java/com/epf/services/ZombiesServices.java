package com.epf.services;

import com.epf.persistance.Zombies;
import java.util.List;
import java.util.Optional;

public interface ZombiesServices {
    List<Zombies> findAll();
    Optional<Zombies> findById(Long id);
    List<Zombies> findByMapId(Long mapId);
    Zombies save(Zombies zombie);

    void update(Zombies zombie);

    void delete(Long id);
}

