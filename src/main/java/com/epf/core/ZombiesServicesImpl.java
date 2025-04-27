package com.epf.core;

import com.epf.core.services.ZombiesServices;
import com.epf.persistence.model.Zombies;
import com.epf.persistence.dao.ZombiesDao;
import com.epf.persistence.dao.MapsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ZombiesServicesImpl implements ZombiesServices {

    private final ZombiesDao zombiesDao;
    private final MapsDao mapsDao;  

    @Autowired
    public ZombiesServicesImpl(ZombiesDao zombiesDao, MapsDao mapsDao) {
        this.zombiesDao = zombiesDao;
        this.mapsDao = mapsDao;
    }

    @Override
    public Zombies save(Zombies zombie) {
        validateZombie(zombie);
        
        if (zombie.getIdMap() != null) {
            mapsDao.findById(zombie.getIdMap())
                .orElseThrow(() -> new RuntimeException("Map non trouvée avec l'ID: " + zombie.getIdMap()));
        }
        
        return zombiesDao.save(zombie);
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
        mapsDao.findById(mapId)
            .orElseThrow(() -> new RuntimeException("Map non trouvée avec l'ID: " + mapId));
        return zombiesDao.findByMapId(mapId);
    }

    @Override
    public Zombies update(Zombies zombie) {

        zombiesDao.findById(zombie.getId())
            .orElseThrow(() -> new RuntimeException("Zombie non trouvé avec l'ID: " + zombie.getId()));
        
        validateZombie(zombie);
        
        if (zombie.getIdMap() != null) {
            mapsDao.findById(zombie.getIdMap())
                .orElseThrow(() -> new RuntimeException("Map non trouvée avec l'ID: " + zombie.getIdMap()));
        }
        
        zombiesDao.update(zombie);
        return zombie;
    }

    @Override
    public void delete(int id) {
        zombiesDao.findById(id)
            .orElseThrow(() -> new RuntimeException("Zombie non trouvé avec l'ID: " + id));
        zombiesDao.delete(id);
    }

    private void validateZombie(Zombies zombie) {
        if (zombie.getNom() == null || zombie.getNom().trim().isEmpty()) {
            throw new IllegalArgumentException("Le nom du zombie ne peut pas être vide");
        }

        if (zombie.getPointDeVie() <= 0) {
            throw new IllegalArgumentException("Les points de vie doivent être positifs");
        }

        if (zombie.getAttaqueParSeconde() == null || zombie.getAttaqueParSeconde() < 0) {
            throw new IllegalArgumentException("L'attaque par seconde ne peut pas être négative");
        }

        if (zombie.getDegatAttaque() <= 0) {
            throw new IllegalArgumentException("Les dégâts d'attaque doivent être positifs");
        }

        if (zombie.getVitesseDeDeplacement() == null || zombie.getVitesseDeDeplacement() <= 0) {
            throw new IllegalArgumentException("La vitesse de déplacement doit être positive");
        }

        if (zombie.getCheminImage() == null || zombie.getCheminImage().trim().isEmpty()) {
            throw new IllegalArgumentException("Le chemin de l'image ne peut pas être vide");
        }
    }
}

