/*package core.services;

import com.epf.persistence.dao.MapsDao;
import com.epf.persistence.model.Maps;
import com.epf.core.services.MapsServices;
import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class MapsServiceTest {

    private MapsDao mapsDao;
    private MapsServices mapsService;

    @Before
    public void setUp() {
        mapsDao = mock(MapsDao.class);
        mapsService = new MapsServices(mapsDao);
    }

    @Test
    public void testCreateMap() {
        Maps map = new Maps(1L, 2, 3, "path.png");
        when(mapsDao.create(map)).thenReturn(map);
        Maps result = mapsService.createMap(map);
        assertEquals(map, result);
    }

    @Test
    public void testGetMapById() {
        Maps map = new Maps(2L, 4, 5, "image.png");
        when(mapsDao.findById(2L)).thenReturn(Optional.of(map));
        Optional<Maps> result = mapsService.getMapById(2L);
        assertTrue(result.isPresent());
        assertEquals("image.png", result.get().getCheminImage());
    }

    @Test
    public void testGetAllMaps() {
        List<Maps> maps = Arrays.asList(
                new Maps(1L, 1, 1, "a.png"),
                new Maps(2L, 2, 2, "b.png")
        );
        when(mapsDao.findAll()).thenReturn(maps);
        List<Maps> result = mapsService.getAllMaps();
        assertEquals(2, result.size());
    }

    @Test
    public void testUpdateMap() {
        Maps map = new Maps(3L, 3, 3, "update.png");
        mapsService.updateMap(map);
        verify(mapsDao).update(map);
    }

    @Test
    public void testDeleteMap() {
        mapsService.deleteMap(4L);
        verify(mapsDao).delete(4L);
    }
}
*/