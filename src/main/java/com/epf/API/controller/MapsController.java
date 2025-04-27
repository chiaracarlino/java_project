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
import java.util.Optional;

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
    public ResponseEntity<MapsDto> updateMap(
            @PathVariable("id") int id,
            @RequestBody MapsDto mapDto) {
        
        System.out.println("DEBUG - Updating map id=" + id + " with data: " + mapDto);

        try {
            Optional<Maps> existingMap = mapsService.findById(id);
            Maps currentMap;
            
            if (existingMap.isEmpty()) {
                List<Maps> maps = mapsService.findAll();
                Optional<Maps> mapByProperties = maps.stream()
                    .filter(m -> m.getLigne() == mapDto.getLigne() 
                        && m.getColonne() == mapDto.getColonne())
                    .findFirst();
                
                if (mapByProperties.isPresent()) {
                    currentMap = mapByProperties.get();
                    id = currentMap.getIdMap();
                    System.out.println("DEBUG - Found map by properties with new id: " + id);
                } else {
                    System.out.println("DEBUG - Map not found by id or properties");
                    return ResponseEntity.notFound().build();
                }
            } else {
                currentMap = existingMap.get();
            }
            Maps map = MapsMapper.toEntity(mapDto);
            map.setIdMap(id);
            
            if (mapDto.getLigne() == null) map.setLigne(currentMap.getLigne());
            if (mapDto.getColonne() == null) map.setColonne(currentMap.getColonne());
            if (mapDto.getChemin_image() == null) map.setCheminImage(currentMap.getCheminImage());

            System.out.println("DEBUG - Updating map with final data: " + map);
            
            Maps updatedMap = mapsService.update(map);
            return ResponseEntity.ok(MapsMapper.toDTO(updatedMap));
        } catch (Exception e) {
            System.out.println("DEBUG - Error updating map: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMap(@PathVariable("id") int id) {
        try {
            if (mapsService.findById(id).isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Map non trouvée avec l'id: " + id);
            }

            try {
                List<Zombies> zombiesAssocies = zombiesService.findByMapId(id);
                for (Zombies zombie : zombiesAssocies) {
                    zombiesService.deleteZombie(zombie.getIdZombie());
                }

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
