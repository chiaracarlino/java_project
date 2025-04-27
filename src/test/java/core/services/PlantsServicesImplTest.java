package core.services;

 

import com.epf.core.PlantsServicesImpl;

import com.epf.persistence.model.Plants;

import com.epf.persistence.repository.PlantsRepository;

import org.junit.Before;

import org.junit.Test;

import org.mockito.InjectMocks;

import org.mockito.Mock;

import org.mockito.MockitoAnnotations;

 

import java.util.Arrays;

import java.util.List;

import java.util.Optional;

 

import static org.mockito.Mockito.*;

import static org.junit.Assert.*;

 

public class PlantsServicesImplTest {

 

    @Mock

    private PlantsRepository plantsRepository;

 

    @InjectMocks

    private PlantsServicesImpl plantsService;

 

    @Before

    public void setUp() {

        MockitoAnnotations.openMocks(this);

    }

 

    @Test

    public void testFindAll() {

        // Arrange

        Plants plant1 = createValidPlant(1);

        Plants plant2 = createValidPlant(2);

        when(plantsRepository.findAll()).thenReturn(Arrays.asList(plant1, plant2));

 

        // Act

        List<Plants> result = plantsService.findAll();

 

        // Assert

        assertEquals(2, result.size());

        verify(plantsRepository).findAll();

    }

 

    @Test

    public void testFindById() {

        // Arrange

        Plants plant = createValidPlant(1);

        when(plantsRepository.findById(1)).thenReturn(Optional.of(plant));

 

        // Act

        Optional<Plants> result = plantsService.findById(1);

 

        // Assert

        assertTrue(result.isPresent());

        assertEquals(1, result.get().getIdPlante());

    }

 

    @Test

    public void testSaveValid() {

        // Arrange

        Plants plant = createValidPlant(1);

        when(plantsRepository.save(any(Plants.class))).thenReturn(plant);

 

        // Act

        Plants result = plantsService.save(plant);

 

        // Assert

        assertNotNull(result);

        verify(plantsRepository).save(plant);

    }

 

    @Test(expected = IllegalArgumentException.class)

    public void testSaveInvalidName() {

        // Arrange

        Plants plant = createValidPlant(1);

        plant.setNom("");

 

        // Act

        plantsService.save(plant);

    }

 

    @Test(expected = IllegalArgumentException.class)

    public void testSaveInvalidPointDeVie() {

        // Arrange

        Plants plant = createValidPlant(1);

        plant.setPointDeVie(0);

 

        // Act

        plantsService.save(plant);

    }

 

    @Test

    public void testUpdateValid() {

        // Arrange

        Plants plant = createValidPlant(1);

        when(plantsRepository.findById(1)).thenReturn(Optional.of(plant));

        doNothing().when(plantsRepository).update(any(Plants.class));

        when(plantsRepository.findById(1)).thenReturn(Optional.of(plant));

 

        // Act

        Plants result = plantsService.update(plant);

 

        // Assert

        assertNotNull(result);

        verify(plantsRepository).update(plant);

    }

 

    @Test(expected = IllegalArgumentException.class)

    public void testUpdateNonExistent() {

        // Arrange

        Plants plant = createValidPlant(999);

        when(plantsRepository.findById(999)).thenReturn(Optional.empty());

 

        // Act

        plantsService.update(plant);

    }

 

    @Test

    public void testDeleteExisting() {

        // Arrange

        Plants plant = createValidPlant(1);

        when(plantsRepository.findById(1)).thenReturn(Optional.of(plant));

        doNothing().when(plantsRepository).delete(1);

 

        // Act

        plantsService.delete(1);

 

        // Assert

        verify(plantsRepository).delete(1);

    }

 

    @Test(expected = IllegalArgumentException.class)

    public void testDeleteNonExistent() {

        // Arrange

        when(plantsRepository.findById(999)).thenReturn(Optional.empty());

 

        // Act

        plantsService.delete(999);

    }

 

    private Plants createValidPlant(int id) {

        Plants plant = new Plants();

        plant.setIdPlante(id);

        plant.setNom("Test Plant");

        plant.setPointDeVie(100);

        plant.setDegatAttaque(20);

        plant.setCout(50);

        plant.setAttaqueParSeconde(1.0);

        plant.setSoleilParSeconde(0.0);

        plant.setEffet("Test effect");

        plant.setCheminImage("test.png");

        return plant;

    }

}