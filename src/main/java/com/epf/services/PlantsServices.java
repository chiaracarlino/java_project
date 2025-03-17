package com.epf.services;

import com.epf.persistance.Plants;
import java.util.List;
import java.util.Optional;

public interface PlantsServices {
    List<Plants> findAll();
    Optional<Plants> findById(Long id);
    Plants save(Plants plant);

    void update(Plants plant);

    void delete(Long id);
}
