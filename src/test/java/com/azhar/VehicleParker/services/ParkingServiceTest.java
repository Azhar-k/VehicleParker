package com.azhar.VehicleParker.services;

import com.azhar.VehicleParker.Dao.AllowedVehicleDao;
import com.azhar.VehicleParker.Dao.LevelDao;
import com.azhar.VehicleParker.Dao.LevelParkedVehicleDao;
import com.azhar.VehicleParker.Dao.VehicleDao;
import com.azhar.VehicleParker.Entities.ApiRequests.ParkRequest;
import com.azhar.VehicleParker.Entities.Exceptions.InvalidInputException;
import com.azhar.VehicleParker.Entities.Exceptions.ParkingException;
import com.azhar.VehicleParker.Entities.LevelSpace;
import com.azhar.VehicleParker.MockData;
import com.azhar.VehicleParker.services.implimentation.ParkingService;

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

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ParkingServiceTest {


    @InjectMocks
    ParkingService parkingService;
    @Mock
    VehicleDao vehicleDao;
    @Mock
    LevelDao levelDao;
    @Mock
    SpaceManager spaceManager;
    @Mock
    AllowedVehicleDao allowedVehicleDao;
    @Mock
    LevelParkedVehicleDao levelParkedVehicleDao;
    MockData mockData = new MockData();

    @BeforeAll
    public static void init() {

    }

    @Nested
    public class getVehicleTest {
        @Test
        public void GivenInValidType() throws Exception {

            Mockito.when(vehicleDao.getByName("jeep")).thenReturn(mockData.findVehicleByName("jeep"));
            assertNull(parkingService.getVehicleByName("jeep"));
        }

        @Test
        public void GivenValidType() throws Exception {
            Mockito.when(vehicleDao.getByName("car")).thenReturn(mockData.findVehicleByName("car"));
            assertNotNull(parkingService.getVehicleByName("car"));
        }
    }


    @ParameterizedTest(name = "vehicle name")
    @CsvSource(value = {"bus", "van", "bike", "container", "truck", "container"})
    public void getAvailableLevelNumberGivenVehicle(String vehicleName) throws InvalidInputException {

        Mockito.when(spaceManager.getAvailableSpaceList()).thenReturn(mockData.getLAvailableSpace());
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
            String vehicleName = "jeep";
            String vehicleNumber = "kl 11 ac 5978";
            ParkRequest parkRequest = new ParkRequest(vehicleName, vehicleNumber);

            Mockito.when(vehicleDao.getByName(vehicleName)).thenReturn(mockData.findVehicleByName(vehicleName));
            Mockito.when(spaceManager.getAvailableSpaceList()).thenReturn(mockData.getLAvailableSpace());
            ParkingException parkingException = assertThrows(ParkingException.class, () -> {
                parkingService.parkVehicle(parkRequest);
            });
            assertEquals("this vehicle can not be parked here", parkingException.getMessage());

        }

        @ParameterizedTest(name = "vehicle name")
        @CsvSource(value = {"bus", "bike", "van", "truck"})
        public void GivenValidVehicle(String vehicleName) throws ParkingException {
            String vehicleNumber = "kl 11 ac 5978 " + vehicleName;
            ParkRequest parkRequest = new ParkRequest(vehicleName, vehicleNumber);

            Mockito.when(vehicleDao.getByName(vehicleName)).thenReturn(mockData.findVehicleByName(vehicleName));
            Mockito.when(spaceManager.getAvailableSpaceList()).thenReturn(mockData.getLAvailableSpace());
            Mockito.when(levelDao.getByNumber(0)).thenReturn(mockData.loadLevels().get(0));
            Mockito.when(levelDao.getByNumber(6)).thenReturn(mockData.loadLevels().get(6));

            LevelParkedVehicle actual = parkingService.parkVehicle(parkRequest);

            assertTrue(actual.getLevelNumber() >= 0);//negative values indicate space is full. can not park vehicle there
        }

        @Test
        public void GivenSpaceIsfull() {

            Mockito.when(vehicleDao.getByName("car")).thenReturn(mockData.findVehicleByName("car"));
            Mockito.when(spaceManager.getAvailableSpaceList()).thenReturn(new ArrayList<LevelSpace>());
            Mockito.when(levelDao.getByNumber(0)).thenReturn(mockData.loadLevels().get(0));

            ParkingException parkingException = assertThrows(ParkingException.class, () -> {
                for (int i = 0; i < 50; i++) {
                    String vehicleName = "car";
                    String vehicleNumber = "xxxx"+i;
                    //50 slot is not available for car
                    ParkRequest parkRequest = new ParkRequest(vehicleName, vehicleNumber);
                    parkingService.parkVehicle(parkRequest);
                }
            });
            assertEquals("Parking Space is Full for car", parkingException.getMessage());


        }


    }


}
