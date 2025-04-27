package persistence.dao;

 

import com.epf.persistence.model.Plants;

import org.junit.Test;

import static org.junit.Assert.*;

 

public class PlantsDaoTest {

 

    @Test

    public void testPlantsConstructorAndGetters() {

        // Arrange

        int id = 1;

        String nom = "Peashooter";

        int pointDeVie = 100;

        Double attaqueParSeconde = 1.5;

        int degatAttaque = 20;

        int cout = 100;

        Double soleilParSeconde = 0.0;

        String effet = "Basic attack";

        String cheminImage = "path/to/image.png";

 

        // Act

        Plants plant = new Plants(id, nom, pointDeVie, attaqueParSeconde,

            degatAttaque, cout, soleilParSeconde, effet, cheminImage);

 

        // Assert

        assertEquals(id, plant.getIdPlante());

        assertEquals(nom, plant.getNom());

        assertEquals(pointDeVie, plant.getPointDeVie());

        assertEquals(attaqueParSeconde, plant.getAttaqueParSeconde());

        assertEquals(degatAttaque, plant.getDegatAttaque());

        assertEquals(cout, plant.getCout());

        assertEquals(soleilParSeconde, plant.getSoleilParSeconde());

        assertEquals(effet, plant.getEffet());

        assertEquals(cheminImage, plant.getCheminImage());

    }

 

    @Test

    public void testPlantsEmptyConstructor() {

        // Act

        Plants plant = new Plants();

 

        // Assert

        assertEquals(0, plant.getIdPlante());

        assertNull(plant.getNom());

        assertEquals(0, plant.getPointDeVie());

        assertNull(plant.getAttaqueParSeconde());

        assertEquals(0, plant.getDegatAttaque());

        assertEquals(0, plant.getCout());

        assertNull(plant.getSoleilParSeconde());

        assertNull(plant.getEffet());

        assertNull(plant.getCheminImage());

    }

 

    @Test

    public void testPlantsSetters() {

        // Arrange

        Plants plant = new Plants();

        int id = 1;

        String nom = "Sunflower";

        int pointDeVie = 50;

        Double attaqueParSeconde = 0.0;

        int degatAttaque = 0;

        int cout = 50;

        Double soleilParSeconde = 1.0;

        String effet = "Produces sun";

        String cheminImage = "path/to/sunflower.png";

 

        // Act

        plant.setIdPlante(id);

        plant.setNom(nom);

        plant.setPointDeVie(pointDeVie);

        plant.setAttaqueParSeconde(attaqueParSeconde);

        plant.setDegatAttaque(degatAttaque);

        plant.setCout(cout);

        plant.setSoleilParSeconde(soleilParSeconde);

        plant.setEffet(effet);

        plant.setCheminImage(cheminImage);

 

        // Assert

        assertEquals(id, plant.getIdPlante());

        assertEquals(nom, plant.getNom());

        assertEquals(pointDeVie, plant.getPointDeVie());

        assertEquals(attaqueParSeconde, plant.getAttaqueParSeconde());

        assertEquals(degatAttaque, plant.getDegatAttaque());

        assertEquals(cout, plant.getCout());

        assertEquals(soleilParSeconde, plant.getSoleilParSeconde());

        assertEquals(effet, plant.getEffet());

        assertEquals(cheminImage, plant.getCheminImage());

    }

}

