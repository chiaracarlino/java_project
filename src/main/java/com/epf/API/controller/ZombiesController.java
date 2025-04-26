package com.epf.API.controller;

import com.epf.API.dto.ZombiesDto;
import com.epf.API.mapper.ZombiesMapper;
import com.epf.persistence.model.Zombies;
import com.epf.core.services.ZombiesServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        List<ZombiesDto> zombiesDtoList = zombies.stream()
                .map(zombiesMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(zombiesDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ZombiesDto> getZombieById(@PathVariable("id") int id) {
        try {
            return zombiesService.findById(id)
                    .map(zombie -> ResponseEntity.ok(zombiesMapper.toDto(zombie)))
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<ZombiesDto> createZombie(@RequestBody ZombiesDto zombieDto) {
        try {
            Zombies saved = zombiesService.createZombie(zombiesMapper.toModel(zombieDto));
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(zombiesMapper.toDto(saved));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<ZombiesDto> updateZombie(@PathVariable("id") int id, @RequestBody ZombiesDto zombieDto) {
        try {
            if (zombiesService.findById(id).isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            zombieDto.setId_zombie(id);
            Zombies toUpdate = zombiesMapper.toModel(zombieDto);
            zombiesService.updateZombie(toUpdate);
            return ResponseEntity.ok(zombieDto);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteZombie(@PathVariable("id") int id) {
        try {
            if (zombiesService.findById(id).isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            zombiesService.deleteZombie(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
