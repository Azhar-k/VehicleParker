package com.azhar.VehicleParker.Services;

import com.azhar.VehicleParker.Dao.AllowedVehicleDao;
import com.azhar.VehicleParker.Dao.LevelDao;
import com.azhar.VehicleParker.Dao.LevelParkedVehicleDao;
import com.azhar.VehicleParker.Dao.VehicleDao;
import com.azhar.VehicleParker.Entities.ApiRequests.ParkRequest;
import com.azhar.VehicleParker.Entities.ApiResponses.ParkResponse;
import com.azhar.VehicleParker.Entities.LevelSpace;
import com.azhar.VehicleParker.MockData;
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

    MockData mockData = new MockData();

    @BeforeAll
    public static void init() {

    }

    @Nested
    public class getVehicleTest {
        @Test
        public void GivenInValidType() {

            Mockito.when(vehicleDao.getVehicleByName("jeep")).thenReturn(mockData.findVehicleByName("jeep"));
            assertNull(parkingService.getVehicleByName("jeep"));
        }

        @Test
        public void GivenValidType() {
            Mockito.when(vehicleDao.getVehicleByName("car")).thenReturn(mockData.findVehicleByName("car"));
            assertNotNull(parkingService.getVehicleByName("car"));
        }
    }


    @ParameterizedTest(name = "vehicle name")
    @CsvSource(value = {"bus", "van", "bike", "container", "truck", "container"})
    public void getAvailableLevelNumberGivenVehicle(String vehicleName) {

        Mockito.when(spaceManager.getLAvailableSpace()).thenReturn(mockData.getLAvailableSpace());
        Vehicle vehicle = mockData.findVehicleByName(vehicleName);

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

            Mockito.when(vehicleDao.getVehicleByName(vehicleName)).thenReturn(mockData.findVehicleByName(vehicleName));
            Mockito.when(spaceManager.getLAvailableSpace()).thenReturn(mockData.getLAvailableSpace());
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

            Mockito.when(vehicleDao.getVehicleByName(vehicleName)).thenReturn(mockData.findVehicleByName(vehicleName));
            Mockito.when(spaceManager.getLAvailableSpace()).thenReturn(mockData.getLAvailableSpace());
            Mockito.when(levelDao.getLevelByLevelNumber(0)).thenReturn(mockData.loadLevels().get(0));
            Mockito.when(levelDao.getLevelByLevelNumber(6)).thenReturn(mockData.loadLevels().get(6));


            LevelParkedVehicle actual= parkingService.parkVehicle(parkRequest);
            assertAll(
                    () -> {
                        assertTrue(actual.getId() < 1000);
                        assertTrue(actual.getLevelNumber() >= 0);//negative values indicate space is full. can not park vehicle there
                    }
            );
        }

        @Test
        public void GivenSpaceIsfull() {
            String vehicleName="car";
            String vehicleNumber="xxxx";
            ParkRequest parkRequest=new ParkRequest(vehicleName,vehicleNumber);

            Mockito.when(vehicleDao.getVehicleByName(vehicleName)).thenReturn(mockData.findVehicleByName(vehicleName));
            Mockito.when(spaceManager.getLAvailableSpace()).thenReturn(mockData.getLAvailableSpace());
            Mockito.when(levelDao.getLevelByLevelNumber(0)).thenReturn(mockData.loadLevels().get(0));
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

            Mockito.when(vehicleDao.getVehicleByName(vehicleName)).thenReturn(mockData.findVehicleByName(vehicleName));
            Mockito.when(spaceManager.getLAvailableSpace()).thenReturn(mockData.getLAvailableSpace());
            Mockito.when(levelDao.getLevelByLevelNumber(0)).thenReturn(mockData.loadLevels().get(0));
            Mockito.when(levelDao.getLevelByLevelNumber(6)).thenReturn(mockData.loadLevels().get(6));

            ParkResponse actual = parkingService.park(parkRequest);
            ParkResponse expected = new ParkResponse(true,"vehicle parked",null);
            assertAll(() -> {
                assertEquals(expected.isSucces(), actual.isSucces());
                assertEquals(expected.getMessage(), actual.getMessage());
            });

        }

    }


}
