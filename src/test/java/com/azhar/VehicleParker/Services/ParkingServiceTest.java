package com.azhar.VehicleParker.Services;

import com.azhar.VehicleParker.Dao.AllowedVehicleDao;
import com.azhar.VehicleParker.Dao.LevelDao;
import com.azhar.VehicleParker.Dao.LevelParkedVehicleDao;
import com.azhar.VehicleParker.Dao.VehicleDao;
import com.azhar.VehicleParker.Database;
import com.azhar.VehicleParker.Entities.ApiResponses.ParkResponse;
import com.azhar.VehicleParker.Entities.Building.AllowedVehicle;
import com.azhar.VehicleParker.Entities.Building.Level;
import com.azhar.VehicleParker.Entities.Building.LevelSpace;
import com.azhar.VehicleParker.Entities.LevelParkedVehicle;
import com.azhar.VehicleParker.Entities.Vehicle.Vehicle;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ParkingServiceTest {


    @Autowired
    ParkingService parkingService;

   @Autowired
    LevelDao levelDao;
   @Autowired
    AllowedVehicleDao allowedVehicleDao;
   @Autowired
    LevelParkedVehicleDao levelParkedVehicleDao;
   @Autowired
    VehicleDao vehicleDao;

   @Autowired
   Database database;


   @Autowired
    SpaceManager spaceManager;

    @BeforeAll
    public static void init() {
        //database.loadData();
    }

    @Nested
    public class getVehicleTest {
        @Test
        public void GivenInValidType() {
            database.loadData();
//            Mockito.when(vehicleDao.getVehicleByName("jeep")).thenReturn(findByName("jeep"));
            assertNull(parkingService.getVehicle("jeep"));
        }

        @Test
        public void GivenValidType() {
//            Mockito.when(vehicleDao.getVehicleByName("car")).thenReturn(findByName("car"));
            assertNotNull(parkingService.getVehicle("car"));
        }
    }


    @ParameterizedTest(name = "vehicle name")
    @CsvSource(value = {"1", "2", "3", "4","5"})
    public void getAvailableLevelNumberGivenVehicle(int vehicleId) {
        database.loadData();
//        Mockito.when(spaceManager.getLAvailableSpace()).thenReturn(getLAvailableSpace());
        Vehicle vehicle = vehicleDao.findById(vehicleId);

        int expected = 0;//all vehicle types except truck has slot in 0th level
        if (vehicle.getName().equals("truck")) {
            //truck has slot in 6th level only
            expected = 6;
        }
        if (vehicle.getName().equals("container")) {
            //container has slot in 7th level only
            expected = 7;
        }
        int actual = parkingService.getAvailableLevelNumber(vehicle);
        assertEquals(expected, actual);

    }


    @Nested
    public class parkVehicleTest {

        @Test
        public void GivenInValidVehicle() {
            database.loadData();
            Vehicle vehicle = new Vehicle("jeep");

//            Mockito.when(vehicleDao.getVehicleByName(vehicle.getName())).thenReturn(findByName(vehicle.getName()));
//            Mockito.when(spaceManager.getLAvailableSpace()).thenReturn(getLAvailableSpace());
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
            Vehicle vehicle = new Vehicle(vehicleName);

//            Mockito.when(vehicleDao.getVehicleByName(vehicle.getName())).thenReturn(findByName(vehicle.getName()));
//            Mockito.when(spaceManager.getLAvailableSpace()).thenReturn(getLAvailableSpace());

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
            database.loadData();
            Vehicle vehicle = new Vehicle(vehicleName);

            LevelParkedVehicle levelVehicle = parkingService.parkVehicle(vehicle);
            ParkResponse expected = new ParkResponse(true, "vehicle parked", levelVehicle);
            ParkResponse actual = parkingService.park(vehicle);
            assertAll(() -> {
                assertEquals(expected.isSucces(), actual.isSucces());
                assertEquals(expected.getMessage(), actual.getMessage());
            });

        }

    }


//    private List<Level> loadLevels() {
//
//        List<Level> levelList = new ArrayList<Level>();
//        for (int i = 0; i < 6; i++) {
//            //all level contains same list of vehicles and free slots
//            Level level = new Level(i);
//            List<AllowedVehicle> allowedVehicles = new ArrayList<AllowedVehicle>();
//            addLevel(allowedVehicles, 0, "car", 5);
//            addLevel(allowedVehicles, 1, "bus", 3);
//            addLevel(allowedVehicles, 2, "van", 4);
//            addLevel(allowedVehicles, 3, "bike", 15);
//            level.setAllowedVehicles(allowedVehicles);
//            levelList.add(level);
//        }
//        //adding extra level where only truck can be parked
//        Level level = new Level(6);
//        List<AllowedVehicle> allowedVehicles = new ArrayList<AllowedVehicle>();
//        addLevel(allowedVehicles, 4, "truck", 4);
//        level.setAllowedVehicles(allowedVehicles);
//        levelList.add(level);
//
//        //adding extra level where bus and container can be parked
//        level = new Level(7);
//        allowedVehicles = new ArrayList<AllowedVehicle>();
//        addLevel(allowedVehicles, 1, "bus", 4);
//        addLevel(allowedVehicles, 5, "container", 3);
//        level.setAllowedVehicles(allowedVehicles);
//        levelList.add(level);
//
//        return levelList;
//    }
//
//    private void addLevel(List<AllowedVehicle> allowedVehicles, int type, String name, int MAX_SLOT) {
//        Vehicle vehicle = findById(type);
//        AllowedVehicle allowedVehicle = new AllowedVehicle(MAX_SLOT, 0, vehicle);
//        allowedVehicles.add(allowedVehicle);
//    }
//
//    private List<Vehicle> loadVehicles() {
//        List<Vehicle> vehicleList = new ArrayList<>();
//        vehicleList.add(new Vehicle(0, "car"));
//        vehicleList.add(new Vehicle(1, "bus"));
//        vehicleList.add(new Vehicle(2, "van"));
//        vehicleList.add(new Vehicle(3, "bike"));
//        vehicleList.add(new Vehicle(4, "truck"));
//        vehicleList.add(new Vehicle(5, "container"));
//        return vehicleList;
//    }
//
//    private Vehicle findById(int type) {
//        List<Vehicle> vehicleList = loadVehicles();
//        for (Vehicle vehicle : vehicleList) {
//            if (vehicle.getId() == type) {
//                return vehicle;
//            }
//        }
//        return null;
//    }
//
//    private Vehicle findByName(String name) {
//        List<Vehicle> vehicleList = loadVehicles();
//        for (Vehicle vehicle : vehicleDao.getVehicleList()) {
//            if (vehicle.getName().equals(name)) {
//                return vehicle;
//            }
//        }
//        return null;
//    }
//
//    private List<LevelSpace> getLAvailableSpace() {
//        List<LevelSpace> availableSpace = new ArrayList<LevelSpace>();
//        for (Level level : loadLevels()) {
//            LevelSpace levelSpace = new LevelSpace(level.getLevelNumber());
//            for (AllowedVehicle allowedVehicle : level.getAllowedVehicles()) {
//                int freeSlot = allowedVehicle.getFreeSlots();
//                levelSpace.getAvailabeSlots().put(allowedVehicle.getVehicle().getName(), freeSlot);
//
//            }
//            availableSpace.add(levelSpace);
//
//        }
//        return availableSpace;
//    }


}
