package com.epf.services;

import com.epf.persistance.Zombies;
import com.epf.persistance.dao.ZombiesDao;
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
    public Zombies save(Zombies zombie) {
        return zombiesDao.save(zombie);
    }

    @Override
    public Optional<Zombies> findById(Long id) {
        return zombiesDao.findById(id);
    }

    @Override
    public List<Zombies> findAll() {
        return zombiesDao.findAll();
    }

    @Override
    public List<Zombies> findByMapId(Long mapId) {
        return zombiesDao.findByMapId(mapId);
    }

    @Override
    public void update(Zombies zombie) {
        zombiesDao.update(zombie);
    }

    @Override
    public void delete(Long id) {
        zombiesDao.delete(id);
    }
}

