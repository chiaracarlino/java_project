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
    public MapsServicesImpl(MapsDao mapsDao) {
        this.mapsDao = mapsDao;
    }

    @Override
    public Maps save(Maps maps) {
        return mapsDao.save(maps);
    }

    @Override
    public Optional<Maps> findById(int id) {
        return mapsDao.findById(id);
    }

    @Override
    public List<Maps> findAll() {
        return mapsDao.findAll();
    }

    @Override
    public void update(Maps maps) {
        mapsDao.update(maps);
    }

    @Override
    public void delete(int id) {
        mapsDao.delete(id);
    }
}

