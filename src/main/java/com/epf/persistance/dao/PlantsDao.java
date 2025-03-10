package com.epf.persistance.dao;

import com.epf.persistance.Plants;

import java.util.List;
import java.util.Optional;

public interface PlantsDao {
    Plants save(Plants plant);
    Optional<Plants> findById(Long id);
    List<Plants> findAll();
    void update(Plants plant);
    void delete(Long id);
}

