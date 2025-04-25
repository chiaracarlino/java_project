/*package API.controller;

import com.epf.API.controller.PlantsController;
import com.epf.API.dto.PlantsDto;
import com.epf.API.mapper.PlantsMapper;
import com.epf.core.services.PlantsServices;
import com.epf.persistence.model.Plants;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PlantsController.class)
class PlantsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlantsServices plantsService;

    @MockBean
    private PlantsMapper plantsMapper;

    @Autowired
    private ObjectMapper objectMapper;

    private Plants testPlant;
    private PlantsDto testPlantDto;

    @BeforeEach
    void setUp() {
        testPlant = new Plants();
        testPlant.setIdPlante(1);
        testPlant.setNom("Test Plant");
        testPlant.setPointDeVie(100);
        testPlant.setAttaqueParSeconde(new BigDecimal("1.5"));
        testPlant.setDegatAttaque(25);
        testPlant.setCout(100);
        testPlant.setSoleilParSeconde(new BigDecimal("0.5"));
        testPlant.setEffet("normal");
        testPlant.setCheminImage("test.png");

        testPlantDto = new PlantsDto();
        testPlantDto.setId_plante(1);
        testPlantDto.setNom("Test Plant");
        testPlantDto.setPoint_de_vie(100);
        testPlantDto.setAttaque_par_seconde(new BigDecimal("1.5"));
        testPlantDto.setDegat_attaque(25);
        testPlantDto.setCout(100);
        testPlantDto.setSoleil_par_seconde(new BigDecimal("0.5"));
        testPlantDto.setEffet("normal");
        testPlantDto.setChemin_image("test.png");
    }

    @Test
    void getAllPlants_ShouldReturnList() throws Exception {
        when(plantsService.findAll()).thenReturn(Arrays.asList(testPlant));
        when(plantsMapper.toDTO(any(Plants.class))).thenReturn(testPlantDto);

        mockMvc.perform(get("/plantes"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id_plante").value(1))
                .andExpect(jsonPath("$[0].nom").value("Test Plant"));
    }

    @Test
    void getPlantById_WhenExists_ShouldReturnPlant() throws Exception {
        when(plantsService.findById(1)).thenReturn(Optional.of(testPlant));
        when(plantsMapper.toDTO(any(Plants.class))).thenReturn(testPlantDto);

        mockMvc.perform(get("/plantes/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id_plante").value(1));
    }

    @Test
    void getPlantById_WhenNotExists_ShouldReturn404() throws Exception {
        when(plantsService.findById(999)).thenReturn(Optional.empty());

        mockMvc.perform(get("/plantes/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void createPlant_ShouldReturnCreatedPlant() throws Exception {
        when(plantsMapper.toEntity(any(PlantsDto.class))).thenReturn(testPlant);
        when(plantsService.save(any(Plants.class))).thenReturn(testPlant);
        when(plantsMapper.toDTO(any(Plants.class))).thenReturn(testPlantDto);

        mockMvc.perform(post("/plantes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testPlantDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id_plante").value(1));
    }

    @Test
    void updatePlant_WhenExists_ShouldReturnUpdatedPlant() throws Exception {
        when(plantsService.findById(1)).thenReturn(Optional.of(testPlant));
        when(plantsMapper.toEntity(any(PlantsDto.class))).thenReturn(testPlant);
        when(plantsMapper.toDTO(any(Plants.class))).thenReturn(testPlantDto);

        mockMvc.perform(put("/plantes/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testPlantDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id_plante").value(1));
    }

    @Test
    void updatePlant_WhenNotExists_ShouldReturn404() throws Exception {
        when(plantsService.findById(999)).thenReturn(Optional.empty());

        mockMvc.perform(put("/plantes/999")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testPlantDto)))
                .andExpect(status().isNotFound());
    }

    @Test
    void deletePlant_WhenExists_ShouldReturn204() throws Exception {
        when(plantsService.findById(1)).thenReturn(Optional.of(testPlant));

        mockMvc.perform(delete("/plantes/1"))
                .andExpect(status().isNoContent());

        verify(plantsService).delete(1);
    }

    @Test
    void deletePlant_WhenNotExists_ShouldReturn404() throws Exception {
        when(plantsService.findById(999)).thenReturn(Optional.empty());

        mockMvc.perform(delete("/plantes/999"))
                .andExpect(status().isNotFound());

        verify(plantsService, never()).delete(999);
    }
}
*/