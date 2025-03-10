package com.epf.services;

import com.epf.persistance.Plants;
import com.epf.persistance.dao.PlantsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlantsServicesImpl implements PlantsServices {

    private final PlantsDao plantsDao;

    @Autowired
    public PlantsServicesImpl(PlantsDao zombiesDao) {
        this.plantsDao = zombiesDao;
    }

    @Override
    public List<Plants> findAll() {
        return plantsDao.findAll();
    }

    @Override
    public Optional<Plants> findById(Long id) {
        return plantsDao.findById(id);
    }

    @Override
    public Plants save(Plants zombie) {
        return plantsDao.save(zombie);
    }

    @Override
    public void delete(Long id) {
        plantsDao.delete(id);
    }
}
