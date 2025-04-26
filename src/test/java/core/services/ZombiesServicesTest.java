/*package core.services;

import com.epf.core.ZombiesServicesImpl;
import com.epf.persistence.dao.ZombiesDao;
import com.epf.persistence.model.Zombies;
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

class ZombiesServicesTest {

    private ZombiesServicesImpl zombiesService;

    @Mock
    private ZombiesDao zombiesDao;

    private Zombies testZombie;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        zombiesService = new ZombiesServicesImpl(zombiesDao);
        
        testZombie = new Zombies();
        testZombie.setIdZombie(1);
        testZombie.setNom("Test Zombie");
        testZombie.setPointDeVie(100);
        testZombie.setAttaqueParSeconde(new BigDecimal("1.5"));
        testZombie.setDegatAttaque(25);
        testZombie.setVitesseDeDeplacement(new BigDecimal("0.5"));
        testZombie.setCheminImage("zombie.png");
        testZombie.setIdMap(1);
    }

    @Test
    void findAll_ShouldReturnListOfZombies() {
        // Arrange
        List<Zombies> expectedZombies = Arrays.asList(testZombie);
        when(zombiesDao.findAll()).thenReturn(expectedZombies);

        // Act
        List<Zombies> actualZombies = zombiesService.findAll();

        // Assert
        assertEquals(expectedZombies, actualZombies);
        verify(zombiesDao).findAll();
    }

    @Test
    void findById_WithExistingId_ShouldReturnZombie() {
        // Arrange
        when(zombiesDao.findById(1)).thenReturn(Optional.of(testZombie));

        // Act
        Optional<Zombies> result = zombiesService.findById(1);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(testZombie, result.get());
        verify(zombiesDao).findById(1);
    }

    @Test
    void findById_WithNonExistingId_ShouldReturnEmpty() {
        // Arrange
        when(zombiesDao.findById(999)).thenReturn(Optional.empty());

        // Act
        Optional<Zombies> result = zombiesService.findById(999);

        // Assert
        assertTrue(result.isEmpty());
        verify(zombiesDao).findById(999);
    }

    @Test
    void save_ShouldReturnSavedZombie() {
        // Arrange
        when(zombiesDao.save(testZombie)).thenReturn(testZombie);

        // Act
        Zombies savedZombie = zombiesService.save(testZombie);

        // Assert
        assertEquals(testZombie, savedZombie);
        verify(zombiesDao).save(testZombie);
    }

    @Test
    void update_ShouldCallDaoUpdate() {
        // Arrange
        doNothing().when(zombiesDao).update(testZombie);

        // Act
        zombiesService.update(testZombie);

        // Assert
        verify(zombiesDao).update(testZombie);
    }

    @Test
    void delete_ShouldCallDaoDelete() {
        // Arrange
        doNothing().when(zombiesDao).delete(1);

        // Act
        zombiesService.delete(1);

        // Assert
        verify(zombiesDao).delete(1);
    }
} */
