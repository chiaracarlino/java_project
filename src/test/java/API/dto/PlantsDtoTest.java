/*package API.dto;

import com.epf.API.dto.PlantsDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class PlantsDtoTest {
    
    private PlantsDto plantsDto;

    @BeforeEach
    void setUp() {
        plantsDto = new PlantsDto();
        plantsDto.setId_plante(1);
        plantsDto.setNom("Test Plant");
        plantsDto.setPoint_de_vie(100);
        plantsDto.setAttaque_par_seconde(new BigDecimal("1.5"));
        plantsDto.setDegat_attaque(25);
        plantsDto.setCout(100);
        plantsDto.setSoleil_par_seconde(new BigDecimal("0.5"));
        plantsDto.setEffet("normal");
        plantsDto.setChemin_image("test.png");
    }

    @Test
    void gettersAndSetters_ShouldWorkCorrectly() {
        assertEquals(1, plantsDto.getId_plante());
        assertEquals("Test Plant", plantsDto.getNom());
        assertEquals(100, plantsDto.getPoint_de_vie());
        assertEquals(new BigDecimal("1.5"), plantsDto.getAttaque_par_seconde());
        assertEquals(25, plantsDto.getDegat_attaque());
        assertEquals(100, plantsDto.getCout());
        assertEquals(new BigDecimal("0.5"), plantsDto.getSoleil_par_seconde());
        assertEquals("normal", plantsDto.getEffet());
        assertEquals("test.png", plantsDto.getChemin_image());
    }

    @Test
    void equals_ShouldReturnTrue_WhenObjectsAreEqual() {
        PlantsDto otherDto = new PlantsDto();
        otherDto.setId_plante(1);
        otherDto.setNom("Test Plant");
        otherDto.setPoint_de_vie(100);
        otherDto.setAttaque_par_seconde(new BigDecimal("1.5"));
        otherDto.setDegat_attaque(25);
        otherDto.setCout(100);
        otherDto.setSoleil_par_seconde(new BigDecimal("0.5"));
        otherDto.setEffet("normal");
        otherDto.setChemin_image("test.png");

        assertEquals(plantsDto, otherDto);
        assertEquals(plantsDto.hashCode(), otherDto.hashCode());
    }

    @Test
    void equals_ShouldReturnFalse_WhenObjectsAreDifferent() {
        PlantsDto otherDto = new PlantsDto();
        otherDto.setId_plante(2);
        otherDto.setNom("Different Plant");

        assertNotEquals(plantsDto, otherDto);
        assertNotEquals(plantsDto.hashCode(), otherDto.hashCode());
    }

    @Test
    void toString_ShouldContainAllFields() {
        String toString = plantsDto.toString();
        
        assertTrue(toString.contains("id_plante=1"));
        assertTrue(toString.contains("nom=Test Plant"));
        assertTrue(toString.contains("point_de_vie=100"));
        assertTrue(toString.contains("attaque_par_seconde=1.5"));
        assertTrue(toString.contains("degat_attaque=25"));
        assertTrue(toString.contains("cout=100"));
        assertTrue(toString.contains("soleil_par_seconde=0.5"));
        assertTrue(toString.contains("effet=normal"));
        assertTrue(toString.contains("chemin_image=test.png"));
    }
} */
