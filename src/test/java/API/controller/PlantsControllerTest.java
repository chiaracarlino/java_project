package API.controller;

import com.epf.API.controller.PlantsController;
import com.epf.core.services.PlantsServices;
import com.epf.persistence.model.Plants;
import com.epf.API.dto.PlantsDto;
import com.epf.API.mapper.PlantsMapper;
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

public class PlantsControllerTest {

    @Mock
    private PlantsServices plantsService;

    @Mock
    private PlantsMapper plantsMapper;

    @InjectMocks
    private PlantsController plantsController;

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
    public void testGetAllPlants() {
        Plants plant = new Plants();
        PlantsDto plantDto = new PlantsDto();

        when(plantsService.findAll()).thenReturn(Arrays.asList(plant));
        when(plantsMapper.toDTO(any(Plants.class))).thenReturn(plantDto);

        ResponseEntity<List<PlantsDto>> response = plantsController.getAllPlants();

        assertEquals(200, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        verify(plantsService, times(1)).findAll();
    }

    @Test
    public void testGetPlantByIdFound() {
        Plants plant = new Plants();
        PlantsDto plantDto = new PlantsDto();

        when(plantsService.findById(1)).thenReturn(Optional.of(plant));
        when(plantsMapper.toDTO(plant)).thenReturn(plantDto);

        ResponseEntity<PlantsDto> response = plantsController.getPlantById(1);

        assertEquals(200, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    public void testGetPlantByIdNotFound() {
        when(plantsService.findById(1)).thenReturn(Optional.empty());

        ResponseEntity<PlantsDto> response = plantsController.getPlantById(1);

        assertEquals(404, response.getStatusCode());
    }

    @Test
    public void testCreatePlant() {
        PlantsDto plantDto = new PlantsDto();
        Plants plant = new Plants();
        Plants savedPlant = new Plants();
        PlantsDto savedPlantDto = new PlantsDto();

        when(plantsMapper.toEntity(plantDto)).thenReturn(plant);
        when(plantsService.save(any(Plants.class))).thenReturn(savedPlant);
        when(plantsMapper.toDTO(savedPlant)).thenReturn(savedPlantDto);

        ResponseEntity<PlantsDto> response = plantsController.createPlant(plantDto);

        assertEquals(200, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    public void testUpdatePlantFound() {
        PlantsDto plantDto = new PlantsDto();
        plantDto.setNom("Sunflower");

        Plants existingPlant = new Plants();
        existingPlant.setIdPlante(1);
        existingPlant.setNom("Sunflower");

        Plants updatedPlant = new Plants();
        PlantsDto updatedPlantDto = new PlantsDto();

        when(plantsService.findById(1)).thenReturn(Optional.of(existingPlant));
        when(plantsService.update(any(Plants.class))).thenReturn(updatedPlant);
        when(plantsMapper.toDTO(updatedPlant)).thenReturn(updatedPlantDto);

        ResponseEntity<PlantsDto> response = plantsController.updatePlant(1, plantDto);

        assertEquals(200, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    public void testUpdatePlantNotFound() {
        PlantsDto plantDto = new PlantsDto();
        plantDto.setNom("Peashooter");

        when(plantsService.findById(1)).thenReturn(Optional.empty());
        when(plantsService.findAll()).thenReturn(Arrays.asList());

        ResponseEntity<PlantsDto> response = plantsController.updatePlant(1, plantDto);

        assertEquals(404, response.getStatusCode());
    }

    @Test
    public void testDeletePlantFound() {
        Plants plant = new Plants();
        when(plantsService.findById(1)).thenReturn(Optional.of(plant));

        ResponseEntity<Void> response = plantsController.deletePlant(1);

        assertEquals(204, response.getStatusCode());
        verify(plantsService, times(1)).delete(1);
    }

    @Test
    public void testDeletePlantNotFound() {
        when(plantsService.findById(1)).thenReturn(Optional.empty());

        ResponseEntity<Void> response = plantsController.deletePlant(1);

        assertEquals(404, response.getStatusCode());
        verify(plantsService, never()).delete(anyInt());
    }
}

