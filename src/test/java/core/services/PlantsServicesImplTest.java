package core.services;

import com.epf.persistence.model.Plants;
import com.epf.persistence.repository.PlantsRepository;
import com.epf.core.services.PlantsServices;
import com.epf.core.PlantsServicesImpl;
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

public class PlantsServicesImplTest {

    @Mock
    private PlantsRepository plantsRepository;  // Mocker le repository pour ne pas interagir avec la base de données réelle

    @InjectMocks
    private PlantsServicesImpl plantsServices;  // Injecter le service avec le repository mocké

    private Plants plant;

    @Before
    public void setUp() {
        // Initialisation d'un objet Plants pour les tests
        plant = new Plants();
        plant.setIdPlante(1);
        plant.setNom("Plante de test");
        plant.setPointDeVie(100);
        plant.setDegatAttaque(10);
        plant.setCout(50);
        plant.setAttaqueParSeconde(5.0);
        plant.setSoleilParSeconde(2.0);

        // Initialisation de Mockito
        MockitoAnnotations.initMocks(this); // Initialise les mocks
    }

    @Test
    public void testSave_Valid() {
        // Configurer le mock pour retourner l'objet Plants sauvegardé
        when(plantsRepository.save(any(Plants.class))).thenReturn(plant);

        // Tester la méthode save
        Plants savedPlant = plantsServices.save(plant);

        assertNotNull(savedPlant);
        assertEquals("Plante de test", savedPlant.getNom());
        assertEquals(100, savedPlant.getPointDeVie());
        verify(plantsRepository, times(1)).save(plant);  // Vérifier que save a été appelé une fois
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSave_InvalidNom() {
        // Créer une plante avec un nom invalide
        plant.setNom(null);

        // Tester la méthode save avec une plante invalide
        plantsServices.save(plant);  // Cela devrait lancer une IllegalArgumentException
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSave_InvalidPointDeVie() {
        // Créer une plante avec des points de vie invalides
        plant.setPointDeVie(0);

        // Tester la méthode save avec une plante invalide
        plantsServices.save(plant);  // Cela devrait lancer une IllegalArgumentException
    }

    @Test
    public void testFindById_Found() {
        // Configurer le mock pour retourner une plante pour l'ID spécifié
        when(plantsRepository.findById(1)).thenReturn(Optional.of(plant));

        // Tester la méthode findById avec un ID existant
        Optional<Plants> result = plantsServices.findById(1);

        assertTrue(result.isPresent());
        assertEquals(plant, result.get());
        verify(plantsRepository, times(1)).findById(1);  // Vérifier que findById a été appelé une fois
    }

    @Test
    public void testFindById_NotFound() {
        // Configurer le mock pour retourner un Optional vide pour un ID inexistant
        when(plantsRepository.findById(999)).thenReturn(Optional.empty());

        // Tester la méthode findById avec un ID inexistant
        Optional<Plants> result = plantsServices.findById(999);

        assertFalse(result.isPresent());
        verify(plantsRepository, times(1)).findById(999);  // Vérifier que findById a été appelé une fois
    }

    @Test
    public void testFindAll() {
        // Configurer le mock pour retourner une liste de plantes
        List<Plants> plantsList = Arrays.asList(plant);
        when(plantsRepository.findAll()).thenReturn(plantsList);

        // Tester la méthode findAll
        List<Plants> result = plantsServices.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(plant, result.get(0));
        verify(plantsRepository, times(1)).findAll();  // Vérifier que findAll a été appelé une fois
    }

    @Test
    public void testUpdate_Valid() {
        // Configurer le mock pour retourner la plante mise à jour
        when(plantsRepository.update(any(Plants.class))).thenReturn(plant);
        when(plantsRepository.findById(1)).thenReturn(Optional.of(plant));

        // Tester la méthode update avec une plante valide
        Plants updatedPlant = plantsServices.update(plant);

        assertNotNull(updatedPlant);
        assertEquals(1, updatedPlant.getIdPlante());
        verify(plantsRepository, times(1)).update(plant);  // Vérifier que update a été appelé une fois
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdate_Invalid() {
        // Configurer le mock pour retourner Optional.empty() si l'ID de la plante n'existe pas
        when(plantsRepository.findById(999)).thenReturn(Optional.empty());

        // Tester la méthode update avec une plante qui n'existe pas
        plantsServices.update(plant);  // Cela devrait lancer une IllegalArgumentException
    }

    @Test
    public void testDelete() {
        // Configurer le mock pour que findById retourne la plante à supprimer
        when(plantsRepository.findById(1)).thenReturn(Optional.of(plant));

        // Tester la méthode delete
        plantsServices.delete(1);

        verify(plantsRepository, times(1)).delete(1);  // Vérifier que delete a été appelé une fois
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDelete_NotFound() {
        // Configurer le mock pour que findById retourne un Optional vide
        when(plantsRepository.findById(999)).thenReturn(Optional.empty());

        // Tester la méthode delete avec un ID inexistant
        plantsServices.delete(999);  // Cela devrait lancer une IllegalArgumentException
    }
}
