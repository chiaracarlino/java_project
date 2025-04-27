package API.controller;

import com.epf.API.dto.MapsDto;
import com.epf.core.services.MapsServices;
import com.epf.core.services.ZombiesServices;
import com.epf.API.controller.MapsController;
import com.epf.persistence.model.Maps;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class MapsControllerTest {

    @Mock
    private MapsServices mapsService;

    @Mock
    private ZombiesServices zombiesService;

    @InjectMocks
    private MapsController mapsController;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllMaps() {
        Maps map = new Maps();
        map.setIdMap(1);
        when(mapsService.findAll()).thenReturn(Arrays.asList(map));

        ResponseEntity<List<MapsDto>> response = mapsController.getAllMaps();

        assertEquals(200, response.getStatusCode());
        assertFalse(response.getBody().isEmpty());
    }

    @Test
    public void testGetMapById_found() {
        Maps map = new Maps();
        map.setIdMap(1);
        when(mapsService.findById(1)).thenReturn(Optional.of(map));

        ResponseEntity<MapsDto> response = mapsController.getMapById(1);

        assertEquals(200, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    public void testGetMapById_notFound() {
        when(mapsService.findById(1)).thenReturn(Optional.empty());

        ResponseEntity<MapsDto> response = mapsController.getMapById(1);

        assertEquals(404, response.getStatusCode());
    }

    @Test
    public void testCreateMap_success() {
        MapsDto dto = new MapsDto();
        dto.setLigne(5);
        dto.setColonne(5);

        Maps map = new Maps();
        map.setIdMap(1);

        when(mapsService.save(any(Maps.class))).thenReturn(map);

        ResponseEntity<MapsDto> response = mapsController.createMap(dto);

        assertEquals(200, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    public void testUpdateMap_existingMap() {
        int id = 1;
        MapsDto dto = new MapsDto();
        dto.setLigne(10);
        dto.setColonne(10);

        Maps existing = new Maps();
        existing.setIdMap(id);
        existing.setLigne(5);
        existing.setColonne(5);

        Maps updated = new Maps();
        updated.setIdMap(id);
        updated.setLigne(10);
        updated.setColonne(10);

        when(mapsService.findById(id)).thenReturn(Optional.of(existing));
        when(mapsService.update(any(Maps.class))).thenReturn(updated);

        ResponseEntity<MapsDto> response = mapsController.updateMap(id, dto);

        assertEquals(200, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    public void testDeleteMap_found() {
        int id = 1;
        Maps map = new Maps();
        map.setIdMap(id);

        when(mapsService.findById(id)).thenReturn(Optional.of(map));
        when(zombiesService.findByMapId(id)).thenReturn(Arrays.asList());

        ResponseEntity<String> response = mapsController.deleteMap(id);

        assertEquals(200, response.getStatusCode());
        assertEquals("Map et zombies associés supprimés avec succès!", response.getBody());
    }

    @Test
    public void testDeleteMap_notFound() {
        int id = 1;
        when(mapsService.findById(id)).thenReturn(Optional.empty());

        ResponseEntity<String> response = mapsController.deleteMap(id);

        assertEquals(404, response.getStatusCode());
        assertTrue(response.getBody().contains("Map non trouvée"));
    }
}


