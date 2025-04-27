package API.controller;

import com.epf.API.controller.ZombiesController;
import com.epf.persistence.model.Zombies;
import com.epf.core.services.ZombiesServices;
import com.epf.API.mapper.ZombiesMapper;
import com.epf.API.dto.ZombiesDto;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
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

    private AutoCloseable closeable;

    @Before
    public void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @After
    public void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    public void testGetAllZombies() {
        Zombies zombie = new Zombies();
        ZombiesDto zombieDto = new ZombiesDto();

        when(zombiesService.findAll()).thenReturn(Arrays.asList(zombie));
        when(zombiesMapper.toDto(any(Zombies.class))).thenReturn(zombieDto);

        ResponseEntity<List<ZombiesDto>> response = zombiesController.getAllZombies();

        assertEquals(200, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        verify(zombiesService, times(1)).findAll();
    }

    @Test
    public void testGetZombieByIdFound() {
        Zombies zombie = new Zombies();
        ZombiesDto zombieDto = new ZombiesDto();

        when(zombiesService.findById(1)).thenReturn(Optional.of(zombie));
        when(zombiesMapper.toDto(zombie)).thenReturn(zombieDto);

        ResponseEntity<ZombiesDto> response = zombiesController.getZombieById(1);

        assertEquals(200, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    public void testGetZombieByIdNotFound() {
        when(zombiesService.findById(1)).thenReturn(Optional.empty());

        ResponseEntity<ZombiesDto> response = zombiesController.getZombieById(1);

        assertEquals(404, response.getStatusCode());
    }

    @Test
    public void testCreateZombieValid() {
        ZombiesDto zombieDto = new ZombiesDto();
        zombieDto.setNom("Zombie");
        zombieDto.setPoint_de_vie(100);
        zombieDto.setDegat_attaque(10);

        Zombies zombie = new Zombies();
        Zombies createdZombie = new Zombies();
        ZombiesDto createdZombieDto = new ZombiesDto();

        when(zombiesMapper.toEntity(zombieDto)).thenReturn(zombie);
        when(zombiesService.save(zombie)).thenReturn(createdZombie);
        when(zombiesMapper.toDto(createdZombie)).thenReturn(createdZombieDto);

        ResponseEntity<ZombiesDto> response = zombiesController.createZombie(zombieDto);

        assertEquals(200, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    public void testCreateZombieInvalid() {
        ZombiesDto zombieDto = new ZombiesDto(); // Invalid, fields missing

        ResponseEntity<ZombiesDto> response = zombiesController.createZombie(zombieDto);

        assertEquals(400, response.getStatusCode());
    }

    @Test
    public void testUpdateZombieFound() {
        ZombiesDto zombieDto = new ZombiesDto();
        zombieDto.setNom("Conehead Zombie");

        Zombies existingZombie = new Zombies();
        existingZombie.setIdZombie(1);
        existingZombie.setNom("Conehead Zombie");

        Zombies updatedZombie = new Zombies();
        ZombiesDto updatedZombieDto = new ZombiesDto();

        when(zombiesService.findById(1)).thenReturn(Optional.of(existingZombie));
        when(zombiesService.update(any(Zombies.class))).thenReturn(updatedZombie);
        when(zombiesMapper.toDto(updatedZombie)).thenReturn(updatedZombieDto);

        ResponseEntity<ZombiesDto> response = zombiesController.updateZombie(1, zombieDto);

        assertEquals(200, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    public void testUpdateZombieNotFound() {
        ZombiesDto zombieDto = new ZombiesDto();
        zombieDto.setNom("Buckethead Zombie");

        when(zombiesService.findById(1)).thenReturn(Optional.empty());

        ResponseEntity<ZombiesDto> response = zombiesController.updateZombie(1, zombieDto);

        assertEquals(404, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void testDeleteZombieFound() {
        Zombies zombie = new Zombies();
        when(zombiesService.findById(1)).thenReturn(Optional.of(zombie));

        ResponseEntity<String> response = zombiesController.deleteZombie(1);

        assertEquals(200, response.getStatusCode());
        assertEquals("Zombie supprimé avec succès!", response.getBody());
        verify(zombiesService, times(1)).delete(1);
    }

    @Test
    public void testDeleteZombieNotFound() {
        when(zombiesService.findById(1)).thenReturn(Optional.empty());

        ResponseEntity<String> response = zombiesController.deleteZombie(1);

        assertEquals(404, response.getStatusCode());
        assertTrue(response.getBody().contains("Zombie non trouvé"));
    }
}

