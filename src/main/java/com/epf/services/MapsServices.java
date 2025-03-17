package com.epf.services;

import com.epf.persistance.Maps;
import java.util.List;
import java.util.Optional;

public interface MapsServices {
    List<Maps> findAll();
    Optional<Maps> findById(Long id);
    Maps save(Maps map);

    void update(Maps plant);

    void delete(Long id);
}
