package com.epf.API.controller;

import com.epf.persistence.model.Zombies;
import com.epf.core.services.ZombiesServices;
import com.epf.API.mapper.ZombiesMapper;
import com.epf.API.dto.ZombiesDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        @PathVariable(name = "id", required = true) int id,
        @RequestBody ZombiesDto zombieDto) {
        
        System.out.println("DEBUG - Updating zombie id=" + id + " with data: " + zombieDto);

        try {
            // 1. Vérifier si le zombie existe
            Optional<Zombies> existingZombie = zombiesService.findById(id);
            Zombies currentZombie;
            if (existingZombie.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(null); // Return 404 if the zombie does not exist
            } else {
                currentZombie = existingZombie.get();
            }

            // Créer un nouveau zombie avec conservation des valeurs existantes
            Zombies zombie = new Zombies();
            zombie.setIdZombie(id);
            
            // Mettre à jour seulement les champs non-null du DTO
            zombie.setNom(zombieDto.getNom() != null ? zombieDto.getNom() : currentZombie.getNom());
            zombie.setPointDeVie(zombieDto.getPoint_de_vie() != null ? zombieDto.getPoint_de_vie() : currentZombie.getPointDeVie());
            zombie.setDegatAttaque(zombieDto.getDegat_attaque() != null ? zombieDto.getDegat_attaque() : currentZombie.getDegatAttaque());
            zombie.setIdMap(zombieDto.getId_map() != null ? zombieDto.getId_map() : currentZombie.getIdMap());
            zombie.setCheminImage(zombieDto.getChemin_image() != null ? zombieDto.getChemin_image() : currentZombie.getCheminImage());
            
            // Conserver les valeurs avancées du zombie existant
            zombie.setAttaqueParSeconde(currentZombie.getAttaqueParSeconde());
            zombie.setVitesseDeDeplacement(currentZombie.getVitesseDeDeplacement());

            System.out.println("DEBUG - Updating zombie with final data: " + zombie);
            
            Zombies updatedZombie = zombiesService.updateZombie(zombie);
            return ResponseEntity.ok(zombiesMapper.toDto(updatedZombie));
        } catch (Exception e) {
            System.out.println("DEBUG - Error updating zombie: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteZombie(@PathVariable("id") int id) {
        try {
            // Vérifier si le zombie existe
            if (zombiesService.findById(id).isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Zombie non trouvé avec l'id: " + id);
            }

            try {
                zombiesService.deleteZombie(id);
                return ResponseEntity.ok("Zombie supprimé avec succès!");
            } catch (Exception e) {
                System.out.println("Error deleting zombie: " + e.getMessage());
                return ResponseEntity.status(500)
                    .body("Erreur lors de la suppression : " + e.getMessage());
            }
        } catch (Exception e) {
            System.out.println("Error finding zombie: " + e.getMessage());
            return ResponseEntity.status(500)
                .body("Erreur lors de la recherche du zombie : " + e.getMessage());
        }
    }

    private boolean isValidZombieDto(ZombiesDto zombieDto) {
        if (zombieDto == null) return false;
        if (zombieDto.getNom() == null || zombieDto.getNom().trim().isEmpty()) return false;
        if (zombieDto.getPoint_de_vie() <= 0) return false;
        if (zombieDto.getDegat_attaque() < 0) return false;
        if (zombieDto.getId_map() != null && zombieDto.getId_map() <= 0) return false;
        return true;
    }
}
