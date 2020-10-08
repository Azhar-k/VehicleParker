package com.azhar.VehicleParker.UnitTests.Services;

import com.azhar.VehicleParker.Dao.AllowedVehicleDao;
import com.azhar.VehicleParker.Dao.LevelDao;
import com.azhar.VehicleParker.Dao.LevelParkedVehicleDao;
import com.azhar.VehicleParker.Entities.ApiResponses.ParkResponse;
import com.azhar.VehicleParker.UnitTests.Services.implimentation.UnParkingService;
import com.azhar.VehicleParker.db.models.Building.AllowedVehicle;
import com.azhar.VehicleParker.db.models.Building.Level;
import com.azhar.VehicleParker.db.models.LevelParkedVehicle;
import com.azhar.VehicleParker.db.models.Vehicle.Vehicle;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UnParckingServiceTest {

    @InjectMocks
    UnParkingService unParkingService;

    @Mock
    LevelParkedVehicleDao levelParkedVehicleDao;
    @Mock
    LevelDao levelDao;
    @Mock
    AllowedVehicleDao allowedVehicleDao;

    @BeforeAll
    public static void init() {

    }

    @Nested
    public class GetValidLevelParkedVehicleTest {
        @Test
        public void givenINvalidId() {

            assertNull(unParkingService.getValidLevelParkedVehicle(1000));
        }

        @Test
        public void givenValidId() throws Exception {
            LevelParkedVehicle expected = new LevelParkedVehicle(0, 0, "car", "xx xx xx xxxx");
            LevelParkedVehicle input = new LevelParkedVehicle();
            when(levelParkedVehicleDao.getLevelParkedVehicleById(input.getId())).thenReturn(expected);
            LevelParkedVehicle actual = unParkingService.getValidLevelParkedVehicle(input.getId());
            assertEquals(expected, actual);
        }
    }

    @Nested
    public class EmptySlot {
        @Test
        public void emptySlot() {
            Level level = new Level(0);
            Vehicle vehicle = new Vehicle(0, "car", 20);
            AllowedVehicle allowedVehicle = new AllowedVehicle(15, 0, vehicle);
            List<AllowedVehicle> allowedVehicleList = new ArrayList<AllowedVehicle>();
            allowedVehicleList.add(allowedVehicle);
            level.setAllowedVehicles(allowedVehicleList);

            when(levelDao.getLevelByLevelNumber(0)).thenReturn(level);
            when(allowedVehicleDao.update(allowedVehicle)).thenReturn(allowedVehicle);

            assertTrue(unParkingService.emptySlot(new LevelParkedVehicle(10)));
        }
    }

    @Nested
    public class UnparkVehicle {
        @Test
        public void givenInvalidLevelVehicle() {
            LevelParkedVehicle input = new LevelParkedVehicle(1000);
            when(levelParkedVehicleDao.getLevelParkedVehicleById(input.getId())).thenReturn(null);
            try {
                unParkingService.unParkVehicle(input);
            } catch (Exception e) {
                assertEquals("This vehicle is not parked here", e.getMessage());
            }

        }

        @Test
        public void givenValidLevelVehicle() throws Exception {
            LevelParkedVehicle input = new LevelParkedVehicle(100);
            LevelParkedVehicle expected = new LevelParkedVehicle(0, 0, "car", "xx xx xx xxxx");
            Level level = new Level(0);
            Vehicle vehicle = new Vehicle(0, "car", 20);
            AllowedVehicle allowedVehicle = new AllowedVehicle(15, 0, vehicle);
            List<AllowedVehicle> allowedVehicleList = new ArrayList<AllowedVehicle>();
            allowedVehicleList.add(allowedVehicle);
            level.setAllowedVehicles(allowedVehicleList);

            when(levelParkedVehicleDao.getLevelParkedVehicleById(input.getId())).thenReturn(expected);
            when(levelDao.getLevelByLevelNumber(0)).thenReturn(level);
            when(allowedVehicleDao.update(allowedVehicle)).thenReturn(allowedVehicle);

            LevelParkedVehicle actual = unParkingService.unParkVehicle(input);

            assertAll(() -> {
                assertEquals(expected.getId(), actual.getId());
                assertEquals(expected.getLevelNumber(), actual.getLevelNumber());
                assertEquals(expected.getDate(), actual.getDate());
                assertEquals(expected.getTime(), actual.getTime());
                assertEquals(expected.getVehicleName(), actual.getVehicleName());
                assertEquals(expected.getVehicleNumber(), actual.getVehicleNumber());
            });


        }
    }

    @Nested
    public class ParkTest {
        @Test
        public void givenInvalidLevelVehicle() {
            //no level vehicle map has id as 1000. id is 3 digit
            LevelParkedVehicle levelVehicle = new LevelParkedVehicle(1000);
            ParkResponse expected = new ParkResponse(false, "This vehicle is not parked here", null);
            ParkResponse actual = unParkingService.unPark(levelVehicle);
            assertAll(() -> {
                assertEquals(expected.getMessage(), actual.getMessage());
                assertEquals(expected.isSucces(), actual.isSucces());
                assertEquals(expected.getVehicleMap(), actual.getVehicleMap());
            });

        }

        @Test
        public void givenValidLevelVehicle() throws Exception {
            LevelParkedVehicle input = new LevelParkedVehicle(100);
            Level level = new Level(0);
            Vehicle vehicle = new Vehicle(0, "car", 20);
            AllowedVehicle allowedVehicle = new AllowedVehicle(15, 0, vehicle);

            List<AllowedVehicle> allowedVehicleList = new ArrayList<AllowedVehicle>();
            allowedVehicleList.add(allowedVehicle);
            level.setAllowedVehicles(allowedVehicleList);

            when(levelParkedVehicleDao.getLevelParkedVehicleById(input.getId())).thenReturn(input);
            when(levelDao.getLevelByLevelNumber(0)).thenReturn(level);
            when(allowedVehicleDao.update(allowedVehicle)).thenReturn(allowedVehicle);

            ParkResponse actual = unParkingService.unPark(input);
            System.out.println(actual.getMessage());
            ParkResponse expected = new ParkResponse(true, "vehicle unparked", input);
            assertAll(() -> {
                assertEquals(expected.isSucces(), actual.isSucces());
                assertEquals(expected.getMessage(), actual.getMessage());
                assertEquals(expected.getVehicleMap(), actual.getVehicleMap());
            });

        }
    }
}
