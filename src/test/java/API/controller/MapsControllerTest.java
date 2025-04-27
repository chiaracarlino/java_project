package API.controller;


import com.epf.API.controller.MapsController;
import com.epf.core.services.MapsServices;
import com.epf.core.services.ZombiesServices;
import com.epf.persistence.model.Maps;
import com.epf.persistence.model.Zombies;
import com.epf.API.dto.MapsDto;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

 

import static org.mockito.Mockito.*;

import static org.junit.Assert.*;

 

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

        // Arrange

        Maps map = new Maps();

        map.setIdMap(1);

        map.setLigne(5);

        map.setColonne(5);

        map.setCheminImage("path/to/image.png");

 

        when(mapsService.findAll()).thenReturn(Arrays.asList(map));

 

        // Act

        ResponseEntity<List<MapsDto>> response = mapsController.getAllMaps();

 

        // Assert

        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertNotNull(response.getBody());

        assertEquals(1, response.getBody().size());

    }

 

    @Test

    public void testGetMapByIdFound() {

        // Arrange

        int id = 1;

        Maps map = new Maps();

        map.setIdMap(id);

        map.setLigne(5);

        map.setColonne(5);

 

        when(mapsService.findById(id)).thenReturn(Optional.of(map));

 

        // Act

        ResponseEntity<MapsDto> response = mapsController.getMapById(id);

 

        // Assert

        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertNotNull(response.getBody());

    }

 

    @Test

    public void testGetMapByIdNotFound() {

        // Arrange

        int id = 999;

        when(mapsService.findById(id)).thenReturn(Optional.empty());

 

        // Act

        ResponseEntity<MapsDto> response = mapsController.getMapById(id);

 

        // Assert

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

    }

 

    @Test

    public void testCreateMapSuccess() {

        // Arrange

        MapsDto mapDto = new MapsDto();

        mapDto.setLigne(5);

        mapDto.setColonne(5);

        mapDto.setChemin_image("test.png");

 

        Maps map = new Maps();

        map.setIdMap(1);

        map.setLigne(5);

        map.setColonne(5);

        map.setCheminImage("test.png");

 

        when(mapsService.save(any(Maps.class))).thenReturn(map);

 

        // Act

        ResponseEntity<MapsDto> response = mapsController.createMap(mapDto);

 

        // Assert

        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertNotNull(response.getBody());

    }

 

    @Test

    public void testDeleteMapSuccess() {

        // Arrange

        int id = 1;

        Maps map = new Maps();

        map.setIdMap(id);

       

        Zombies zombie = new Zombies();

        zombie.setId(1);

        zombie.setIdMap(id);

 

        when(mapsService.findById(id)).thenReturn(Optional.of(map));

        when(zombiesService.findByMapId(id)).thenReturn(Arrays.asList(zombie));

 

        // Act

        ResponseEntity<String> response = mapsController.deleteMap(id);

 

        // Assert

        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertEquals("Map et zombies associés supprimés avec succès!", response.getBody());

        verify(zombiesService).delete(zombie.getId());

        verify(mapsService).delete(id);

    }

 

    @Test

    public void testDeleteMapNotFound() {

        // Arrange

        int id = 999;

        when(mapsService.findById(id)).thenReturn(Optional.empty());

 

        // Act

        ResponseEntity<String> response = mapsController.deleteMap(id);

 

        // Assert

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        assertTrue(response.getBody().contains("Map non trouvée"));

        verify(mapsService, never()).delete(anyInt());

    }

}

 