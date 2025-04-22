package com.epf.core;

import com.epf.core.services.PlantsService;
import com.epf.persistence.dao.PlantsDao;
import com.epf.persistence.model.Plants;
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
    public List<Plants> findAll() {
        return plantsDao.findAll();
    }

    @Override
    public Optional<Plants> findById(Long id) {
        return plantsDao.findById(id);
    }

    @Override
    public Plants save(Plants plant) {
        return plantsDao.save(plant);
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


