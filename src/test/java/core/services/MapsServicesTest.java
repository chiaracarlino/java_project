package core.services;

 

import com.epf.core.MapsServicesImpl;

import com.epf.persistence.dao.MapsDao;

import com.epf.persistence.model.Maps;

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

 

public class MapsServicesTest {

 

    @Mock

    private MapsDao mapsDao;

 

    @InjectMocks

    private MapsServicesImpl mapsService;

 

    @Before

    public void setUp() {

        MockitoAnnotations.openMocks(this);

    }

 

    @Test

    public void testFindById() {

        // Arrange

        Maps map = new Maps();

        map.setIdMap(1);

        map.setLigne(5);

        map.setColonne(5);

        when(mapsDao.findById(1)).thenReturn(Optional.of(map));

 

        // Act

        Optional<Maps> result = mapsService.findById(1);

 

        // Assert

        assertTrue(result.isPresent());

        assertEquals(Integer.valueOf(1), result.get().getIdMap());

        verify(mapsDao).findById(1);

    }

 

    @Test

    public void testFindAll() {

        // Arrange

        Maps map1 = new Maps();

        map1.setIdMap(1);

        Maps map2 = new Maps();

        map2.setIdMap(2);

        when(mapsDao.findAll()).thenReturn(Arrays.asList(map1, map2));

 

        // Act

        List<Maps> result = mapsService.findAll();

 

        // Assert

        assertEquals(2, result.size());

        verify(mapsDao).findAll();

    }

 

    @Test

    public void testSave() {

        // Arrange

        Maps map = new Maps();

        map.setLigne(5);

        map.setColonne(5);

        when(mapsDao.save(any(Maps.class))).thenReturn(map);

 

        // Act

        Maps result = mapsService.save(map);

 

        // Assert

        assertNotNull(result);

        verify(mapsDao).save(map);

    }

 

    @Test

    public void testUpdateSuccess() {

        // Arrange

        Maps map = new Maps();

        map.setIdMap(1);

        map.setLigne(5);

        map.setColonne(5);

       

        // On retourne la map pour le findById initial et après update

        when(mapsDao.findById(1)).thenReturn(Optional.of(map));

        doNothing().when(mapsDao).update(any(Maps.class));  // Changed this line

        when(mapsDao.findById(1)).thenReturn(Optional.of(map));  // For the second findById after update

 

        // Act

        Maps result = mapsService.update(map);

 

        // Assert

        assertNotNull(result);

        verify(mapsDao).update(map);

    }

 

    @Test(expected = IllegalArgumentException.class)

    public void testUpdateNullMap() {

        // Act & Assert (exception expected)

        mapsService.update(null);

    }

 

    @Test(expected = IllegalArgumentException.class)

    public void testUpdateNonExistingMap() {

        // Arrange

        Maps map = new Maps();

        map.setIdMap(999);

        when(mapsDao.findById(999)).thenReturn(Optional.empty());

 

        // Act & Assert (exception expected)

        mapsService.update(map);

    }

 

    @Test

    public void testDelete() {

        // Arrange

        doNothing().when(mapsDao).delete(anyInt());

 

        // Act

        mapsService.delete(1);

 

        // Assert

        verify(mapsDao).delete(1);

    }

}
