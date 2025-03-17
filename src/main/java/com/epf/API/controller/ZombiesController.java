package com.epf.API.controller;

import com.epf.core.services.ZombiesServices;

public class ZombiesController {

import com.epf.API.controller.ZombiesController;
import com.epf.API.dto.ZombiesDto;
import ZombiesServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation .*;

import java.util.List;

    @RestController
    @RequestMapping("/zombies")
    public class ZombiesControllerImpl implements ZombiesController {

        @Autowired
        private ZombiesServices zombieService;

        @Override
        @GetMapping
        public List<ZombiesDto> getAllZombies() {
            return zombieService.getAllZombies();
        }

        @Override
        @GetMapping("/{id}")
        public ZombiesDto getZombieById(@PathVariable Long id) {
            return zombieService.getZombieById(id);
        }

        @Override
        @PostMapping
        public ZombiesDto createZombie(@RequestBody ZombiesDto zombieDTO) {
            return zombieService.createZombie(zombieDTO);
        }

        @Override
        @PutMapping("/{id}")
        public ZombiesDto updateZombie(@PathVariable Long id, @RequestBody ZombiesDto zombieDTO) {
            return zombieService.updateZombie(id, zombieDTO);
        }

        @Override
        @DeleteMapping("/{id}")
        public void deleteZombie(@PathVariable Long id) {
            zombieService.deleteZombie(id);
        }
    }
}