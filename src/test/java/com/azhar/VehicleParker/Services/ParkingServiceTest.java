package com.azhar.VehicleParker.Services;

import com.azhar.VehicleParker.Dao.LevelDao;
import com.azhar.VehicleParker.Database;
import com.azhar.VehicleParker.Entities.ApiResponses.ParkResponse;
import com.azhar.VehicleParker.Entities.Building.LevelSpace;
import com.azhar.VehicleParker.Entities.LevelVehicle;
import com.azhar.VehicleParker.Entities.Vehicle.Vehicle;
import com.azhar.VehicleParker.Services.Interfaces.ParkingService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ParkingServiceTest {



    @Autowired
    ParkingService parkingService;
    @Autowired
    LevelDao levelDao;

    @BeforeAll
    public static void init(){
        Database db = new Database();
        db.loadData();
    }

    @Test
    public void getVehicleTypeGivenInValidType() {
        assertNull(parkingService.getVehicle("jeep"));
    }

    @Test
    public void getVehicleTypeGivenValidType() {
        //db.loadData();
        assertNotNull(parkingService.getVehicle("car"));
    }

    @Test
    public void getAvailableLevelNumberGivenVehicle() {
        for (Vehicle vehicle : levelDao.getVehicleList()) {
            int expected = 0;//all vehicle types except truck has slot in oth level
            if (vehicle.getName().equals("truck")) {
                //truck has no slot in 6th level
                expected = 6;
            }
            int actual = parkingService.getAvailableLevelNumber(vehicle);
            assertEquals(expected, actual);
        }
    }

    @Test
    public void parkVehicleGivenInValidVehicle() {
        Vehicle vehicle = new Vehicle("jeep");

        try {
            parkingService.parkVehicle(vehicle);
        } catch (Exception exception) {
            assertEquals("This vehicle can not be parked here", exception.getMessage());
        }
    }

    @Test
    public void parkVehicleGivenValidVehicle() throws Exception {
        LevelVehicle levelVehicle;
        Vehicle vehicle;

        vehicle = new Vehicle("car");
        levelVehicle = parkingService.parkVehicle(vehicle);
        assertTrue(100 <= levelVehicle.getId() && levelVehicle.getId() < 1000);

        vehicle = new Vehicle("truck");
        levelVehicle = parkingService.parkVehicle(vehicle);
        assertTrue(100 <= levelVehicle.getId() && levelVehicle.getId() < 1000);

    }

    @Test
    public void parkVehicleGivenSpaceIsfull() {
        Vehicle vehicle = new Vehicle("car");
        try {
            for (int i = 0; i < 100; i++) {
                //100 slot is not available for car
                parkingService.parkVehicle(vehicle);
            }
        } catch (Exception exception) {
            assertEquals("Parking Space is Full for " + vehicle.getName(), exception.getMessage());
        }
    }

}
