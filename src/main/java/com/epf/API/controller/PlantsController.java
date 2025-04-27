package com.epf.API.controller;

import com.epf.core.services.PlantsServices;
import com.epf.persistence.model.Plants;
import com.epf.API.dto.PlantsDto;
import com.epf.API.mapper.PlantsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
        return ResponseEntity.ok(plants.stream()
                .map(plantsMapper::toDTO)
                .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlantsDto> getPlantById(@PathVariable("id") int id) {
        return plantsService.findById(id)
                .map(plantsMapper::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PlantsDto> createPlant(@RequestBody PlantsDto plantDto) {
        System.out.println("DEBUG - Received plant DTO: " + plantDto);

        try {
            // Validation plus permissive pour correspondre au frontend
            if (plantDto == null) {
                System.out.println("DEBUG - Plant DTO is null");
                return ResponseEntity.badRequest().build();
            }

            // Set default values in DTO level before conversion
            if (plantDto.getNom() == null) plantDto.setNom("");
            if (plantDto.getPoint_de_vie() == null) plantDto.setPoint_de_vie(100);
            if (plantDto.getDegat_attaque() == null) plantDto.setDegat_attaque(0);
            if (plantDto.getCout() == null) plantDto.setCout(0);
            
            Plants plant = plantsMapper.toEntity(plantDto);
            setDefaultValues(plant);
            
            Plants savedPlant = plantsService.save(plant);
            System.out.println("DEBUG - Plant saved successfully: " + savedPlant);
            return ResponseEntity.ok(plantsMapper.toDTO(savedPlant));

        } catch (Exception e) {
            System.out.println("DEBUG - Error creating plant: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlantsDto> updatePlant(
            @PathVariable("id") int id,
            @RequestBody PlantsDto plantDto) {
        if (!isValidPlantDto(plantDto)) {
            return ResponseEntity.badRequest().build();
        }

        if (plantsService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Plants plant = plantsMapper.toEntity(plantDto);
        plant.setIdPlante(id);
        setDefaultValues(plant);
        
        Plants updatedPlant = plantsService.update(plant);
        return ResponseEntity.ok(plantsMapper.toDTO(updatedPlant));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlant(@PathVariable("id") int id) {
        if (plantsService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        plantsService.delete(id);
        return ResponseEntity.noContent().build();
    }

    private boolean isValidPlantDto(PlantsDto plantDto) {
        return plantDto != null; // Simplified validation since frontend handles type checking
    }

    private void setDefaultValues(Plants plant) {
        if (plant.getAttaqueParSeconde() == null) plant.setAttaqueParSeconde(0.0);
        if (plant.getSoleilParSeconde() == null) plant.setSoleilParSeconde(0.0);
        if (plant.getEffet() == null) plant.setEffet("");
        if (plant.getCheminImage() == null) plant.setCheminImage("");
    }
}

