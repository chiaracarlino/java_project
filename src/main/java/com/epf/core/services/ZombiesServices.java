package com.epf.core.services;

import com.epf.persistence.model.Zombies;
import java.util.List;
import java.util.Optional;

public interface ZombiesServices {
    List<Zombies> findAll();
    Optional<Zombies> findById(int id);
    List<Zombies> findByMapId(int mapId);
    Zombies createZombie(Zombies zombie);
    Zombies updateZombie(Zombies zombie);
    void deleteZombie(int id);
}

