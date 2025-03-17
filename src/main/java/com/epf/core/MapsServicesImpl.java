package com.epf.core;

import com.epf.core.services.MapsServices;
import com.epf.persistence.model.Maps;
import com.epf.persistence.dao.MapsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MapsServicesImpl implements MapsServices {

    private final MapsDao mapsDao;

    @Autowired
    public MapsServicesImpl(MapsDao plantsDao) {
        this.mapsDao = plantsDao;
    }

    @Override
    public Maps save(Maps plant) {
        return mapsDao.save(plant);
    }

    @Override
    public Optional<Maps> findById(Long id) {
        return mapsDao.findById(id);
    }

    @Override
    public List<Maps> findAll() {
        return mapsDao.findAll();
    }

    @Override
    public void update(Maps plant) {
        mapsDao.update(plant);
    }

    @Override
    public void delete(Long id) {
        mapsDao.delete(id);
    }
}

