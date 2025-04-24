package com.epf.core;

import com.epf.core.services.PlantsServices;
import com.epf.persistence.model.Plants;
import com.epf.persistence.repository.PlantsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlantsServicesImpl implements PlantsServices {

    private final PlantsRepository plantsRepository;

    @Autowired
    public PlantsServicesImpl(PlantsRepository plantsRepository) {
        this.plantsRepository = plantsRepository;
    }

    @Override
    public List<Plants> findAll() {
        return plantsRepository.findAll();
    }

    @Override
    public Optional<Plants> findById(int id) {
        return plantsRepository.findById(id);
    }

    @Override
    public Plants save(Plants plant) {
        return plantsRepository.save(plant);
    }

    @Override
    public void update(Plants plant) {
        plantsRepository.update(plant);
    }

    @Override
    public void delete(int id) {
        plantsRepository.deleteById(id);
    }
}



