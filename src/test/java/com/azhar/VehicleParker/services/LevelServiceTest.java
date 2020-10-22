package com.azhar.VehicleParker.services;

import com.azhar.VehicleParker.Dao.LevelDao;
import com.azhar.VehicleParker.Dao.VehicleDao;
import com.azhar.VehicleParker.Entities.Exceptions.LevelException;
import com.azhar.VehicleParker.MockData;
import com.azhar.VehicleParker.services.implimentation.LevelService;
import com.azhar.VehicleParker.db.models.Building.Level;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

//Not implimented.
@SpringBootTest
public class LevelServiceTest {
    @InjectMocks
    LevelService levelService;

    @Mock
    LevelDao levelDao;
    @Mock
    VehicleDao vehicleDao;

    MockData mockData = new MockData();


    @Nested
    public class testInsertLevel{

        @Test
        public void givenNewLevel() throws LevelException {
            Level input = mockData.loadLevels().get(0);

            input.setNumber(100);//change level number to a new level number

            when(levelDao.insert(input)).thenReturn(input);
            when(vehicleDao.getByName(anyString())).thenReturn(mockData.findVehicleByName("car"));

            Level expected = input;
            Level actual = levelService.insertLevel(input);

            assertEquals(expected.getNumber(),actual.getNumber());

        }

        @Test
        public void givenExistingLevel() {
            Level input = mockData.loadLevels().get(0);

            when(levelDao.getByNumber(0)).thenReturn(mockData.loadLevels().get(0));

            LevelException levelException = assertThrows(LevelException.class,()->{
                levelService.insertLevel(input);
            });
            assertEquals("level already exist",levelException.getMessage());
        }
    }

    @Nested
    public class testDeleteLevel{
        @Test
        public void givenNewLevel() {
            Level input = new Level(100);

            when(levelDao.getByNumber(anyInt())).thenReturn(null);

            LevelException levelException = assertThrows(LevelException.class,()->{
                levelService.deleteLevel(input);
            });
            assertEquals("input Level does not exist",levelException.getMessage());



        }

        @Test
        public void givenExistingLevel() throws LevelException {
            Level input = new Level(0);

            when(levelDao.getByNumber(anyInt())).thenReturn(mockData.loadLevels().get(0));

            boolean actual = levelService.deleteLevel(input);

            assertTrue(actual);

        }
    }

}
