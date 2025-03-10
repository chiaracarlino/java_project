package com.epf.persistance.dao;

import com.epf.persistance.Zombies;
import com.epf.persistance.ZombiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ZombiesDaoImpl implements ZombiesDao {

    @Autowired
    private ZombiesRepository zombieRepository;

    @Override
    public Zombies save(Zombies zombie) {
        return zombieRepository.save(zombie);
    }

    @Override
    public Optional<Zombies> findById(Long id) {
        Zombies zombie = zombieRepository.findById(id); // Récupère le zombie
        return Optional.ofNullable(zombie);             // Enveloppe dans un Optional
    }


    @Override
    public List<Zombies> findAll() {
        return zombieRepository.findAll();
    }

    @Override
    public List<Zombies> findByMapId(Long mapId) {
        return zombieRepository.findByMapId(mapId); // 🧠 Récupère les zombies liés à une map
    }

    @Override
    public void update(Zombies zombie) {
        zombieRepository.update(zombie);
    }

    @Override
    public void delete(Long id) {
        zombieRepository.deleteById(id);
    }
}

