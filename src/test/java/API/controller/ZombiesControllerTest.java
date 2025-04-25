/*package API.controller;

import com.epf.API.controller.ZombiesController;
import com.epf.API.dto.ZombiesDto;
import com.epf.API.mapper.ZombiesMapper;
import com.epf.core.services.ZombiesServices;
import com.epf.persistence.model.Zombies;
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

@WebMvcTest(ZombiesController.class)
class ZombiesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ZombiesServices zombiesService;

    @MockBean
    private ZombiesMapper zombiesMapper;

    @Autowired
    private ObjectMapper objectMapper;

    private Zombies testZombie;
    private ZombiesDto testZombieDto;

    @BeforeEach
    void setUp() {
        testZombie = new Zombies();
        testZombie.setIdZombie(1);
        testZombie.setNom("Test Zombie");
        testZombie.setPointDeVie(100);
        testZombie.setAttaqueParSeconde(new BigDecimal("1.5"));
        testZombie.setDegatAttaque(25);
        testZombie.setVitesseDeDeplacement(new BigDecimal("0.5"));
        testZombie.setCheminImage("zombie.png");
        testZombie.setIdMap(1);

        testZombieDto = new ZombiesDto();
        testZombieDto.setId_zombie(1);
        testZombieDto.setNom("Test Zombie");
        testZombieDto.setPoint_de_vie(100);
        testZombieDto.setAttaque_par_seconde(new BigDecimal("1.5"));
        testZombieDto.setDegat_attaque(25);
        testZombieDto.setVitesse_de_deplacement(new BigDecimal("0.5"));
        testZombieDto.setChemin_image("zombie.png");
        testZombieDto.setId_map(1);
    }

    @Test
    void getAllZombies_ShouldReturnList() throws Exception {
        when(zombiesService.findAll()).thenReturn(Arrays.asList(testZombie));
        when(zombiesMapper.toDto(any(Zombies.class))).thenReturn(testZombieDto);

        mockMvc.perform(get("/zombies"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id_zombie").value(1))
                .andExpect(jsonPath("$[0].nom").value("Test Zombie"));
    }

    @Test
    void getZombieById_WhenExists_ShouldReturnZombie() throws Exception {
        when(zombiesService.findById(1)).thenReturn(Optional.of(testZombie));
        when(zombiesMapper.toDto(any(Zombies.class))).thenReturn(testZombieDto);

        mockMvc.perform(get("/zombies/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id_zombie").value(1));
    }

    @Test
    void getZombieById_WhenNotExists_ShouldReturn404() throws Exception {
        when(zombiesService.findById(999)).thenReturn(Optional.empty());

        mockMvc.perform(get("/zombies/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void createZombie_ShouldReturnCreatedZombie() throws Exception {
        when(zombiesMapper.toModel(any(ZombiesDto.class))).thenReturn(testZombie);
        when(zombiesService.save(any(Zombies.class))).thenReturn(testZombie);
        when(zombiesMapper.toDto(any(Zombies.class))).thenReturn(testZombieDto);

        mockMvc.perform(post("/zombies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testZombieDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id_zombie").value(1));
    }

    @Test
    void updateZombie_WhenExists_ShouldReturnUpdatedZombie() throws Exception {
        when(zombiesService.findById(1)).thenReturn(Optional.of(testZombie));
        when(zombiesMapper.toModel(any(ZombiesDto.class))).thenReturn(testZombie);
        when(zombiesService.save(any(Zombies.class))).thenReturn(testZombie);
        when(zombiesMapper.toDto(any(Zombies.class))).thenReturn(testZombieDto);

        mockMvc.perform(put("/zombies/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testZombieDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id_zombie").value(1));
    }

    @Test
    void updateZombie_WhenNotExists_ShouldReturn404() throws Exception {
        when(zombiesService.findById(999)).thenReturn(Optional.empty());

        mockMvc.perform(put("/zombies/999")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testZombieDto)))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteZombie_WhenExists_ShouldReturn200() throws Exception {
        when(zombiesService.findById(1)).thenReturn(Optional.of(testZombie));
        doNothing().when(zombiesService).deleteById(1);

        mockMvc.perform(delete("/zombies/1"))
                .andExpect(status().isOk());
    }

    @Test
    void deleteZombie_WhenNotExists_ShouldReturn404() throws Exception {
        when(zombiesService.findById(999)).thenReturn(Optional.empty());

        mockMvc.perform(delete("/zombies/999"))
                .andExpect(status().isNotFound());
    }
} */
