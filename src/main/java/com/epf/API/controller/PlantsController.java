package com.epf.API.controller;

import com.epf.core.services.PlantsServices;
import com.epf.persistence.model.Plants;
import com.epf.API.mapper.PlantsMapper;
import com.epf.API.dto.PlantsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/plantes")
public class PlantsController {

    private final PlantsServices plantsService;
    private final PlantsMapper plantsMapper;

    @Autowired
    public PlantsController(PlantsServices plantsService, PlantsMapper plantsMapper) {
        this.plantsService = plantsService;
        this.plantsMapper = plantsMapper;
    }

    @GetMapping
    public ResponseEntity<List<PlantsDto>> getAllPlants() {
        List<Plants> plants = plantsService.findAll();
        List<PlantsDto> plantsDtoList = plants.stream()
                .map(plantsMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(plantsDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Plants> getPlantById(@PathVariable("id") int id) {
        Optional<Plants> plant = plantsService.findById(id);
        return plant.map(ResponseEntity::ok)
                   .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Plants> createPlant(@RequestBody Plants plant) {
        return ResponseEntity.ok(plantsService.save(plant));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlantsDto> updatePlant(@PathVariable("id") int id, @RequestBody PlantsDto plantDto) {
        try {
            if (plantsService.findById(id).isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            plantDto.setIdPlante(id);
            Plants plant = PlantsMapper.toEntity(plantDto);
            plantsService.update(plant);
            return ResponseEntity.ok(plantsMapper.toDTO(plant));
        } catch (Exception e) {
            System.err.println("Error updating plant: " + e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlant(@PathVariable("id")  int id) {
        if (plantsService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        plantsService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

