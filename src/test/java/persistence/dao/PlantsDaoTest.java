/* 
        
        
package persistence.dao;

import com.epf.persistence.dao.PlantsDao;
import com.epf.persistence.model.Plants;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;




public class PlantsDaoTest {

    @Mock
    private PlantsDao plantsDao;

    private Plants plant;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        plant = new Plants();
        plant.setIdPlante(1);
        plant.setNom("Peashooter");
    }

    @Test
    public void testFindAll() {
        List<Plants> expectedPlants = Arrays.asList(plant);
        when(plantsDao.findAll()).thenReturn(expectedPlants);

        List<Plants> actualPlants = plantsDao.findAll();

        assertEquals(expectedPlants, actualPlants);
        verify(plantsDao).findAll();
    }

    @Test
    public void testFindById() {
        when(plantsDao.findById(1)).thenReturn(Optional.of(plant));

        Optional<Plants> foundPlant = plantsDao.findById(1);

        assertTrue(foundPlant.isPresent());
        assertEquals(plant, foundPlant.get());
        verify(plantsDao).findById(1);
    }

    @Test
    public void testSave() {
        when(plantsDao.save(plant)).thenReturn(plant);

        Plants savedPlant = plantsDao.save(plant);

        assertEquals(plant, savedPlant);
        verify(plantsDao).save(plant);
    }

    @Test
    public void testUpdate() {
        plantsDao.update(plant);
        verify(plantsDao).update(plant);
    }

    @Test
    public void testDelete() {
        plantsDao.delete(1);
        verify(plantsDao).delete(1);
    }

    @Test
    public void testFindByIdNotFound() {
        when(plantsDao.findById(999)).thenReturn(Optional.empty());

        Optional<Plants> foundPlant = plantsDao.findById(999);

        assertFalse(foundPlant.isPresent());
        verify(plantsDao).findById(999);
    }
}
*/
        
        