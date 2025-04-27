package core.services;

 

import com.epf.core.ZombiesServicesImpl;

import com.epf.persistence.dao.ZombiesDao;

import com.epf.persistence.dao.MapsDao;

import com.epf.persistence.model.Maps;

import com.epf.persistence.model.Zombies;

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

 

public class ZombiesServicesTest {

 

    @Mock

    private ZombiesDao zombiesDao;

 

    @Mock

    private MapsDao mapsDao;

 

    @InjectMocks

    private ZombiesServicesImpl zombiesService;

 

    @Before

    public void setUp() {

        MockitoAnnotations.openMocks(this);

    }

 

    @Test

    public void testFindAll() {

        // Arrange

        Zombies zombie1 = createValidZombie(1);

        Zombies zombie2 = createValidZombie(2);

        when(zombiesDao.findAll()).thenReturn(Arrays.asList(zombie1, zombie2));

 

        // Act

        List<Zombies> result = zombiesService.findAll();

 

        // Assert

        assertEquals(2, result.size());

        verify(zombiesDao).findAll();

    }

 

    @Test

    public void testFindById() {

        // Arrange

        Zombies zombie = createValidZombie(1);

        when(zombiesDao.findById(1)).thenReturn(Optional.of(zombie));

 

        // Act

        Optional<Zombies> result = zombiesService.findById(1);

 

        // Assert

        assertTrue(result.isPresent());

        assertEquals(Integer.valueOf(1), result.get().getId());

    }

 

    @Test

    public void testFindByMapId() {

        // Arrange

        int mapId = 1;

        Maps map = new Maps();

        map.setIdMap(mapId);

        Zombies zombie = createValidZombie(1);

       

        when(mapsDao.findById(mapId)).thenReturn(Optional.of(map));

        when(zombiesDao.findByMapId(mapId)).thenReturn(Arrays.asList(zombie));

 

        // Act

        List<Zombies> result = zombiesService.findByMapId(mapId);

 

        // Assert

        assertEquals(1, result.size());

        verify(mapsDao).findById(mapId);

        verify(zombiesDao).findByMapId(mapId);

    }

 

    @Test

    public void testSaveValid() {

        // Arrange

        Zombies zombie = createValidZombie(1);

        Maps map = new Maps();

        map.setIdMap(1);

       

        when(mapsDao.findById(1)).thenReturn(Optional.of(map));

        when(zombiesDao.save(any(Zombies.class))).thenReturn(zombie);

 

        // Act

        Zombies result = zombiesService.save(zombie);

 

        // Assert

        assertNotNull(result);

        verify(zombiesDao).save(zombie);

    }

 

    @Test(expected = IllegalArgumentException.class)

    public void testSaveInvalidZombie() {

        // Arrange

        Zombies zombie = new Zombies();

        zombie.setId(1);

        // Missing required fields

 

        // Act

        zombiesService.save(zombie);

    }

 

    @Test

    public void testUpdateValid() {

        // Arrange

        Zombies zombie = createValidZombie(1);

        Maps map = new Maps();

        map.setIdMap(1);

       

        when(zombiesDao.findById(1)).thenReturn(Optional.of(zombie));

        when(mapsDao.findById(1)).thenReturn(Optional.of(map));

        when(zombiesDao.update(any(Zombies.class))).thenReturn(zombie);  // Changed this line

 

        // Act

        Zombies result = zombiesService.update(zombie);

 

        // Assert

        assertNotNull(result);

        verify(zombiesDao).update(zombie);

    }

 

    @Test

    public void testDelete() {

        // Arrange

        Zombies zombie = createValidZombie(1);

        when(zombiesDao.findById(1)).thenReturn(Optional.of(zombie));

        doNothing().when(zombiesDao).delete(1);

 

        // Act

        zombiesService.delete(1);

 

        // Assert

        verify(zombiesDao).delete(1);

    }

 

    private Zombies createValidZombie(int id) {

        Zombies zombie = new Zombies();

        zombie.setId(id);

        zombie.setNom("Test Zombie");

        zombie.setPointDeVie(100);

        zombie.setDegatAttaque(20);

        zombie.setIdMap(1);

        zombie.setAttaqueParSeconde(1.0);

        zombie.setVitesseDeDeplacement(0.5);

        zombie.setCheminImage("test.png");

        return zombie;

    }

}