package com.epf.core.services;

import com.epf.persistence.model.Plants;

import java.util.List;
import java.util.Optional;

public interface PlantsServices {
    List<Plants> findAll();
    Optional<Plants> findById(Long id);
    Plants save(Plants plant);
    void update(Plants plant);
    void delete(Long id);
}

