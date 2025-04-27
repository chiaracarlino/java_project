package API.controller;

 

import com.epf.API.controller.PlantsController;

import com.epf.core.services.PlantsServices;

import com.epf.persistence.model.Plants;

import com.epf.API.dto.PlantsDto;

import com.epf.API.mapper.PlantsMapper;

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

 

public class PlantsControllerTest {

 

    @Mock

    private PlantsServices plantsService;

 

    @Mock

    private PlantsMapper plantsMapper;

 

    @InjectMocks

    private PlantsController plantsController;

 

    @Before

    public void setUp() {

        MockitoAnnotations.openMocks(this);

    }

 

    @Test

    public void testGetAllPlants() {

        // Arrange

        Plants plant = new Plants();

        plant.setIdPlante(1);

        plant.setNom("Peashooter");

        plant.setPointDeVie(100);

        plant.setDegatAttaque(20);

 

        PlantsDto plantDto = new PlantsDto();

        plantDto.setId_plante(1);

        plantDto.setNom("Peashooter");

        plantDto.setPoint_de_vie(100);

        plantDto.setDegat_attaque(20);

 

        when(plantsService.findAll()).thenReturn(Arrays.asList(plant));

        when(plantsMapper.toDTO(any(Plants.class))).thenReturn(plantDto);

 

        // Act

        ResponseEntity<List<PlantsDto>> response = plantsController.getAllPlants();

 

        // Assert

        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertNotNull(response.getBody());

        assertEquals(1, response.getBody().size());

    }

 

    @Test

    public void testGetPlantByIdFound() {

        // Arrange

        Integer id = 1;

        Plants plant = new Plants();

        plant.setIdPlante(id);

        plant.setNom("Sunflower");

 

        PlantsDto plantDto = new PlantsDto();

        plantDto.setId_plante(id);

        plantDto.setNom("Sunflower");

 

        when(plantsService.findById(id)).thenReturn(Optional.of(plant));

        when(plantsMapper.toDTO(plant)).thenReturn(plantDto);

 

        // Act

        ResponseEntity<PlantsDto> response = plantsController.getPlantById(id);

 

        // Assert

        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertNotNull(response.getBody());

        assertEquals("Sunflower", response.getBody().getNom());

    }

 

    @Test

    public void testCreatePlantSuccess() {

        // Arrange

        PlantsDto plantDto = new PlantsDto();

        plantDto.setNom("New Plant");

        plantDto.setPoint_de_vie(100);

        plantDto.setDegat_attaque(20);

        plantDto.setCout(50);

 

        Plants plant = new Plants();

        plant.setIdPlante(1);

        plant.setNom("New Plant");

        plant.setPointDeVie(100);

 

        when(plantsMapper.toEntity(plantDto)).thenReturn(plant);

        when(plantsService.save(any(Plants.class))).thenReturn(plant);

        when(plantsMapper.toDTO(plant)).thenReturn(plantDto);

 

        // Act

        ResponseEntity<PlantsDto> response = plantsController.createPlant(plantDto);

 

        // Assert

        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertNotNull(response.getBody());

    }

 

    @Test

    public void testUpdatePlantSuccess() {

        // Arrange

        Integer id = 1;

        PlantsDto plantDto = new PlantsDto();

        plantDto.setId_plante(id);

        plantDto.setNom("Updated Plant");

        plantDto.setPoint_de_vie(150);

 

        Plants existingPlant = new Plants();

        existingPlant.setIdPlante(id);

        existingPlant.setNom("Old Plant");

        existingPlant.setPointDeVie(100);

 

        Plants updatedPlant = new Plants();

        updatedPlant.setIdPlante(id);

        updatedPlant.setNom("Updated Plant");

        updatedPlant.setPointDeVie(150);

 

        when(plantsService.findById(id)).thenReturn(Optional.of(existingPlant));

        when(plantsService.update(any(Plants.class))).thenReturn(updatedPlant);

        when(plantsMapper.toDTO(updatedPlant)).thenReturn(plantDto);

 

        // Act

        ResponseEntity<PlantsDto> response = plantsController.updatePlant(id, plantDto);

 

        // Assert

        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertNotNull(response.getBody());

        assertEquals("Updated Plant", response.getBody().getNom());

    }

 

    @Test

    public void testDeletePlantSuccess() {

        // Arrange

        Integer id = 1;

        Plants plant = new Plants();

        plant.setIdPlante(id);

        when(plantsService.findById(id)).thenReturn(Optional.of(plant));

 

        // Act

        ResponseEntity<Void> response = plantsController.deletePlant(id);

 

        // Assert

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        verify(plantsService).delete(id);

    }

 

    @Test

    public void testDeletePlantNotFound() {

        // Arrange

        Integer id = 999;

        when(plantsService.findById(id)).thenReturn(Optional.empty());

 

        // Act

        ResponseEntity<Void> response = plantsController.deletePlant(id);

 

        // Assert

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        verify(plantsService, never()).delete(anyInt());

    }

}

