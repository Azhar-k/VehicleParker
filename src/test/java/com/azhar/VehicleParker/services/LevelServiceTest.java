package com.azhar.VehicleParker.services;

import com.azhar.VehicleParker.Dao.AllowedVehicleDao;
import com.azhar.VehicleParker.Dao.LevelDao;
import com.azhar.VehicleParker.Dao.VehicleDao;
import com.azhar.VehicleParker.Entities.ApiResponses.LevelResponse;
import com.azhar.VehicleParker.Entities.Exceptions.VehicleNotFound;
import com.azhar.VehicleParker.MockData;
import com.azhar.VehicleParker.services.implimentation.LevelService;
import com.azhar.VehicleParker.db.models.Building.Level;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
    AllowedVehicleDao allowedVehicleDao;
    @Mock
    VehicleDao vehicleDao;

    MockData mockData = new MockData();


    @Nested
    public class testInsertLevel{

        @Test
        public void givenNewLevel() throws VehicleNotFound {
            Level input = mockData.loadLevels().get(0);
            System.out.println(input.getNumber());
            input.setNumber(100);//change level number to a new level number

            when(levelDao.insert(input)).thenReturn(input);
            when(vehicleDao.getVehicleByName(anyString())).thenReturn(mockData.findVehicleByName("car"));

            LevelResponse expected = new LevelResponse(true,"Level added",input);
            LevelResponse actual = levelService.insertLevel(input);

            assertAll(()->{
               assertEquals(expected.getMessage(),actual.getMessage());
               assertEquals(expected.isSucces(),actual.isSucces());
            });

        }

        @Test
        public void givenExistingLevel(){
            Level input = mockData.loadLevels().get(0);

            when(levelDao.getLevelByLevelNumber(0)).thenReturn(mockData.loadLevels().get(0));

            LevelResponse expected = new LevelResponse(false,"level already exist",input);
            LevelResponse actual = levelService.insertLevel(input);

            assertAll(()->{
                assertEquals(expected.getMessage(),actual.getMessage());
                assertEquals(expected.isSucces(),actual.isSucces());
            });

        }
    }

    @Nested
    public class testDeleteLevel{
        @Test
        public void givenNewLevel(){
            Level input = new Level(100);

            when(levelDao.getLevelByLevelNumber(anyInt())).thenReturn(null);

            LevelResponse expected = new LevelResponse(false,"input Level does not exist",input);
            LevelResponse actual = levelService.deleteLevel(input);

            assertAll(()->{
                assertEquals(expected.getMessage(),actual.getMessage());
                assertEquals(expected.isSucces(),actual.isSucces());
            });
        }

        @Test
        @Ignore
        public void givenExistingLevel(){
            Level input = new Level(0);

            when(levelDao.getLevelByLevelNumber(anyInt())).thenReturn(mockData.loadLevels().get(0));

            LevelResponse expected = new LevelResponse(true,"Level deleted",input);
            LevelResponse actual = levelService.deleteLevel(input);

            assertAll(()->{
                assertEquals(expected.getMessage(),actual.getMessage());
                assertEquals(expected.isSucces(),actual.isSucces());
            });
        }
    }

}
