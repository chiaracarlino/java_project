package persistence.dao;

 

import com.epf.persistence.model.Maps;

import com.epf.persistence.model.Zombies;

import org.junit.Test;

import java.util.ArrayList;

import java.util.List;

import static org.junit.Assert.*;

 

public class MapsDaoTest {

 

    @Test

    public void testMapsConstructorAndGetters() {

        // Arrange

        Integer id = 1;

        Integer ligne = 5;

        Integer colonne = 7;

        String cheminImage = "path/to/image.png";

 

        // Act

        Maps map = new Maps(id, ligne, colonne, cheminImage);

 

        // Assert

        assertEquals(id, map.getIdMap());

        assertEquals(ligne, map.getLigne());

        assertEquals(colonne, map.getColonne());

        assertEquals(cheminImage, map.getCheminImage());

    }

 

    @Test

    public void testMapsEmptyConstructor() {

        // Act

        Maps map = new Maps();

 

        // Assert

        assertNull(map.getIdMap());

        assertNull(map.getLigne());

        assertNull(map.getColonne());

        assertNull(map.getCheminImage());

        assertNull(map.getZombies());

    }

 

    @Test

    public void testMapsSetters() {

        // Arrange

        Maps map = new Maps();

        Integer id = 1;

        Integer ligne = 5;

        Integer colonne = 7;

        String cheminImage = "path/to/image.png";

 

        // Act

        map.setIdMap(id);

        map.setLigne(ligne);

        map.setColonne(colonne);

        map.setCheminImage(cheminImage);

 

        // Assert

        assertEquals(id, map.getIdMap());

        assertEquals(ligne, map.getLigne());

        assertEquals(colonne, map.getColonne());

        assertEquals(cheminImage, map.getCheminImage());

    }

 

    @Test

    public void testZombiesList() {

        // Arrange

        Maps map = new Maps();

        List<Zombies> zombies = new ArrayList<>();

        Zombies zombie1 = new Zombies();

        zombie1.setId(1);

        Zombies zombie2 = new Zombies();

        zombie2.setId(2);

        zombies.add(zombie1);

        zombies.add(zombie2);

 

        // Act

        map.setZombies(zombies);

 

        // Assert

        assertNotNull(map.getZombies());

        assertEquals(2, map.getZombies().size());

        assertEquals(Integer.valueOf(1), map.getZombies().get(0).getId());

        assertEquals(Integer.valueOf(2), map.getZombies().get(1).getId());

    }

}