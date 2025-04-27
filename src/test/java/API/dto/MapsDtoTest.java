package API.dto;

 

import com.epf.API.dto.MapsDto;

import org.junit.Test;

import static org.junit.Assert.*;

 

public class MapsDtoTest {

 

    @Test

    public void testMapsDtoConstructorAndGetters() {

        // Arrange

        Integer id = 1;

        Integer ligne = 5;

        Integer colonne = 7;

        String cheminImage = "path/to/image.png";

 

        // Act

        MapsDto mapsDto = new MapsDto(id, ligne, colonne, cheminImage);

 

        // Assert

        assertEquals(id, mapsDto.getId_map());

        assertEquals(ligne, mapsDto.getLigne());

        assertEquals(colonne, mapsDto.getColonne());

        assertEquals(cheminImage, mapsDto.getChemin_image());

    }

 

    @Test

    public void testMapsDtoEmptyConstructor() {

        // Act

        MapsDto mapsDto = new MapsDto();

 

        // Assert

        assertNull(mapsDto.getId_map());

        assertNull(mapsDto.getLigne());

        assertNull(mapsDto.getColonne());

        assertNull(mapsDto.getChemin_image());

    }

 

    @Test

    public void testMapsDtoSetters() {

        // Arrange

        MapsDto mapsDto = new MapsDto();

        Integer id = 1;

        Integer ligne = 5;

        Integer colonne = 7;

        String cheminImage = "path/to/image.png";

 

        // Act

        mapsDto.setId_map(id);

        mapsDto.setLigne(ligne);

        mapsDto.setColonne(colonne);

        mapsDto.setChemin_image(cheminImage);

 

        // Assert

        assertEquals(id, mapsDto.getId_map());

        assertEquals(ligne, mapsDto.getLigne());

        assertEquals(colonne, mapsDto.getColonne());

        assertEquals(cheminImage, mapsDto.getChemin_image());

    }

}

 