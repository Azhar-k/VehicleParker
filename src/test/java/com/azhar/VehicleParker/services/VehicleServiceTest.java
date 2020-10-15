package com.azhar.VehicleParker.services;

import com.azhar.VehicleParker.Dao.AllowedVehicleDao;
import com.azhar.VehicleParker.Dao.LevelDao;
import com.azhar.VehicleParker.Dao.VehicleDao;
import com.azhar.VehicleParker.Entities.ApiResponses.VehicleResponse;
import com.azhar.VehicleParker.Entities.Exceptions.VehicleNotFound;
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
        void validateVehicleGivenExistingVehicle() throws VehicleNotFound {
            Vehicle inputVehicle = new Vehicle( "car", 20);

            Mockito.when(vehicleDao.getVehicleByName(inputVehicle.getName())).thenReturn(inputVehicle);

            Vehicle expected = inputVehicle;
            Vehicle actual = vehicleService.validateVehicle(inputVehicle);
            assertAll(() -> {
                assertEquals(expected.getName(), actual.getName());
                assertEquals(expected.getId(), actual.getId());
                assertEquals(expected.getParkingRate(), actual.getParkingRate());
            });

        }

        @Test
        void validateVehicleGivenNewVehicle() throws VehicleNotFound {
            Vehicle inputVehicle = new Vehicle( "car", 20);

            Mockito.when(vehicleDao.getVehicleByName(inputVehicle.getName())).thenReturn(null);
            Vehicle actual = vehicleService.validateVehicle(inputVehicle);
            assertNull(actual);

        }
    }


    @Nested
    public class InsertVehicleTest {
        @Test
        void insertVehicleTestGivenExistingVehicle() throws VehicleNotFound {
            Vehicle inputVehicle = new Vehicle(0, "car", 20);
            Mockito.when(vehicleDao.getVehicleByName(inputVehicle.getName())).thenReturn(inputVehicle);


            VehicleResponse expected = new VehicleResponse(true, "vehicle already exist", inputVehicle);
            VehicleResponse actual = vehicleService.insertVehicle(inputVehicle);
            assertEquals(expected.getMessage(), actual.getMessage());
        }

        @Test
        void insertVehicleTestGivenNewVehicle() throws VehicleNotFound {
            Vehicle inputVehicle = new Vehicle(0, "car", 20);
            Mockito.when(vehicleDao.getVehicleByName(inputVehicle.getName())).thenReturn(null);
            Mockito.when(vehicleDao.insert(inputVehicle)).thenReturn(inputVehicle);

            VehicleResponse expected = new VehicleResponse(true, "vehicle added", inputVehicle);
            VehicleResponse actual = vehicleService.insertVehicle(inputVehicle);
            assertEquals(expected.getMessage(), actual.getMessage());
        }
    }

    @Nested
    public class EditVehicleTest {
        @Test
        void editVehicleTestGivenExistingVehicle() throws VehicleNotFound {
            Vehicle inputVehicle = new Vehicle(0, "car", 20);
            Mockito.when(vehicleDao.getVehicleByName(inputVehicle.getName())).thenReturn(inputVehicle);
            Mockito.when(vehicleDao.update(inputVehicle)).thenReturn(inputVehicle);

            VehicleResponse expected = new VehicleResponse(true, "vehicle edited", inputVehicle);
            VehicleResponse actual = vehicleService.editVehicle(inputVehicle);
            assertEquals(expected.getMessage(), actual.getMessage());
        }

        @Test
        void editVehicleTestGivenNewVehicle() throws VehicleNotFound {
            Vehicle inputVehicle = new Vehicle(0, "car", 20);
            Mockito.when(vehicleDao.getVehicleByName(inputVehicle.getName())).thenReturn(null);

            VehicleResponse expected = new VehicleResponse(true, "vehicle do not exist", inputVehicle);
            VehicleResponse actual = vehicleService.editVehicle(inputVehicle);
            assertEquals(expected.getMessage(), actual.getMessage());
        }
    }

    @Nested
    public class DeleteVehicleTest {
        @Test
        void deleteVehicleTestGivenExistingVehicle() throws VehicleNotFound {
            Vehicle inputVehicle = new Vehicle(0, "car", 20);
            Mockito.when(vehicleDao.getVehicleByName(inputVehicle.getName())).thenReturn(inputVehicle);
            Mockito.when(levelDao.getLevelList()).thenReturn(mockData.loadLevels());
            VehicleResponse expected = new VehicleResponse(true, "vehicle deleted", inputVehicle);
            VehicleResponse actual = vehicleService.deleteVehicle(inputVehicle);
            assertEquals(expected.getMessage(), actual.getMessage());
        }

        @Test
        void deleteVehicleTestGivenNewVehicle() throws VehicleNotFound {
            Vehicle inputVehicle = new Vehicle(0, "car", 20);
            Mockito.when(vehicleDao.getVehicleByName(inputVehicle.getName())).thenReturn(null);

            VehicleResponse expected = new VehicleResponse(true, "vehicle do not exist", inputVehicle);
            VehicleResponse actual = vehicleService.deleteVehicle(inputVehicle);
            assertEquals(expected.getMessage(), actual.getMessage());
        }

    }


}
