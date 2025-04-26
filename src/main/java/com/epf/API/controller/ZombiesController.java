package com.epf.API.controller;

import com.epf.persistence.model.Zombies;
import com.epf.core.services.ZombiesServices;
import com.epf.API.mapper.ZombiesMapper;
import com.epf.API.dto.ZombiesDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/zombies")
public class ZombiesController {
    private final ZombiesServices zombiesService;
    private final ZombiesMapper zombiesMapper;

    @Autowired
    public ZombiesController(ZombiesServices zombiesService, ZombiesMapper zombiesMapper) {
        this.zombiesService = zombiesService;
        this.zombiesMapper = zombiesMapper;
    }

    @GetMapping
    public ResponseEntity<List<ZombiesDto>> getAllZombies() {
        List<Zombies> zombies = zombiesService.findAll();
        return ResponseEntity.ok(zombies.stream()
                .map(zombiesMapper::toDto)
                .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ZombiesDto> getZombieById(@PathVariable int id) {
        return zombiesService.findById(id)
                .map(zombiesMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ZombiesDto> createZombie(@RequestBody ZombiesDto zombieDto) {
        // Manual validation
        if (!isValidZombieDto(zombieDto)) {
            return ResponseEntity.badRequest().build();
        }

        try {
            Zombies zombie = zombiesMapper.toEntity(zombieDto);
            Zombies createdZombie = zombiesService.createZombie(zombie);
            return ResponseEntity.ok(zombiesMapper.toDto(createdZombie));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ZombiesDto> updateZombie(
        @PathVariable("id") int id,
        @RequestBody ZombiesDto zombieDto) {
        
        if (zombieDto == null) {
            return ResponseEntity.badRequest().build();
        }

        // Validate point_de_vie 
        if (zombieDto.getPoint_de_vie() <= 0) {
            return ResponseEntity.badRequest().build();
        }

        try {
            if (zombiesService.findById(id).isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            
            Zombies zombie = zombiesMapper.toEntity(zombieDto);
            zombie.setIdZombie(id);
            Zombies updatedZombie = zombiesService.updateZombie(zombie);
            return ResponseEntity.ok(zombiesMapper.toDto(updatedZombie));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteZombie(@PathVariable int id) {
        if (zombiesService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        zombiesService.deleteZombie(id);
        return ResponseEntity.noContent().build();
    }

    // Helper method for validation
    private boolean isValidZombieDto(ZombiesDto zombieDto) {
        if (zombieDto == null) return false;
        if (zombieDto.getNom() == null || zombieDto.getNom().trim().isEmpty()) return false;
        if (zombieDto.getPoint_de_vie() <= 0) return false;
        if (zombieDto.getDegat_attaque() < 0) return false;
        if (zombieDto.getId_map() != null && zombieDto.getId_map() <= 0) return false;
        return true;
    }
}
