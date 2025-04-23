/*package persistence.dao;
import com.epf.persistence.dao.MapsDao;
import com.epf.persistence.model.Maps;
import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class MapsDaoTest {

    private MapsDao mapsDao;

    @Before
    public void setUp() {
        mapsDao = mock(MapsDao.class);
    }

    @Test
    public void testCreate() {
        Maps map = new Maps(1L, 3, 4, "dao.png");
        when(mapsDao.create(map)).thenReturn(map);
        Maps result = mapsDao.create(map);
        assertEquals(Long.valueOf(1), result.getIdMap());
    }

    @Test
    public void testFindById() {
        Maps map = new Maps(2L, 5, 6, "find.png");
        when(mapsDao.findById(2L)).thenReturn(Optional.of(map));
        Optional<Maps> result = mapsDao.findById(2L);
        assertTrue(result.isPresent());
    }

    @Test
    public void testFindAll() {
        List<Maps> maps = Arrays.asList(
                new Maps(1L, 1, 1, "a.png"),
                new Maps(2L, 2, 2, "b.png")
        );
        when(mapsDao.findAll()).thenReturn(maps);
        List<Maps> result = mapsDao.findAll();
        assertEquals(2, result.size());
    }

    @Test
    public void testUpdate() {
        Maps map = new Maps(3L, 7, 8, "updated.png");
        doNothing().when(mapsDao).update(map);
        mapsDao.update(map);
        verify(mapsDao).update(map);
    }

    @Test
    public void testDelete() {
        doNothing().when(mapsDao).delete(4L);
        mapsDao.delete(4L);
        verify(mapsDao).delete(4L);
    }
} */

