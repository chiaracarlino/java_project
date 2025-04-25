package com.epf.API.controller;

import com.epf.API.dto.MapsDto;
import com.epf.API.mapper.MapsMapper;
import com.epf.persistence.model.Maps;
import com.epf.core.services.MapsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/maps")
public class MapsController {

    private final MapsServices mapsService;

    @Autowired
    public MapsController(MapsServices mapsService) {
        this.mapsService = mapsService;
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
    public ResponseEntity<Void> deleteMap(@PathVariable("id") int id) {
        if (mapsService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        mapsService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
