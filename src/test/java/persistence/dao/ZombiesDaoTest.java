package persistence.dao;

 

import com.epf.persistence.model.Zombies;

import com.epf.persistence.model.Maps;

import org.junit.Test;

import static org.junit.Assert.*;

 

public class ZombiesDaoTest {

 

    @Test

    public void testZombiesConstructorAndGetters() {

        // Arrange

        Integer id = 1;

        String nom = "Basic Zombie";

        Integer pointDeVie = 100;

        Integer degatAttaque = 20;

        Integer idMap = 1;

        Double attaqueParSeconde = 1.0;

        Double vitesseDeDeplacement = 0.5;

        String cheminImage = "path/to/zombie.png";

 

        // Act

        Zombies zombie = new Zombies(id, nom, pointDeVie, degatAttaque, idMap,

                attaqueParSeconde, vitesseDeDeplacement, cheminImage);

 

        // Assert

        assertEquals(id, zombie.getId());

        assertEquals(nom, zombie.getNom());

        assertEquals(pointDeVie, zombie.getPointDeVie());

        assertEquals(degatAttaque, zombie.getDegatAttaque());

        assertEquals(idMap, zombie.getIdMap());

        assertEquals(attaqueParSeconde, zombie.getAttaqueParSeconde());

        assertEquals(vitesseDeDeplacement, zombie.getVitesseDeDeplacement());

        assertEquals(cheminImage, zombie.getCheminImage());

    }

 

    @Test

    public void testZombiesEmptyConstructor() {

        // Act

        Zombies zombie = new Zombies();

 

        // Assert

        assertNull(zombie.getId());

        assertNull(zombie.getNom());

        assertNull(zombie.getPointDeVie());

        assertNull(zombie.getDegatAttaque());

        assertNull(zombie.getIdMap());

        assertNull(zombie.getAttaqueParSeconde());

        assertNull(zombie.getVitesseDeDeplacement());

        assertNull(zombie.getCheminImage());

        assertNull(zombie.getMap());

    }

 

    @Test

    public void testZombiesSetters() {

        // Arrange

        Zombies zombie = new Zombies();

        Integer id = 1;

        String nom = "Fast Zombie";

        Integer pointDeVie = 80;

        Integer degatAttaque = 15;

        Integer idMap = 2;

        Double attaqueParSeconde = 2.0;

        Double vitesseDeDeplacement = 1.0;

        String cheminImage = "path/to/fast_zombie.png";

 

        // Act

        zombie.setId(id);

        zombie.setNom(nom);

        zombie.setPointDeVie(pointDeVie);

        zombie.setDegatAttaque(degatAttaque);

        zombie.setIdMap(idMap);

        zombie.setAttaqueParSeconde(attaqueParSeconde);

        zombie.setVitesseDeDeplacement(vitesseDeDeplacement);

        zombie.setCheminImage(cheminImage);

 

        // Assert

        assertEquals(id, zombie.getId());

        assertEquals(nom, zombie.getNom());

        assertEquals(pointDeVie, zombie.getPointDeVie());

        assertEquals(degatAttaque, zombie.getDegatAttaque());

        assertEquals(idMap, zombie.getIdMap());

        assertEquals(attaqueParSeconde, zombie.getAttaqueParSeconde());

        assertEquals(vitesseDeDeplacement, zombie.getVitesseDeDeplacement());

        assertEquals(cheminImage, zombie.getCheminImage());

    }

 

    @Test

    public void testMapAssociation() {

        // Arrange

        Zombies zombie = new Zombies();

        Maps map = new Maps();

        map.setIdMap(1);

 

        // Act

        zombie.setMap(map);

 

        // Assert

        assertNotNull(zombie.getMap());

        assertEquals(Integer.valueOf(1), zombie.getMap().getIdMap());

    }

 

    @Test

    public void testToString() {

        // Arrange

        Zombies zombie = new Zombies(1, "Test Zombie", 100, 20, 1, 1.0, 0.5, "test.png");

 

        // Act

        String result = zombie.toString();

 

        // Assert

        assertTrue(result.contains("id=1"));

        assertTrue(result.contains("nom='Test Zombie'"));

        assertTrue(result.contains("pointDeVie=100"));

        assertTrue(result.contains("degatAttaque=20"));

        assertTrue(result.contains("idMap=1"));

    }

}

 