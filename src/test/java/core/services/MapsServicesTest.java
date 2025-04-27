package core.services;

import com.epf.persistence.dao.MapsDao;
import com.epf.persistence.model.Maps;
import com.epf.core.services.MapsServices;
import com.epf.core.MapsServicesImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.util.Optional;
import java.util.List;
import java.util.Arrays;

public class MapsServicesTest {

    @Mock
    private MapsDao mapsDao;  // Mocker le DAO pour ne pas interagir avec la base de données réelle

    @InjectMocks
    private MapsServicesImpl mapsServices;  // Injecter le service avec le DAO mocké

    private Maps map;

    @Before
    public void setUp() {
        // Initialisation d'un objet Maps pour les tests
        map = new Maps();
        map.setIdMap(1);
        map.setLigne(10);
        map.setColonne(20);
        map.setCheminImage("maps/sample.png");

        // Initialisation de Mockito
        MockitoAnnotations.initMocks(this); // Initialise les mocks
    }

    @Test
    public void testSave() {
        // Configurer le mock pour retourner l'objet Maps sauvegardé
        when(mapsDao.save(any(Maps.class))).thenReturn(map);

        // Tester la méthode save
        Maps savedMap = mapsServices.save(map);

        assertNotNull(savedMap);
        assertEquals(1, savedMap.getIdMap());
        verify(mapsDao, times(1)).save(map);  // Vérifier que save a été appelé une fois
    }

    @Test
    public void testFindById_Found() {
        // Configurer le mock pour retourner une Map pour l'ID spécifié
        when(mapsDao.findById(1)).thenReturn(Optional.of(map));

        // Tester la méthode findById avec un ID existant
        Optional<Maps> result = mapsServices.findById(1);

        assertTrue(result.isPresent());
        assertEquals(map, result.get());
        verify(mapsDao, times(1)).findById(1);  // Vérifier que findById a été appelé une fois
    }

    @Test
    public void testFindById_NotFound() {
        // Configurer le mock pour retourner un Optional vide pour un ID inexistant
        when(mapsDao.findById(999)).thenReturn(Optional.empty());

        // Tester la méthode findById avec un ID inexistant
        Optional<Maps> result = mapsServices.findById(999);

        assertFalse(result.isPresent());
        verify(mapsDao, times(1)).findById(999);  // Vérifier que findById a été appelé une fois
    }

    @Test
    public void testFindAll() {
        // Configurer le mock pour retourner une liste de Maps
        List<Maps> mapsList = Arrays.asList(map);
        when(mapsDao.findAll()).thenReturn(mapsList);

        // Tester la méthode findAll
        List<Maps> result = mapsServices.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(map, result.get(0));
        verify(mapsDao, times(1)).findAll();  // Vérifier que findAll a été appelé une fois
    }

    @Test
    public void testUpdate_Valid() {
        // Configurer le mock pour retourner la map mise à jour
        when(mapsDao.update(any(Maps.class))).thenReturn(map);
        when(mapsDao.findById(1)).thenReturn(Optional.of(map));

        // Tester la méthode update avec une map valide
        Maps updatedMap = mapsServices.update(map);

        assertNotNull(updatedMap);
        assertEquals(1, updatedMap.getIdMap());
        verify(mapsDao, times(1)).update(map);  // Vérifier que update a été appelé une fois
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdate_Invalid() {
        // Configurer le mock pour retourner Optional.empty() si l'ID de la map n'existe pas
        when(mapsDao.findById(999)).thenReturn(Optional.empty());

        // Tester la méthode update avec une map qui n'existe pas
        mapsServices.update(map);
    }

    @Test
    public void testDelete() {
        // Tester la méthode delete
        doNothing().when(mapsDao).delete(1);  // Simuler le comportement du DAO

        mapsServices.delete(1);

        verify(mapsDao, times(1)).delete(1);  // Vérifier que delete a été appelé une fois
    }
}
