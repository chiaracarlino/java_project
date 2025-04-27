package com.epf.core.services;

import com.epf.persistence.model.Plants;

import java.util.List;
import java.util.Optional;

public interface PlantsServices {
    List<Plants> findAll();
    Optional<Plants> findById(int id);
    Plants save(Plants plant);
    Plants update(Plants plant);
    void delete(int id);
}

