package persistence.dao;

import com.epf.persistence.dao.ZombiesDao;
import com.epf.persistence.model.Zombies;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ZombiesDaoTest {

    @Autowired
    private ZombiesDao zombiesDao;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private Zombies testZombie;

    @BeforeEach
    void setUp() {
        // Clear the table before each test
        jdbcTemplate.update("DELETE FROM zombie");
        
        testZombie = new Zombies();
        testZombie.setNom("Test Zombie");
        testZombie.setPointDeVie(100);
        testZombie.setAttaqueParSeconde(new BigDecimal("1.5"));
        testZombie.setDegatAttaque(25);
        testZombie.setVitesseDeDeplacement(new BigDecimal("0.5"));
        testZombie.setCheminImage("zombie.png");
        testZombie.setIdMap(1);
    }

    @Test
    void findAll_ShouldReturnEmptyList_WhenTableIsEmpty() {
        List<Zombies> zombies = zombiesDao.findAll();
        assertTrue(zombies.isEmpty());
    }

    @Test
    void findAll_ShouldReturnList_WhenTableHasData() {
        // Arrange
        Zombies saved = zombiesDao.save(testZombie);
        
        // Act
        List<Zombies> zombies = zombiesDao.findAll();
        
        // Assert
        assertEquals(1, zombies.size());
        assertEquals(saved.getIdZombie(), zombies.get(0).getIdZombie());
        assertEquals(saved.getNom(), zombies.get(0).getNom());
    }

    @Test
    void findById_ShouldReturnEmpty_WhenIdDoesNotExist() {
        Optional<Zombies> result = zombiesDao.findById(999);
        assertTrue(result.isEmpty());
    }

    @Test
    void findById_ShouldReturnZombie_WhenIdExists() {
        // Arrange
        Zombies saved = zombiesDao.save(testZombie);
        
        // Act
        Optional<Zombies> result = zombiesDao.findById(saved.getIdZombie());
        
        // Assert
        assertTrue(result.isPresent());
        assertEquals(saved.getIdZombie(), result.get().getIdZombie());
        assertEquals(saved.getNom(), result.get().getNom());
    }

    @Test
    void save_ShouldPersistZombie() {
        // Act
        Zombies saved = zombiesDao.save(testZombie);
        
        // Assert
        assertNotNull(saved.getIdZombie());
        assertEquals(testZombie.getNom(), saved.getNom());
        assertEquals(testZombie.getPointDeVie(), saved.getPointDeVie());
    }

    @Test
    void update_ShouldModifyExistingZombie() {
        // Arrange
        Zombies saved = zombiesDao.save(testZombie);
        saved.setNom("Updated Zombie");
        saved.setPointDeVie(200);
        
        // Act
        zombiesDao.update(saved);
        Optional<Zombies> updated = zombiesDao.findById(saved.getIdZombie());
        
        // Assert
        assertTrue(updated.isPresent());
        assertEquals("Updated Zombie", updated.get().getNom());
        assertEquals(200, updated.get().getPointDeVie());
    }

    @Test
    void delete_ShouldRemoveZombie() {
        // Arrange
        Zombies saved = zombiesDao.save(testZombie);
        
        // Act
        zombiesDao.delete(saved.getIdZombie());
        
        // Assert
        Optional<Zombies> deleted = zombiesDao.findById(saved.getIdZombie());
        assertTrue(deleted.isEmpty());
    }
}
