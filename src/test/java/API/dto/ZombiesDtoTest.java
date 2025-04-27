package API.dto;

 

import com.epf.API.dto.ZombiesDto;

import org.junit.Test;

import static org.junit.Assert.*;

 

public class ZombiesDtoTest {

 

    @Test

    public void testZombiesDtoConstructorAndGetters() {

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

        ZombiesDto zombiesDto = new ZombiesDto(id, nom, pointDeVie, degatAttaque,

                idMap, attaqueParSeconde, vitesseDeDeplacement, cheminImage);

 

        // Assert

        assertEquals(id, zombiesDto.getId_zombie());

        assertEquals(nom, zombiesDto.getNom());

        assertEquals(pointDeVie, zombiesDto.getPoint_de_vie());

        assertEquals(degatAttaque, zombiesDto.getDegat_attaque());

        assertEquals(idMap, zombiesDto.getId_map());

        assertEquals(attaqueParSeconde, zombiesDto.getAttaque_par_seconde());

        assertEquals(vitesseDeDeplacement, zombiesDto.getVitesse_de_deplacement());

        assertEquals(cheminImage, zombiesDto.getChemin_image());

    }

 

    @Test

    public void testZombiesDtoEmptyConstructor() {

        // Act

        ZombiesDto zombiesDto = new ZombiesDto();

 

        // Assert

        assertNull(zombiesDto.getId_zombie());

        assertNull(zombiesDto.getNom());

        assertNull(zombiesDto.getPoint_de_vie());

        assertNull(zombiesDto.getDegat_attaque());

        assertNull(zombiesDto.getId_map());

        assertNull(zombiesDto.getAttaque_par_seconde());

        assertNull(zombiesDto.getVitesse_de_deplacement());

        assertNull(zombiesDto.getChemin_image());

    }

 

    @Test

    public void testZombiesDtoSetters() {

        // Arrange

        ZombiesDto zombiesDto = new ZombiesDto();

        Integer id = 1;

        String nom = "Fast Zombie";

        Integer pointDeVie = 80;

        Integer degatAttaque = 15;

        Integer idMap = 2;

        Double attaqueParSeconde = 2.0;

        Double vitesseDeDeplacement = 1.0;

        String cheminImage = "path/to/fast_zombie.png";

 

        // Act

        zombiesDto.setId_zombie(id);

        zombiesDto.setNom(nom);

        zombiesDto.setPoint_de_vie(pointDeVie);

        zombiesDto.setDegat_attaque(degatAttaque);

        zombiesDto.setId_map(idMap);

        zombiesDto.setAttaque_par_seconde(attaqueParSeconde);

        zombiesDto.setVitesse_de_deplacement(vitesseDeDeplacement);

        zombiesDto.setChemin_image(cheminImage);

 

        // Assert

        assertEquals(id, zombiesDto.getId_zombie());

        assertEquals(nom, zombiesDto.getNom());

        assertEquals(pointDeVie, zombiesDto.getPoint_de_vie());

        assertEquals(degatAttaque, zombiesDto.getDegat_attaque());

        assertEquals(idMap, zombiesDto.getId_map());

        assertEquals(attaqueParSeconde, zombiesDto.getAttaque_par_seconde());

        assertEquals(vitesseDeDeplacement, zombiesDto.getVitesse_de_deplacement());

        assertEquals(cheminImage, zombiesDto.getChemin_image());

    }

}

 
