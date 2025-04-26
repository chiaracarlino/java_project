package com.epf.core;

import com.epf.core.services.ZombiesServices;
import com.epf.persistence.model.Zombies;
import com.epf.persistence.dao.ZombiesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ZombiesServicesImpl implements ZombiesServices {

    private final ZombiesDao zombiesDao;

    @Autowired
    public ZombiesServicesImpl(ZombiesDao zombiesDao) {
        this.zombiesDao = zombiesDao;
    }

    @Override
    public Zombies createZombie(Zombies zombie) {
        return zombiesDao.createZombie(zombie);
    }

    @Override
    public Optional<Zombies> findById(int id) {
        return zombiesDao.findById(id);
    }

    @Override
    public List<Zombies> findAll() {
        return zombiesDao.findAll();
    }

    @Override
    public List<Zombies> findByMapId(int mapId) {
        return zombiesDao.findByMapId(mapId);
    }

    @Override
    public Zombies updateZombie(Zombies zombie) {
        zombiesDao.updateZombie(zombie);
        return zombie;
    }

    @Override
    public void deleteZombie(int id) {
        zombiesDao.deleteZombie(id);
    }
}

