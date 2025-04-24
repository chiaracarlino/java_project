package com.epf.persistence.dao;

import com.epf.persistence.model.Maps;

import java.util.List;
import java.util.Optional;

public interface MapsDao {
    Maps save(Maps map);
    Optional<Maps> findById(int id);
    List<Maps> findAll();
    void update(Maps map);
    void delete(int id);
}

