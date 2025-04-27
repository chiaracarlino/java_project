package API.controller;

 

import com.epf.API.controller.ZombiesController;

import com.epf.core.services.ZombiesServices;

import com.epf.persistence.model.Zombies;

import com.epf.API.dto.ZombiesDto;

import com.epf.API.mapper.ZombiesMapper;

import org.junit.Before;

import org.junit.Test;

import org.mockito.InjectMocks;

import org.mockito.Mock;

import org.mockito.MockitoAnnotations;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;

 

import java.util.Arrays;

import java.util.List;

import java.util.Optional;

 

import static org.mockito.Mockito.*;

import static org.junit.Assert.*;

 

public class ZombiesControllerTest {

 

    @Mock

    private ZombiesServices zombiesService;

 

    @Mock

    private ZombiesMapper zombiesMapper;

 

    @InjectMocks

    private ZombiesController zombiesController;

 

    @Before

    public void setUp() {

        MockitoAnnotations.openMocks(this);

    }

 

    @Test

    public void testGetAllZombies() {

        // Arrange

        Zombies zombie = new Zombies();

        zombie.setId(1);

        zombie.setNom("Basic Zombie");

        zombie.setPointDeVie(100);

        zombie.setDegatAttaque(20);

 

        ZombiesDto zombieDto = new ZombiesDto();

        zombieDto.setId_zombie(1);

        zombieDto.setNom("Basic Zombie");

        zombieDto.setPoint_de_vie(100);

        zombieDto.setDegat_attaque(20);

 

        when(zombiesService.findAll()).thenReturn(Arrays.asList(zombie));

        when(zombiesMapper.toDto(any(Zombies.class))).thenReturn(zombieDto);

 

        // Act

        ResponseEntity<List<ZombiesDto>> response = zombiesController.getAllZombies();

 

        // Assert

        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertNotNull(response.getBody());

        assertEquals(1, response.getBody().size());

    }

 

    @Test

    public void testGetZombieByIdFound() {

        // Arrange

        int id = 1;

        Zombies zombie = new Zombies();

        zombie.setId(id);

        zombie.setNom("Basic Zombie");

 

        ZombiesDto zombieDto = new ZombiesDto();

        zombieDto.setId_zombie(id);

        zombieDto.setNom("Basic Zombie");

 

        when(zombiesService.findById(id)).thenReturn(Optional.of(zombie));

        when(zombiesMapper.toDto(zombie)).thenReturn(zombieDto);

 

        // Act

        ResponseEntity<ZombiesDto> response = zombiesController.getZombieById(id);

 

        // Assert

        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertNotNull(response.getBody());

        assertEquals("Basic Zombie", response.getBody().getNom());

    }

 

    @Test

    public void testCreateZombieSuccess() {

        // Arrange

        ZombiesDto zombieDto = new ZombiesDto();

        zombieDto.setNom("New Zombie");

        zombieDto.setPoint_de_vie(100);

        zombieDto.setDegat_attaque(20);

        zombieDto.setId_map(1);

 

        Zombies zombie = new Zombies();

        zombie.setId(1);

        zombie.setNom("New Zombie");

        zombie.setPointDeVie(100);

 

        when(zombiesMapper.toEntity(zombieDto)).thenReturn(zombie);

        when(zombiesService.save(any(Zombies.class))).thenReturn(zombie);

        when(zombiesMapper.toDto(zombie)).thenReturn(zombieDto);

 

        // Act

        ResponseEntity<ZombiesDto> response = zombiesController.createZombie(zombieDto);

 

        // Assert

        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertNotNull(response.getBody());

    }

 

    @Test

    public void testCreateZombieInvalid() {

        // Arrange

        ZombiesDto zombieDto = new ZombiesDto();

        // Missing required fields

 

        // Act

        ResponseEntity<ZombiesDto> response = zombiesController.createZombie(zombieDto);

 

        // Assert

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

    }

 

    @Test

    public void testUpdateZombieSuccess() {

        // Arrange

        int id = 1;

        ZombiesDto zombieDto = new ZombiesDto();

        zombieDto.setId_zombie(id);

        zombieDto.setNom("Updated Zombie");

        zombieDto.setPoint_de_vie(150);

 

        Zombies existingZombie = new Zombies();

        existingZombie.setId(id);

        existingZombie.setNom("Old Zombie");

        existingZombie.setPointDeVie(100);

 

        Zombies updatedZombie = new Zombies();

        updatedZombie.setId(id);

        updatedZombie.setNom("Updated Zombie");

        updatedZombie.setPointDeVie(150);

 

        when(zombiesService.findById(id)).thenReturn(Optional.of(existingZombie));

        when(zombiesService.update(any(Zombies.class))).thenReturn(updatedZombie);

        when(zombiesMapper.toDto(updatedZombie)).thenReturn(zombieDto);

 

        // Act

        ResponseEntity<ZombiesDto> response = zombiesController.updateZombie(id, zombieDto);

 

        // Assert

        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertNotNull(response.getBody());

        assertEquals("Updated Zombie", response.getBody().getNom());

    }

 

    @Test

    public void testDeleteZombieSuccess() {

        // Arrange

        int id = 1;

        Zombies zombie = new Zombies();

        zombie.setId(id);

        when(zombiesService.findById(id)).thenReturn(Optional.of(zombie));

 

        // Act

        ResponseEntity<String> response = zombiesController.deleteZombie(id);

 

        // Assert

        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertEquals("Zombie supprimé avec succès!", response.getBody());

        verify(zombiesService).delete(id);

    }

 

    @Test

    public void testDeleteZombieNotFound() {

        // Arrange

        int id = 999;

        when(zombiesService.findById(id)).thenReturn(Optional.empty());

 

        // Act

        ResponseEntity<String> response = zombiesController.deleteZombie(id);

 

        // Assert

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        assertTrue(response.getBody().contains("Zombie non trouvé"));

        verify(zombiesService, never()).delete(anyInt());

    }

}




 

