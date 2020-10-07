package com.azhar.VehicleParker.Services;

import com.azhar.VehicleParker.Dao.AllowedVehicleDao;
import com.azhar.VehicleParker.Dao.LevelDao;
import com.azhar.VehicleParker.Dao.LevelParkedVehicleDao;
import com.azhar.VehicleParker.Dao.VehicleDao;
import com.azhar.VehicleParker.Entities.ApiRequests.ParkRequest;
import com.azhar.VehicleParker.Entities.ApiResponses.ParkResponse;
import com.azhar.VehicleParker.Entities.LevelSpace;
import com.azhar.VehicleParker.Services.implimentation.ParkingService;

import com.azhar.VehicleParker.db.models.Building.AllowedVehicle;
import com.azhar.VehicleParker.db.models.Building.Level;
import com.azhar.VehicleParker.db.models.LevelParkedVehicle;
import com.azhar.VehicleParker.db.models.Vehicle.Vehicle;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ParkingServiceTest {


    @InjectMocks
    ParkingService parkingService;

    @Mock
    LevelDao levelDao;
    @Mock
    AllowedVehicleDao allowedVehicleDao;
    @Mock
    LevelParkedVehicleDao levelParkedVehicleDao;
    @Mock
    VehicleDao vehicleDao;
    @Mock
    SpaceManager spaceManager;

    @BeforeAll
    public static void init() {

    }

    @Nested
    public class getVehicleTest {
        @Test
        public void GivenInValidType() {

            Mockito.when(vehicleDao.getVehicleByName("jeep")).thenReturn(findVehicleByName("jeep"));
            assertNull(parkingService.getVehicle("jeep"));
        }

        @Test
        public void GivenValidType() {
            Mockito.when(vehicleDao.getVehicleByName("car")).thenReturn(findVehicleByName("car"));
            assertNotNull(parkingService.getVehicle("car"));
        }
    }


    @ParameterizedTest(name = "vehicle name")
    @CsvSource(value = {"bus", "van", "bike", "container", "truck", "container"})
    public void getAvailableLevelNumberGivenVehicle(String vehicleName) {

        Mockito.when(spaceManager.getLAvailableSpace()).thenReturn(getLAvailableSpace());
        Vehicle vehicle = findVehicleByName(vehicleName);

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
            String vehicleName="jeep";
            String vehicleNumber="kl 11 ac 5978";
            ParkRequest parkRequest=new ParkRequest(vehicleName,vehicleNumber);

            Mockito.when(vehicleDao.getVehicleByName(vehicleName)).thenReturn(findVehicleByName(vehicleName));
            Mockito.when(spaceManager.getLAvailableSpace()).thenReturn(getLAvailableSpace());
            try {
                parkingService.parkVehicle(parkRequest);
            } catch (Exception exception) {
                assertEquals("This vehicle can not be parked here", exception.getMessage());
            }
        }

        @ParameterizedTest(name = "vehicle name")
        @CsvSource(value = {"bus", "bike", "van", "truck"})
        public void GivenValidVehicle(String vehicleName) throws Exception {
            String vehicleNumber="kl 11 ac 5978";

            ParkRequest parkRequest=new ParkRequest(vehicleName,vehicleNumber);

            Mockito.when(vehicleDao.getVehicleByName(vehicleName)).thenReturn(findVehicleByName(vehicleName));
            Mockito.when(spaceManager.getLAvailableSpace()).thenReturn(getLAvailableSpace());
            Mockito.when(levelDao.getLevelByLevelNumber(0)).thenReturn(loadLevels().get(0));
            Mockito.when(levelDao.getLevelByLevelNumber(6)).thenReturn(loadLevels().get(6));


            LevelParkedVehicle actual= parkingService.parkVehicle(parkRequest);
            assertAll(
                    () -> {
                        assertTrue(actual.getId() < 1000);
                        assertTrue(actual.getLevelNumber() >= 0);//negative values indicate space is full. can not park vehicle there
                        ;
                    }
            );
        }

        @Test
        public void GivenSpaceIsfull() {
            String vehicleName="car";
            String vehicleNumber="xxxx";
            ParkRequest parkRequest=new ParkRequest(vehicleName,vehicleNumber);

            Mockito.when(vehicleDao.getVehicleByName(vehicleName)).thenReturn(findVehicleByName(vehicleName));
            Mockito.when(spaceManager.getLAvailableSpace()).thenReturn(getLAvailableSpace());
            Mockito.when(levelDao.getLevelByLevelNumber(0)).thenReturn(loadLevels().get(0));
            try {
                for (int i = 0; i < 50; i++) {
                    //100 slot is not available for car
                    parkingService.parkVehicle(parkRequest);
                }
            } catch (Exception exception) {
                assertEquals("Parking Space is Full for " +vehicleName , exception.getMessage());
            }
        }
    }

    @Nested
    public class parkTest {
        @ParameterizedTest(name = "vehicle name")
        @CsvSource(value = {"bus", "bike", "van", "truck"})
        public void GivenValidVehicle(String vehicleName) throws Exception {
            String vehicleNumber="kl 11 ac 5978";

            ParkRequest parkRequest=new ParkRequest(vehicleName,vehicleNumber);

            Mockito.when(vehicleDao.getVehicleByName(vehicleName)).thenReturn(findVehicleByName(vehicleName));
            Mockito.when(spaceManager.getLAvailableSpace()).thenReturn(getLAvailableSpace());
            Mockito.when(levelDao.getLevelByLevelNumber(0)).thenReturn(loadLevels().get(0));
            Mockito.when(levelDao.getLevelByLevelNumber(6)).thenReturn(loadLevels().get(6));

            ParkResponse actual = parkingService.park(parkRequest);
            ParkResponse expected = new ParkResponse(true,"vehicle parked",null);
            assertAll(() -> {
                assertEquals(expected.isSucces(), actual.isSucces());
                assertEquals(expected.getMessage(), actual.getMessage());
            });

        }

    }


    //Loading mockdata for testing
    private List<Level> loadLevels() {

        List<Level> levelList = new ArrayList<Level>();
        for (int i = 0; i < 6; i++) {
            //all level contains same list of vehicles and free slots
            Level level = new Level(i);
            List<AllowedVehicle> allowedVehicles = new ArrayList<AllowedVehicle>();
            addLevel(allowedVehicles, 0, "car", 5);
            addLevel(allowedVehicles, 1, "bus", 3);
            addLevel(allowedVehicles, 2, "van", 4);
            addLevel(allowedVehicles, 3, "bike", 15);
            level.setAllowedVehicles(allowedVehicles);
            levelList.add(level);
        }
        //adding extra level where only truck can be parked
        Level level = new Level(6);
        List<AllowedVehicle> allowedVehicles = new ArrayList<AllowedVehicle>();
        addLevel(allowedVehicles, 4, "truck", 4);
        level.setAllowedVehicles(allowedVehicles);
        levelList.add(level);

        //adding extra level where bus and container can be parked
        level = new Level(7);
        allowedVehicles = new ArrayList<AllowedVehicle>();
        addLevel(allowedVehicles, 1, "bus", 4);
        addLevel(allowedVehicles, 5, "container", 3);
        level.setAllowedVehicles(allowedVehicles);
        levelList.add(level);

        return levelList;
    }

    private void addLevel(List<AllowedVehicle> allowedVehicles, int type, String name, int MAX_SLOT) {
        Vehicle vehicle = findVehicleById(type);
        AllowedVehicle allowedVehicle = new AllowedVehicle(MAX_SLOT, 0, vehicle);
        allowedVehicles.add(allowedVehicle);
    }

    private List<Vehicle> loadVehicles() {
        List<Vehicle> vehicleList = new ArrayList<>();
        vehicleList.add(new Vehicle(0, "car", 20));
        vehicleList.add(new Vehicle(1, "bus", 40));
        vehicleList.add(new Vehicle(2, "van", 20));
        vehicleList.add(new Vehicle(3, "bike", 10));
        vehicleList.add(new Vehicle(4, "truck", 70));
        vehicleList.add(new Vehicle(5, "container", 100));
        return vehicleList;
    }

    private Vehicle findVehicleById(int type) {
        List<Vehicle> vehicleList = loadVehicles();
        for (Vehicle vehicle : vehicleList) {
            if (vehicle.getId() == type) {
                return vehicle;
            }
        }
        return null;
    }

    private Vehicle findVehicleByName(String name) {
        List<Vehicle> vehicleList = loadVehicles();
        for (Vehicle vehicle : vehicleList) {
            if (vehicle.getName().equals(name)) {
                return vehicle;
            }
        }
        return null;
    }

    private List<LevelSpace> getLAvailableSpace() {
        List<LevelSpace> availableSpace = new ArrayList<LevelSpace>();
        for (Level level : loadLevels()) {
            LevelSpace levelSpace = new LevelSpace(level.getLevelNumber());
            for (AllowedVehicle allowedVehicle : level.getAllowedVehicles()) {
                int freeSlot = allowedVehicle.getFreeSlots();
                levelSpace.getAvailabeSlots().put(allowedVehicle.getVehicle().getName(), freeSlot);

            }
            availableSpace.add(levelSpace);

        }
        return availableSpace;
    }


}
