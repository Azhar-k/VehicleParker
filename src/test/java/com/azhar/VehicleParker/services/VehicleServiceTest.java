package com.azhar.VehicleParker.services;

import com.azhar.VehicleParker.Dao.AllowedVehicleDao;
import com.azhar.VehicleParker.Dao.LevelDao;
import com.azhar.VehicleParker.Dao.VehicleDao;
import com.azhar.VehicleParker.Entities.Exceptions.VehicleException;
import com.azhar.VehicleParker.MockData;
import com.azhar.VehicleParker.services.implimentation.VehicleService;
import com.azhar.VehicleParker.db.models.Vehicle.Vehicle;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class VehicleServiceTest {

    @Mock
    private VehicleDao vehicleDao;
    @Mock
    private LevelDao levelDao;
    @Mock
    private AllowedVehicleDao allowedVehicleDao;

    @InjectMocks
    private VehicleService vehicleService;
    MockData mockData = new MockData();

    @Nested
    public class ValidateVehicleTest {
        @Test
        void validateVehicleGivenExistingVehicle()  {
            Vehicle inputVehicle = new Vehicle( "car", 20);

            Mockito.when(vehicleDao.getByName(inputVehicle.getName())).thenReturn(inputVehicle);

            Vehicle expected = inputVehicle;
            Vehicle actual = vehicleService.validateVehicle(inputVehicle);
            assertAll(() -> {
                assertEquals(expected.getName(), actual.getName());
                assertEquals(expected.getId(), actual.getId());
                assertEquals(expected.getParkingRate(), actual.getParkingRate());
            });

        }

        @Test
        void validateVehicleGivenNewVehicle()  {
            Vehicle inputVehicle = new Vehicle( "car", 20);

            Mockito.when(vehicleDao.getByName(inputVehicle.getName())).thenReturn(null);
            Vehicle actual = vehicleService.validateVehicle(inputVehicle);
            assertNull(actual);

        }
    }


    @Nested
    public class InsertVehicleTest {
        @Test
        void insertVehicleTestGivenExistingVehicle()  {
            Vehicle inputVehicle = new Vehicle(0, "car", 20);
            Mockito.when(vehicleDao.getByName(inputVehicle.getName())).thenReturn(inputVehicle);

            VehicleException vehicleException=assertThrows(VehicleException.class,()->{
                vehicleService.insertVehicle(inputVehicle);
            });
            assertEquals("vehicle already exist", vehicleException.getMessage());
        }

        @Test
        void insertVehicleTestGivenNewVehicle() throws VehicleException {
            Vehicle inputVehicle = new Vehicle(0, "car", 20);
            Mockito.when(vehicleDao.getByName(inputVehicle.getName())).thenReturn(null);
            Mockito.when(vehicleDao.insert(inputVehicle)).thenReturn(inputVehicle);

            Vehicle expected = inputVehicle;
            Vehicle actual = vehicleService.insertVehicle(inputVehicle);
            assertEquals(expected.getName(), actual.getName());
        }
    }

    @Nested
    public class EditVehicleTest {
        @Test
        void editVehicleTestGivenExistingVehicle() throws VehicleException {
            Vehicle inputVehicle = new Vehicle(0, "car", 20);
            Mockito.when(vehicleDao.getByName(inputVehicle.getName())).thenReturn(inputVehicle);
            Mockito.when(vehicleDao.update(inputVehicle)).thenReturn(inputVehicle);

            Vehicle expected = inputVehicle;
            Vehicle actual = vehicleService.editVehicle(inputVehicle);
            assertEquals(expected.getName(), actual.getName());
        }

        @Test
        void editVehicleTestGivenNewVehicle()  {
            Vehicle inputVehicle = new Vehicle(0, "car", 20);
            Mockito.when(vehicleDao.getByName(inputVehicle.getName())).thenReturn(null);

            VehicleException vehicleException=assertThrows(VehicleException.class,()->{
                vehicleService.editVehicle(inputVehicle);
            });
            assertEquals("vehicle do not exist", vehicleException.getMessage());
        }
    }

    @Nested
    public class DeleteVehicleTest {
        @Test
        void deleteVehicleTestGivenExistingVehicle() throws VehicleException {
            Vehicle inputVehicle = new Vehicle(0, "car", 20);
            Mockito.when(vehicleDao.getByName(inputVehicle.getName())).thenReturn(inputVehicle);
            Mockito.when(levelDao.getAll()).thenReturn(mockData.loadLevels());
            boolean actual = vehicleService.deleteVehicle(inputVehicle);
            assertTrue(actual);
        }

        @Test
        void deleteVehicleTestGivenNewVehicle()  {
            Vehicle inputVehicle = new Vehicle(0, "car", 20);
            Mockito.when(vehicleDao.getByName(inputVehicle.getName())).thenReturn(null);

            VehicleException vehicleException=assertThrows(VehicleException.class,()->{
                vehicleService.deleteVehicle(inputVehicle);
            });
            assertEquals("vehicle do not exist", vehicleException.getMessage());
        }

    }


}
