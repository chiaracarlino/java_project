package core.services;

import com.epf.persistence.model.Zombies;
import com.epf.persistence.dao.ZombiesDao;
import com.epf.persistence.dao.MapsDao;
import com.epf.core.ZombiesServicesImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.util.Optional;
import java.util.List;
import java.util.Arrays;

public class ZombiesServicesTest {

    @Mock
    private ZombiesDao zombiesDao;  // Mocker le ZombiesDao

    @Mock
    private MapsDao mapsDao;  // Mocker le MapsDao

    @InjectMocks
    private ZombiesServicesImpl zombiesServices;  // Injecter le service avec les mocks

    private Zombies zombie;

    @Before
    public void setUp() {
        // Initialisation d'un objet Zombies pour les tests
        zombie = new Zombies();
        zombie.setId(1);
        zombie.setNom("Zombie de test");
        zombie.setPointDeVie(100);
        zombie.setAttaqueParSeconde(10.0);
        zombie.setDegatAttaque(20);
        zombie.setVitesseDeDeplacement(1.5);
        zombie.setCheminImage("path/to/image.png");

        // Initialisation de Mockito
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSave_Valid() {
        // Configurer le mock pour que save retourne le zombie sauvegardé
        when(zombiesDao.save(any(Zombies.class))).thenReturn(zombie);

        // Tester la méthode save
        Zombies savedZombie = zombiesServices.save(zombie);

        assertNotNull(savedZombie);
        assertEquals("Zombie de test", savedZombie.getNom());
        verify(zombiesDao, times(1)).save(zombie);  // Vérifier que save a été appelé une fois
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSave_InvalidNom() {
        // Créer un zombie avec un nom invalide
        zombie.setNom(null);

        // Tester la méthode save avec un zombie invalide
        zombiesServices.save(zombie);  // Cela devrait lancer une IllegalArgumentException
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSave_InvalidPointDeVie() {
        // Créer un zombie avec des points de vie invalides
        zombie.setPointDeVie(0);

        // Tester la méthode save avec un zombie invalide
        zombiesServices.save(zombie);  // Cela devrait lancer une IllegalArgumentException
    }

    @Test(expected = RuntimeException.class)
    public void testSave_MapNotFound() {
        // Configurer le mock pour que la map n'existe pas
        when(mapsDao.findById(anyInt())).thenReturn(Optional.empty());

        // Assigner un id de map invalide
        zombie.setIdMap(999);

        // Tester la méthode save avec une map invalide
        zombiesServices.save(zombie);  // Cela devrait lancer une RuntimeException
    }

    @Test
    public void testFindById_Found() {
        // Configurer le mock pour retourner un zombie pour l'ID spécifié
        when(zombiesDao.findById(1)).thenReturn(Optional.of(zombie));

        // Tester la méthode findById avec un ID existant
        Optional<Zombies> result = zombiesServices.findById(1);

        assertTrue(result.isPresent());
        assertEquals(zombie, result.get());
        verify(zombiesDao, times(1)).findById(1);  // Vérifier que findById a été appelé une fois
    }

    @Test
    public void testFindById_NotFound() {
        // Configurer le mock pour retourner un Optional vide
        when(zombiesDao.findById(999)).thenReturn(Optional.empty());

        // Tester la méthode findById avec un ID inexistant
        Optional<Zombies> result = zombiesServices.findById(999);

        assertFalse(result.isPresent());
        verify(zombiesDao, times(1)).findById(999);  // Vérifier que findById a été appelé une fois
    }

    @Test
    public void testFindAll() {
        // Configurer le mock pour retourner une liste de zombies
        List<Zombies> zombiesList = Arrays.asList(zombie);
        when(zombiesDao.findAll()).thenReturn(zombiesList);

        // Tester la méthode findAll
        List<Zombies> result = zombiesServices.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(zombie, result.get(0));
        verify(zombiesDao, times(1)).findAll();  // Vérifier que findAll a été appelé une fois
    }

    @Test
    public void testFindByMapId() {
        // Configurer le mock pour que findById retourne une map valide
        when(mapsDao.findById(1)).thenReturn(Optional.of(new Object()));  // Simuler une map valide
        when(zombiesDao.findByMapId(1)).thenReturn(Arrays.asList(zombie));

        // Tester la méthode findByMapId
        List<Zombies> zombiesByMap = zombiesServices.findByMapId(1);

        assertNotNull(zombiesByMap);
        assertEquals(1, zombiesByMap.size());
        verify(zombiesDao, times(1)).findByMapId(1);  // Vérifier que findByMapId a été appelé une fois
    }

    @Test(expected = RuntimeException.class)
    public void testFindByMapId_MapNotFound() {
        // Configurer le mock pour que findById retourne un Optional vide
        when(mapsDao.findById(999)).thenReturn(Optional.empty());

        // Tester la méthode findByMapId avec une map invalide
        zombiesServices.findByMapId(999);  // Cela devrait lancer une RuntimeException
    }

    @Test
    public void testUpdate_Valid() {
        // Configurer le mock pour retourner un zombie mis à jour
        when(zombiesDao.findById(1)).thenReturn(Optional.of(zombie));
        when(zombiesDao.update(any(Zombies.class))).thenReturn(zombie);

        // Tester la méthode update avec un zombie valide
        Zombies updatedZombie = zombiesServices.update(zombie);

        assertNotNull(updatedZombie);
        assertEquals(zombie, updatedZombie);
        verify(zombiesDao, times(1)).update(zombie);  // Vérifier que update a été appelé une fois
    }

    @Test(expected = RuntimeException.class)
    public void testUpdate_ZombieNotFound() {
        // Configurer le mock pour retourner un Optional vide
        when(zombiesDao.findById(999)).thenReturn(Optional.empty());

        // Tester la méthode update avec un zombie inexistant
        zombiesServices.update(zombie);  // Cela devrait lancer une RuntimeException
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdate_InvalidZombie() {
        // Créer un zombie avec un nom invalide
        zombie.setNom(null);

        // Tester la méthode update avec un zombie invalide
        zombiesServices.update(zombie);  // Cela devrait lancer une IllegalArgumentException
    }

    @Test
    public void testDelete() {
        // Configurer le mock pour que findById retourne un zombie valide
        when(zombiesDao.findById(1)).thenReturn(Optional.of(zombie));

        // Tester la méthode delete
        zombiesServices.delete(1);

        verify(zombiesDao, times(1)).delete(1);  // Vérifier que delete a été appelé une fois
    }

    @Test(expected = RuntimeException.class)
    public void testDelete_NotFound() {
        // Configurer le mock pour que findById retourne un Optional vide
        when(zombiesDao.findById(999)).thenReturn(Optional.empty());

        // Tester la méthode delete avec un ID inexistant
        zombiesServices.delete(999);  // Cela devrait lancer une RuntimeException
    }
}

