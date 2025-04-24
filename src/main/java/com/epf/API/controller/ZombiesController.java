package com.epf.API.controller;

import com.epf.API.dto.ZombiesDto;
import com.epf.API.mapper.ZombiesMapper;
import com.epf.persistence.model.Zombies;
import com.epf.core.services.ZombiesServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
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
        List<ZombiesDto> zombiesDtoList = zombies.stream()
                .map(zombiesMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(zombiesDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ZombiesDto> getZombieById(@PathVariable("id") int id) {
        Optional<Zombies> zombie = zombiesService.findById(id);
        return zombie.map(z -> ResponseEntity.ok(zombiesMapper.toDto(z)))
                    .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ZombiesDto> createZombie(@RequestBody ZombiesDto zombieDto) {
        Zombies zombie = zombiesMapper.toModel(zombieDto);
        Zombies savedZombie = zombiesService.save(zombie);
        return ResponseEntity.ok(zombiesMapper.toDto(savedZombie));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ZombiesDto> updateZombie(@PathVariable("id") int id, @RequestBody ZombiesDto zombieDto) {
        try {
            if (zombiesService.findById(id).isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            zombieDto.setId_zombie(id);
            Zombies zombie = zombiesMapper.toModel(zombieDto);
            zombiesService.update(zombie);
            return ResponseEntity.ok(zombiesMapper.toDto(zombie));
        } catch (Exception e) {
            System.err.println("Error updating zombie: " + e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteZombie(@PathVariable("id") int id) {
        if (zombiesService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        zombiesService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
