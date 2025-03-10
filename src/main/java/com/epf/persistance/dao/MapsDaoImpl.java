package com.epf.persistance.dao;

import com.epf.persistance.Maps;
import com.epf.persistance.MapsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class MapsDaoImpl implements MapsDao {

    @Autowired
    private MapsRepository mapRepository;

    @Override
    public Maps save(Maps map) {
        return mapRepository.save(map);
    }

    @Override
    public Optional<Maps> findById(Long id) {
        return mapRepository.findById(id);
    }

    @Override
    public List<Maps> findAll() {
        return mapRepository.findAll();
    }

    @Override
    public void update(Maps map) {
        mapRepository.update(map);
    }

    @Override
    public void delete(Long id) {
        mapRepository.deleteById(id);
    }
}
