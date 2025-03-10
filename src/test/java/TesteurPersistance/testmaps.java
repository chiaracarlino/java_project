/*package TesteurPersistance;

import com.epf.persistance.maps;
import com.epf.persistance.mapsrepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MapServiceTest {

    @Mock
    private mapsrepository mapRepository;

    @InjectMocks
    private MapService mapService;

    @Test
    void whenAddMap_thenReturnMap() {
        maps map = new maps(1L, "Front Yard", 5, 3);
        when(mapRepository.save(any(maps.class))).thenReturn(map);

        maps createdMap = mapService.addmaps(map);

        assertEquals("Front Yard", createdMap.getName());
        assertEquals(5, createdMap.getWidth());
        verify(mapRepository, times(1)).save(map);
    }

    @Test
    void whenGetAllMaps_thenReturnMapList() {
        List<maps> maps = Arrays.asList(
                new maps(1L, "Backyard", 6, 4),
                new maps(2L, "Roof", 7, 5)
        );
        when(mapRepository.findAll()).thenReturn(maps);

        List<maps> allMaps = mapService.getAllMaps();

        assertEquals(2, allMaps.size());
        verify(mapRepository, times(1)).findAll();
    }
} */
