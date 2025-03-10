package com.epf.persistance.dao;

import com.epf.persistance.Maps;

import java.util.List;
import java.util.Optional;

public interface MapsDao {
    Maps save(Maps map);
    Optional<Maps> findById(Long id);
    List<Maps> findAll();
    void update(Maps map);
    void delete(Long id);
}

