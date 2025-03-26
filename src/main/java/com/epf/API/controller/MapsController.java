package com.epf.API.controller;

import com.epf.persistence.model.Maps;
import com.epf.core.services.MapsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/maps")
public class MapsController {

    private final MapsServices mapsService;

    @Autowired
    public MapsController(MapsServices mapsService) {
        this.mapsService = mapsService;
    }

    @GetMapping
    public ResponseEntity<List<Maps>> getAllMaps() {
        return ResponseEntity.ok(mapsService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Maps> getMapById(@PathVariable Long id) {
        Optional<Maps> map = mapsService.findById(id);
        return map.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Maps> createMap(@RequestBody Maps map) {
        Maps newMap = mapsService.save(map);
        return ResponseEntity.ok(newMap);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Maps> updateMap(@PathVariable Long id, @RequestBody Maps updatedMap) {
        if (mapsService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        updatedMap.setId(id);
        mapsService.update(updatedMap);
        return ResponseEntity.ok(updatedMap);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMap(@PathVariable Long id) {
        if (mapsService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        mapsService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
