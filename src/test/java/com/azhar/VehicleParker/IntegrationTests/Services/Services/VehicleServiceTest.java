package com.azhar.VehicleParker.IntegrationTests.Services.Services;

import com.azhar.VehicleParker.Dao.VehicleDao;
import com.azhar.VehicleParker.Entities.ApiResponses.VehicleResponse;
import com.azhar.VehicleParker.UnitTests.Services.implimentation.VehicleService;
import com.azhar.VehicleParker.db.models.Vehicle.Vehicle;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class VehicleServiceTest {

    @Mock
    private VehicleDao vehicleDao;

    @InjectMocks
    private VehicleService vehicleService;

    @Nested
    public class ValidateVehicleTest {
        @Test
        void validateVehicleGivenExistingVehicle() {
            Vehicle inputVehicle = new Vehicle(0, "car", 20);

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
        void validateVehicleGivenNewVehicle() {
            Vehicle inputVehicle = new Vehicle(0, "car", 20);

            Mockito.when(vehicleDao.getVehicleByName(inputVehicle.getName())).thenReturn(null);
            Vehicle actual = vehicleService.validateVehicle(inputVehicle);
            assertNull(actual);

        }
    }


    @Nested
    public class InsertVehicleTest {
        @Test
        void insertVehicleTestGivenExistingVehicle() {
            Vehicle inputVehicle = new Vehicle(0, "car", 20);
            Mockito.when(vehicleDao.getVehicleByName(inputVehicle.getName())).thenReturn(inputVehicle);


            VehicleResponse expected = new VehicleResponse(true, "vehicle already exist", inputVehicle);
            VehicleResponse actual = vehicleService.insertVehicle(inputVehicle);
            assertEquals(expected.getMessage(), actual.getMessage());
        }

        @Test
        void insertVehicleTestGivenNewVehicle() {
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
        void editVehicleTestGivenExistingVehicle() {
            Vehicle inputVehicle = new Vehicle(0, "car", 20);
            Mockito.when(vehicleDao.getVehicleByName(inputVehicle.getName())).thenReturn(inputVehicle);
            Mockito.when(vehicleDao.update(inputVehicle)).thenReturn(inputVehicle);

            VehicleResponse expected = new VehicleResponse(true, "vehicle edited", inputVehicle);
            VehicleResponse actual = vehicleService.editVehicle(inputVehicle);
            assertEquals(expected.getMessage(), actual.getMessage());
        }

        @Test
        void editVehicleTestGivenNewVehicle() {
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
        void deleteVehicleTestGivenExistingVehicle() {
            Vehicle inputVehicle = new Vehicle(0, "car", 20);
            Mockito.when(vehicleDao.getVehicleByName(inputVehicle.getName())).thenReturn(inputVehicle);
            //Mockito.when(vehicleDao.delete(inputVehicle)).then();

            VehicleResponse expected = new VehicleResponse(true, "vehicle deleted", inputVehicle);
            VehicleResponse actual = vehicleService.deleteVehicle(inputVehicle);
            assertEquals(expected.getMessage(), actual.getMessage());
        }

        @Test
        void deleteVehicleTestGivenNewVehicle() {
            Vehicle inputVehicle = new Vehicle(0, "car", 20);
            Mockito.when(vehicleDao.getVehicleByName(inputVehicle.getName())).thenReturn(null);

            VehicleResponse expected = new VehicleResponse(true, "vehicle do not exist", inputVehicle);
            VehicleResponse actual = vehicleService.deleteVehicle(inputVehicle);
            assertEquals(expected.getMessage(), actual.getMessage());
        }
    }


}
