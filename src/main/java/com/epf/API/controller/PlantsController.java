package com.epf.API.controller;

import com.epf.core.services.PlantsServices;
import com.epf.persistence.model.Plants;
import com.epf.API.dto.PlantsDto;
import com.epf.API.mapper.PlantsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
            if (plantDto == null) {
                System.out.println("DEBUG - Plant DTO is null");
                return ResponseEntity.badRequest().build();
            }

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
        
        System.out.println("DEBUG - Updating plant id=" + id + " with data: " + plantDto);

        try {
            Optional<Plants> existingPlant = plantsService.findById(id);
            Plants currentPlant;
            
            if (existingPlant.isEmpty()) {
                List<Plants> plants = plantsService.findAll();
                Optional<Plants> plantByName = plants.stream()
                    .filter(p -> p.getNom().equals(plantDto.getNom()))
                    .findFirst();
                
                if (plantByName.isPresent()) {
                    currentPlant = plantByName.get();
                    id = currentPlant.getIdPlante();
                    System.out.println("DEBUG - Found plant by name with new id: " + id);
                } else {
                    System.out.println("DEBUG - Plant not found by id or name");
                    return ResponseEntity.notFound().build();
                }
            } else {
                currentPlant = existingPlant.get();
            }

            Plants plant = new Plants();
            plant.setIdPlante(id); 
            
            plant.setNom(plantDto.getNom() != null ? plantDto.getNom() : currentPlant.getNom());
            plant.setPointDeVie(plantDto.getPoint_de_vie() != null ? plantDto.getPoint_de_vie() : currentPlant.getPointDeVie());
            plant.setDegatAttaque(plantDto.getDegat_attaque() != null ? plantDto.getDegat_attaque() : currentPlant.getDegatAttaque());
            plant.setCout(plantDto.getCout() != null ? plantDto.getCout() : currentPlant.getCout());
            plant.setCheminImage(plantDto.getChemin_image() != null ? plantDto.getChemin_image() : currentPlant.getCheminImage());
            
            plant.setAttaqueParSeconde(currentPlant.getAttaqueParSeconde());
            plant.setSoleilParSeconde(currentPlant.getSoleilParSeconde());
            plant.setEffet(currentPlant.getEffet());

            System.out.println("DEBUG - Updating plant with final data: " + plant);
            
            Plants updatedPlant = plantsService.update(plant);
            return ResponseEntity.ok(plantsMapper.toDTO(updatedPlant));
        } catch (Exception e) {
            System.out.println("DEBUG - Error updating plant: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlant(@PathVariable("id") int id) {
        if (plantsService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        plantsService.delete(id);
        return ResponseEntity.noContent().build();
    }

    private void setDefaultValues(Plants plant) {
        if (plant.getAttaqueParSeconde() == null) plant.setAttaqueParSeconde(0.0);
        if (plant.getSoleilParSeconde() == null) plant.setSoleilParSeconde(0.0);
        if (plant.getEffet() == null) plant.setEffet("");
        if (plant.getCheminImage() == null) plant.setCheminImage("");
    }
}

