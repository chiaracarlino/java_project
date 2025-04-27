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
        System.out.println("DEBUG - Received plant: " + plant);

        try {
            // Validation de base
            if (plant == null) {
                System.out.println("DEBUG - Plant is null");
                return ResponseEntity.badRequest().build();
            }

            // Définir les valeurs par défaut AVANT la validation
            if (plant.getAttaqueParSeconde() == null) plant.setAttaqueParSeconde(0.0);
            if (plant.getSoleilParSeconde() == null) plant.setSoleilParSeconde(0.0);
            if (plant.getCheminImage() == null) plant.setCheminImage("");
            if (plant.getEffet() == null) plant.setEffet("");

            // Validation des champs requis
            if (plant.getNom() == null || plant.getNom().trim().isEmpty()) {
                System.out.println("DEBUG - Invalid name");
                return ResponseEntity.badRequest().build();
            }

            Plants savedPlant = plantsService.save(plant);
            System.out.println("DEBUG - Plant saved: " + savedPlant);
            return ResponseEntity.ok(savedPlant);
        } catch (Exception e) {
            System.out.println("DEBUG - Error: " + e.getMessage());
            e.printStackTrace();
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

