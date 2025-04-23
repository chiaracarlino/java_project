/*package API.controller;

import com.epf.persistence.model.Maps;
import com.epf.core.services.MapsServices;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class MapsControllerTest {

    private MapsController mapsController;
    private MapsServices mapsService;

    @Before
    public void setup() {
        mapsService = mock(MapsServices.class);
        mapsController = new MapsController(mapsService);
    }

    @Test
    public void testGetAllMaps() {
        List<Maps> fakeMaps = Arrays.asList(
                new Maps(1L, 5, 5, "chemin1.png"),
                new Maps(2L, 6, 6, "chemin2.png")
        );

        when(mapsService.findAll()).thenReturn(fakeMaps);

        List<Maps> result = mapsController.getAllMaps();

        assertEquals(2, result.size());
        assertEquals("chemin1.png", result.get(0).getCheminImage());
    }
}
*/


