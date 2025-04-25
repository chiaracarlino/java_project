package core.services;

import com.epf.core.PlantsServicesImpl;
import com.epf.persistence.dao.PlantsDao;
import com.epf.persistence.model.Plants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PlantsServicesTest {

    private PlantsServicesImpl plantsService;

    @Mock
    private PlantsDao plantsDao;

    private Plants testPlant;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        plantsService = new PlantsServicesImpl(plantsDao);
        
        testPlant = new Plants();
        testPlant.setIdPlante(1);
        testPlant.setNom("Test Plant");
        testPlant.setPointDeVie(100);
        testPlant.setAttaqueParSeconde(new BigDecimal("1.5"));
        testPlant.setDegatAttaque(25);
        testPlant.setCout(100);
        testPlant.setSoleilParSeconde(new BigDecimal("0.5"));
        testPlant.setEffet("normal");
        testPlant.setCheminImage("test.png");
    }

    @Test
    void findAll_ShouldReturnListOfPlants() {
        // Arrange
        List<Plants> expectedPlants = Arrays.asList(testPlant);
        when(plantsDao.findAll()).thenReturn(expectedPlants);

        // Act
        List<Plants> actualPlants = plantsService.findAll();

        // Assert
        assertEquals(expectedPlants, actualPlants);
        verify(plantsDao).findAll();
    }

    @Test
    void findById_WithExistingId_ShouldReturnPlant() {
        // Arrange
        when(plantsDao.findById(1)).thenReturn(Optional.of(testPlant));

        // Act
        Optional<Plants> result = plantsService.findById(1);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(testPlant, result.get());
        verify(plantsDao).findById(1);
    }

    @Test
    void findById_WithNonExistingId_ShouldReturnEmpty() {
        // Arrange
        when(plantsDao.findById(999)).thenReturn(Optional.empty());

        // Act
        Optional<Plants> result = plantsService.findById(999);

        // Assert
        assertTrue(result.isEmpty());
        verify(plantsDao).findById(999);
    }

    @Test
    void save_ShouldReturnSavedPlant() {
        // Arrange
        when(plantsDao.save(testPlant)).thenReturn(testPlant);

        // Act
        Plants savedPlant = plantsService.save(testPlant);

        // Assert
        assertEquals(testPlant, savedPlant);
        verify(plantsDao).save(testPlant);
    }

    @Test
    void update_ShouldCallDaoUpdate() {
        // Arrange
        doNothing().when(plantsDao).update(testPlant);

        // Act
        plantsService.update(testPlant);

        // Assert
        verify(plantsDao).update(testPlant);
    }

    @Test
    void delete_ShouldCallDaoDelete() {
        // Arrange
        doNothing().when(plantsDao).delete(1);

        // Act
        plantsService.delete(1);

        // Assert
        verify(plantsDao).delete(1);
    }
}
