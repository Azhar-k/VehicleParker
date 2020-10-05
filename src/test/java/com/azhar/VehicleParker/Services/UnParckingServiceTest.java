package com.azhar.VehicleParker.Services;

import com.azhar.VehicleParker.Dao.LevelDao;
import com.azhar.VehicleParker.Database;
import com.azhar.VehicleParker.Entities.ApiRequests.ParkRequest;
import com.azhar.VehicleParker.Entities.ApiResponses.ParkResponse;
import com.azhar.VehicleParker.Entities.LevelParkedVehicle;
import com.azhar.VehicleParker.Entities.Vehicle.Vehicle;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UnParckingServiceTest {

    @Autowired
    UnParkingService unParkingService;
    @Autowired
    ParkingService parkingService;
    @Autowired
    LevelDao levelDao;

    @BeforeAll
    public static void init(){
        Database database = new Database();
        database.loadData();
    }

    @Nested
    public class GetValidLevelParkedVehicleTest {
        @Test
        public void givenINvalidId(){
            //no level vehicle map has id as 1000. id is 3 digit
            assertNull(unParkingService.getValidLevelParkedVehicle(1000));
        }

        @Test
        public void givenValidId() throws Exception {
            LevelParkedVehicle expected = parkingService.parkVehicle(new ParkRequest());
            LevelParkedVehicle actual = unParkingService.getValidLevelParkedVehicle(expected.getId());
            assertEquals(expected,actual);
        }
    }

    @Nested
    public class UnparkVehicle{
        @Test
        public void givenInvalidLevelVehicle(){
            //no level vehicle map has id as 1000. id is 3 digit
            LevelParkedVehicle levelVehicle = new LevelParkedVehicle(1000);
            try {
                unParkingService.unParkVehicle(levelVehicle);
            } catch (Exception e) {
                assertEquals("This vehicle is not parked here",e.getMessage());
            }

        }

        @Test
        public void givenValidLevelVehicle() throws Exception {

            LevelParkedVehicle expected = parkingService.parkVehicle(new ParkRequest());
            LevelParkedVehicle actual = unParkingService.unParkVehicle(expected);
            assertEquals(expected,actual);

        }
    }

    @Nested
    public class ParkTest{
        @Test
        public void givenInvalidLevelVehicle(){
            //no level vehicle map has id as 1000. id is 3 digit
            LevelParkedVehicle levelVehicle = new LevelParkedVehicle(1000);
            ParkResponse expected = new ParkResponse(false,"This vehicle is not parked here",null);
            ParkResponse actual = unParkingService.unPark(levelVehicle);
            assertAll(()->{
                assertEquals(expected.getMessage(),actual.getMessage());
                assertEquals(expected.isSucces(),actual.isSucces());
                assertEquals(expected.getVehicleMap(),actual.getVehicleMap());
            });

        }

        @Test
        public void givenValidLevelVehicle() throws Exception {
            LevelParkedVehicle levelVehicle = parkingService.parkVehicle(new ParkRequest());
            ParkResponse actual = unParkingService.unPark(levelVehicle);
            ParkResponse expected = new ParkResponse(true,"vehicle unparked",levelVehicle);
            assertAll(()->{
                assertEquals(expected.isSucces(),actual.isSucces());
                assertEquals(expected.getMessage(),actual.getMessage());
                assertEquals(expected.getVehicleMap(),actual.getVehicleMap());
            });

        }
    }
}
