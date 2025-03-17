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
    public PlantsServicesImpl(PlantsDao plantsDao) {
        this.plantsDao = plantsDao;
    }

    @Override
    public Plants save(Plants plant) {
        return plantsDao.save(plant);
    }

    @Override
    public Optional<Plants> findById(Long id) {
        return plantsDao.findById(id);
    }

    @Override
    public List<Plants> findAll() {
        return plantsDao.findAll();
    }

    @Override
    public void update(Plants plant) {
        plantsDao.update(plant);
    }

    @Override
    public void delete(Long id) {
        plantsDao.delete(id);
    }
}

