package com.epf.core;

import com.epf.core.services.PlantsServices;
import com.epf.persistence.model.Plants;
import com.epf.persistence.repository.PlantsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlantsServicesImpl implements PlantsServices {

    private final PlantsRepository plantsRepository;

    @Autowired
    public PlantsServicesImpl(PlantsRepository plantsRepository) {
        this.plantsRepository = plantsRepository;
    }

    @Override
    public List<Plants> findAll() {
        return plantsRepository.findAll();
    }

    @Override
    public Optional<Plants> findById(int id) {
        return plantsRepository.findById(id);
    }

    @Override
    public Plants save(Plants plant) {
        if (plant.getNom() == null || plant.getNom().trim().isEmpty()) {
            throw new IllegalArgumentException("Le nom de la plante est obligatoire");
        }
        if (plant.getPointDeVie() <= 0) {
            throw new IllegalArgumentException("Les points de vie doivent être positifs");
        }
        if (plant.getDegatAttaque() < 0) {
            throw new IllegalArgumentException("Les dégâts d'attaque ne peuvent pas être négatifs");
        }
        if (plant.getCout() < 0) {
            throw new IllegalArgumentException("Le coût ne peut pas être négatif");
        }
        if (plant.getAttaqueParSeconde() == null) {
            plant.setAttaqueParSeconde(0.0);
        }
        if (plant.getSoleilParSeconde() == null) {
            plant.setSoleilParSeconde(0.0);
        }

        return plantsRepository.save(plant);
    }

    @Override
    public Plants update(Plants plant) {
        if (plant == null) {
            throw new IllegalArgumentException("Plant cannot be null");
        }
        if (findById(plant.getIdPlante()).isEmpty()) {
            throw new IllegalArgumentException("Plant with id " + plant.getIdPlante() + " not found");
        }

        if (plant.getNom() == null || plant.getNom().trim().isEmpty()) {
            throw new IllegalArgumentException("Le nom de la plante est obligatoire");
        }
        if (plant.getPointDeVie() <= 0) {
            throw new IllegalArgumentException("Les points de vie doivent être positifs");
        }
        if (plant.getDegatAttaque() < 0) {
            throw new IllegalArgumentException("Les dégâts d'attaque ne peuvent pas être négatifs");
        }
        if (plant.getCout() < 0) {
            throw new IllegalArgumentException("Le coût ne peut pas être négatif");
        }
        if (plant.getAttaqueParSeconde() == null) {
            plant.setAttaqueParSeconde(0.0);
        }
        if (plant.getSoleilParSeconde() == null) {
            plant.setSoleilParSeconde(0.0);
        }
        if (plant.getEffet() == null) {
            plant.setEffet("");
        }
        if (plant.getCheminImage() == null) {
            plant.setCheminImage("");
        }
        
        plantsRepository.update(plant);
        
        return findById(plant.getIdPlante())
            .orElseThrow(() -> new RuntimeException("Failed to retrieve updated plant"));
    }

    @Override
    public void delete(int id) {
        if (findById(id).isEmpty()) {
            throw new IllegalArgumentException("Plant with id " + id + " not found");
        }
        plantsRepository.delete(id);
    }
}



