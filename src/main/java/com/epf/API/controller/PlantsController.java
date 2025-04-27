package com.epf.API.controller;

import com.epf.core.services.PlantsServices;
import com.epf.persistence.model.Plants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/plantes")
public class PlantsController {
    
    private final PlantsServices plantsService;

    @Autowired
    public PlantsController(PlantsServices plantsService) {
        this.plantsService = plantsService;
    }

    @GetMapping
    public ResponseEntity<List<Plants>> getAllPlants() {
        return ResponseEntity.ok(plantsService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Plants> getPlantById(@PathVariable("id") int id) {
        Optional<Plants> plant = plantsService.findById(id);
        return plant.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Plants> createPlant(@RequestBody Plants plant) {
        System.out.println("Received plant data: " + plant);

        if (plant == null) {
            System.out.println("Plant object is null");
            return ResponseEntity.badRequest().build();
        }

        if (plant.getNom() == null || plant.getNom().trim().isEmpty()) {
            System.out.println("Plant name is null or empty");
            return ResponseEntity.badRequest().build();
        }
        
        if (plant.getPointDeVie() <= 0) {
            System.out.println("Invalid point_de_vie: " + plant.getPointDeVie());
            return ResponseEntity.badRequest().build();
        }

        if (plant.getDegatAttaque() < 0) {
            System.out.println("Invalid degat_attaque: " + plant.getDegatAttaque());
            return ResponseEntity.badRequest().build();
        }

        if (plant.getAttaqueParSeconde() == null) {
            plant.setAttaqueParSeconde(0.0);
        }
        if (plant.getSoleilParSeconde() == null) {
            plant.setSoleilParSeconde(0.0);
        }
        if (plant.getCheminImage() == null) {
            plant.setCheminImage("");
        }

        if (plant.getEffet() == null) {
            plant.setEffet("");
        }

        try {
            Plants savedPlant = plantsService.save(plant);
            System.out.println("Plant saved successfully: " + savedPlant);
            return ResponseEntity.ok(savedPlant);
        } catch (IllegalArgumentException e) {
            System.out.println("Error saving plant: " + e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Plants> updatePlant(@PathVariable("id") int id, @RequestBody Plants plant) {
        if (plantsService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        plant.setIdPlante(id);
        plantsService.update(plant);
        return ResponseEntity.ok(plant);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlant(@PathVariable("id") int id) {
        if (plantsService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        plantsService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

