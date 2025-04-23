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
    public ResponseEntity<Plants> getPlantById(@PathVariable Long id) {
        Optional<Plants> plant = plantsService.findById(id);
        return plant.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Plants> createPlant(@RequestBody Plants plant) {
        return ResponseEntity.ok(plantsService.save(plant));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Plants> updatePlant(@PathVariable Long id, @RequestBody Plants updatedPlant) {
        if (plantsService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        updatedPlant.setIdPlante(id);
        plantsService.update(updatedPlant);
        return ResponseEntity.ok(updatedPlant);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlant(@PathVariable Long id) {
        if (plantsService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        plantsService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

