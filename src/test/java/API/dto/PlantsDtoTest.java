package API.dto;

 

import com.epf.API.dto.PlantsDto;

import org.junit.Test;

import static org.junit.Assert.*;

 

public class PlantsDtoTest {

 

    @Test

    public void testPlantsDtoConstructorAndGetters() {

        // Arrange

        Integer id = 1;

        String nom = "Peashooter";

        Integer pointDeVie = 100;

        Double attaqueParSeconde = 1.5;

        Integer degatAttaque = 20;

        Integer cout = 100;

        Double soleilParSeconde = 0.0;

        String effet = "Basic attack";

        String cheminImage = "path/to/peashooter.png";

 

        // Act

        PlantsDto plantsDto = new PlantsDto(id, nom, pointDeVie, attaqueParSeconde,

                degatAttaque, cout, soleilParSeconde, effet, cheminImage);

 

        // Assert

        assertEquals(id, plantsDto.getId_plante());

        assertEquals(nom, plantsDto.getNom());

        assertEquals(pointDeVie, plantsDto.getPoint_de_vie());

        assertEquals(attaqueParSeconde, plantsDto.getAttaque_par_seconde());

        assertEquals(degatAttaque, plantsDto.getDegat_attaque());

        assertEquals(cout, plantsDto.getCout());

        assertEquals(soleilParSeconde, plantsDto.getSoleil_par_seconde());

        assertEquals(effet, plantsDto.getEffet());

        assertEquals(cheminImage, plantsDto.getChemin_image());

    }

 

    @Test

    public void testPlantsDtoEmptyConstructor() {

        // Act

        PlantsDto plantsDto = new PlantsDto();

 

        // Assert

        assertNull(plantsDto.getId_plante());

        assertNull(plantsDto.getNom());

        assertNull(plantsDto.getPoint_de_vie());

        assertNull(plantsDto.getAttaque_par_seconde());

        assertNull(plantsDto.getDegat_attaque());

        assertNull(plantsDto.getCout());

        assertNull(plantsDto.getSoleil_par_seconde());

        assertNull(plantsDto.getEffet());

        assertNull(plantsDto.getChemin_image());

    }

 

    @Test

    public void testPlantsDtoSetters() {

        // Arrange

        PlantsDto plantsDto = new PlantsDto();

        Integer id = 1;

        String nom = "Sunflower";

        Integer pointDeVie = 50;

        Double attaqueParSeconde = 0.0;

        Integer degatAttaque = 0;

        Integer cout = 50;

        Double soleilParSeconde = 1.0;

        String effet = "Produces sun";

        String cheminImage = "path/to/sunflower.png";

 

        // Act

        plantsDto.setId_plante(id);

        plantsDto.setNom(nom);

        plantsDto.setPoint_de_vie(pointDeVie);

        plantsDto.setAttaque_par_seconde(attaqueParSeconde);

        plantsDto.setDegat_attaque(degatAttaque);

        plantsDto.setCout(cout);

        plantsDto.setSoleil_par_seconde(soleilParSeconde);

        plantsDto.setEffet(effet);

        plantsDto.setChemin_image(cheminImage);

 

        // Assert

        assertEquals(id, plantsDto.getId_plante());

        assertEquals(nom, plantsDto.getNom());

        assertEquals(pointDeVie, plantsDto.getPoint_de_vie());

        assertEquals(attaqueParSeconde, plantsDto.getAttaque_par_seconde());

        assertEquals(degatAttaque, plantsDto.getDegat_attaque());

        assertEquals(cout, plantsDto.getCout());

        assertEquals(soleilParSeconde, plantsDto.getSoleil_par_seconde());

        assertEquals(effet, plantsDto.getEffet());

        assertEquals(cheminImage, plantsDto.getChemin_image());

    }

}

 