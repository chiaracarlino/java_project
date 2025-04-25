/*package API.dto;

import com.epf.API.dto.ZombiesDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ZombiesDtoTest {
    
    private ZombiesDto zombiesDto;

    @BeforeEach
    void setUp() {
        zombiesDto = new ZombiesDto();
        zombiesDto.setId_zombie(1);
        zombiesDto.setNom("Test Zombie");
        zombiesDto.setPoint_de_vie(100);
        zombiesDto.setAttaque_par_seconde(new BigDecimal("1.5"));
        zombiesDto.setDegat_attaque(25);
        zombiesDto.setVitesse_de_deplacement(new BigDecimal("0.5"));
        zombiesDto.setChemin_image("zombie.png");
        zombiesDto.setId_map(1);
    }

    @Test
    void gettersAndSetters_ShouldWorkCorrectly() {
        assertEquals(1, zombiesDto.getId_zombie());
        assertEquals("Test Zombie", zombiesDto.getNom());
        assertEquals(100, zombiesDto.getPoint_de_vie());
        assertEquals(new BigDecimal("1.5"), zombiesDto.getAttaque_par_seconde());
        assertEquals(25, zombiesDto.getDegat_attaque());
        assertEquals(new BigDecimal("0.5"), zombiesDto.getVitesse_de_deplacement());
        assertEquals("zombie.png", zombiesDto.getChemin_image());
        assertEquals(1, zombiesDto.getId_map());
    }

    @Test
    void equals_ShouldReturnTrue_WhenObjectsAreEqual() {
        ZombiesDto otherDto = new ZombiesDto();
        otherDto.setId_zombie(1);
        otherDto.setNom("Test Zombie");
        otherDto.setPoint_de_vie(100);
        otherDto.setAttaque_par_seconde(new BigDecimal("1.5"));
        otherDto.setDegat_attaque(25);
        otherDto.setVitesse_de_deplacement(new BigDecimal("0.5"));
        otherDto.setChemin_image("zombie.png");
        otherDto.setId_map(1);

        assertEquals(zombiesDto, otherDto);
        assertEquals(zombiesDto.hashCode(), otherDto.hashCode());
    }

    @Test
    void equals_ShouldReturnFalse_WhenObjectsAreDifferent() {
        ZombiesDto otherDto = new ZombiesDto();
        otherDto.setId_zombie(2);
        otherDto.setNom("Different Zombie");

        assertNotEquals(zombiesDto, otherDto);
        assertNotEquals(zombiesDto.hashCode(), otherDto.hashCode());
    }

    @Test
    void toString_ShouldContainAllFields() {
        String toString = zombiesDto.toString();
        
        assertTrue(toString.contains("id_zombie=1"));
        assertTrue(toString.contains("nom=Test Zombie"));
        assertTrue(toString.contains("point_de_vie=100"));
        assertTrue(toString.contains("attaque_par_seconde=1.5"));
        assertTrue(toString.contains("degat_attaque=25"));
        assertTrue(toString.contains("vitesse_de_deplacement=0.5"));
        assertTrue(toString.contains("chemin_image=zombie.png"));
        assertTrue(toString.contains("id_map=1"));
    }
} */
