package com.epf.API.controller;

import com.epf.persistence.model.Zombies;
import com.epf.core.services.ZombiesServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/zombies")
public class ZombiesController {

    private final ZombiesServices zombiesService;

    @Autowired
    public ZombiesController(ZombiesServices zombiesService) {
        this.zombiesService = zombiesService;
    }

    @GetMapping
    public ResponseEntity<List<Zombies>> getAllZombies() {
        return ResponseEntity.ok(zombiesService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Zombies> getZombieById(@PathVariable Long id) {
        Optional<Zombies> zombie = zombiesService.findById(id);
        return zombie.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Zombies> createZombie(@RequestBody Zombies zombie) {
        Zombies newZombie = zombiesService.save(zombie);
        return ResponseEntity.ok(newZombie);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Zombies> updateZombie(@PathVariable Long id, @RequestBody Zombies updatedZombie) {
        if (zombiesService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        updatedZombie.setId(id); // On garde le même ID
        zombiesService.update(updatedZombie);
        return ResponseEntity.ok(updatedZombie);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteZombie(@PathVariable Long id) {
        if (zombiesService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        zombiesService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
