package com.epf.persistance.dao;

import com.epf.persistance.Plants;
import com.epf.persistance.PlantsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PlantsDaoImpl implements PlantsDao {

    @Autowired
    private PlantsRepository plantRepository;

    @Override
    public Plants save(Plants plant) {
        return plantRepository.save(plant);
    }

    @Override
    public Optional<Plants> findById(Long id) {
        return plantRepository.findById(id);
    }

    @Override
    public List<Plants> findAll() {
        return plantRepository.findAll();
    }

    @Override
    public void update(Plants plant) {
        plantRepository.update(plant);
    }

    @Override
    public void delete(Long id) {
        plantRepository.deleteById(id);
    }
}

