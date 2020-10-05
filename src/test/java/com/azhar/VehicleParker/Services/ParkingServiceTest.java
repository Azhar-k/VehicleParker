package com.azhar.VehicleParker.Services;

import com.azhar.VehicleParker.Dao.Interfaces.LevelDao;
import com.azhar.VehicleParker.Dao.Interfaces.VehicleDao;
import com.azhar.VehicleParker.Database;
import com.azhar.VehicleParker.Entities.ApiResponses.ParkResponse;
import com.azhar.VehicleParker.Entities.LevelParkedVehicle;
import com.azhar.VehicleParker.Entities.Vehicle.Vehicle;
import com.azhar.VehicleParker.Services.Interfaces.ParkingService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ParkingServiceTest {


    @Autowired
    ParkingService parkingService;
    @Autowired
    LevelDao levelDao;
    @Autowired
    VehicleDao vehicleDao;

    @BeforeAll
    public static void init() {
        Database db = new Database();
        db.loadData();
    }

    @Nested
    public class getVehicleTest {
        @Test
        public void GivenInValidType() {
            assertNull(parkingService.getVehicle("jeep"));
        }

        @Test
        public void GivenValidType() {
            assertNotNull(parkingService.getVehicle("car"));
        }
    }


    @Test
    public void getAvailableLevelNumberGivenVehicle() {
        for (Vehicle vehicle : vehicleDao.getVehicleList()) {
            int expected = 0;//all vehicle types except truck has slot in 0th level
            if (vehicle.getName().equals("truck")) {
                //truck has slot in 6th level only
                expected = 6;
            }
            int actual = parkingService.getAvailableLevelNumber(vehicle);
            assertEquals(expected, actual);
        }
    }


    @Nested
    public class parkVehicleTest {
        @Test
        public void GivenInValidVehicle() {
            Vehicle vehicle = new Vehicle("jeep");

            try {
                parkingService.parkVehicle(vehicle);
            } catch (Exception exception) {
                assertEquals("This vehicle can not be parked here", exception.getMessage());
            }
        }

        @ParameterizedTest(name = "vehicle name")
        @CsvSource(value = {"bus", "bike", "van", "truck"})
        public void GivenValidVehicle(String vehicleName) throws Exception {
            LevelParkedVehicle levelVehicle;
            Vehicle vehicle;

            vehicle = new Vehicle(vehicleName);
            levelVehicle = parkingService.parkVehicle(vehicle);
            assertAll(
                    () -> {
                        assertTrue(100 <= levelVehicle.getId() && levelVehicle.getId() < 1000);
                        assertTrue(levelVehicle.getLevelNumber() >= 0);//negative values indicate space is full. can not park vehicle there
                    }
            );


        }

        @Test
        public void GivenSpaceIsfull() {
            Vehicle vehicle = new Vehicle("car");
            try {
                for (int i = 0; i < 50; i++) {
                    //100 slot is not available for car
                    parkingService.parkVehicle(vehicle);
                }
            } catch (Exception exception) {
                assertEquals("Parking Space is Full for " + vehicle.getName(), exception.getMessage());
            }
        }
    }

    @Nested
    public class parkTest {
        @ParameterizedTest(name = "vehicle name")
        @CsvSource(value = {"bus", "bike", "van", "truck"})
        public void GivenValidVehicle(String vehicleName) throws Exception {
            Vehicle vehicle = new Vehicle(vehicleName);

            LevelParkedVehicle levelVehicle = parkingService.parkVehicle(vehicle);
            ParkResponse expected = new ParkResponse(true, "vehicle parked", levelVehicle);
            ParkResponse actual = parkingService.park(vehicle);
            assertAll(()->{
                assertEquals(expected.isSucces(),actual.isSucces());
                assertEquals(expected.getMessage(),actual.getMessage());
            });

        }

    }


}
