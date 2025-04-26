package com.epf.API.controller;

import com.epf.API.dto.MapsDto;
import com.epf.API.mapper.MapsMapper;
import com.epf.persistence.model.Maps;
import com.epf.core.services.MapsServices;
import com.epf.core.services.ZombiesServices;
import com.epf.persistence.model.Zombies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/maps")
public class MapsController {

    private final MapsServices mapsService;
    private final ZombiesServices zombiesService;

    @Autowired
    public MapsController(MapsServices mapsService, ZombiesServices zombiesService) {
        this.mapsService = mapsService;
        this.zombiesService = zombiesService;
    }

    @GetMapping
    public ResponseEntity<List<MapsDto>> getAllMaps() {
        List<MapsDto> dtos = mapsService.findAll().stream()
                .map(MapsMapper::toDTO)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MapsDto> getMapById(@PathVariable("id")  int id) {
        return mapsService.findById(id)
                .map(MapsMapper::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<MapsDto> createMap(@RequestBody MapsDto dto) {
        Maps saved = mapsService.save(MapsMapper.toEntity(dto));
        return ResponseEntity.ok(MapsMapper.toDTO(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MapsDto> updateMap(@PathVariable("id")  int id, @RequestBody MapsDto dto) {
        if (mapsService.findById(id).isEmpty()) return ResponseEntity.notFound().build();
        Maps toUpdate = MapsMapper.toEntity(dto);
        toUpdate.setIdMap(id); 
        mapsService.update(toUpdate);
        return ResponseEntity.ok(MapsMapper.toDTO(toUpdate));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMap(@PathVariable("id") int id) {
        try {
            // Vérifier si la map existe
            if (mapsService.findById(id).isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Map non trouvée avec l'id: " + id);
            }

            try {
                // Récupérer et supprimer les zombies associés
                List<Zombies> zombiesAssocies = zombiesService.findByMapId(id);
                for (Zombies zombie : zombiesAssocies) {
                    zombiesService.deleteZombie(zombie.getIdZombie());
                }

                // Supprimer la map
                mapsService.delete(id);
                return ResponseEntity.ok("Map et zombies associés supprimés avec succès!");
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.internalServerError()
                    .body("Erreur lors de la suppression des zombies : " + e.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError()
                    .body("Erreur lors de la suppression de la map : " + e.getMessage());
        }
    }
}
