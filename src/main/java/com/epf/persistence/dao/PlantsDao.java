package com.epf.persistence.dao;

import com.epf.persistence.model.Plants;

import java.util.List;
import java.util.Optional;

public interface PlantsDao {
    List<Plants> findAll();
    Optional<Plants> findById(int id);
    Plants save(Plants plant);
    void update(Plants plant);
    void delete(int id);
}


