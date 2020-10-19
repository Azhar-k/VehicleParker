package com.azhar.VehicleParker.servicesintegration;

import com.azhar.VehicleParker.Entities.Exceptions.VehicleException;
import com.azhar.VehicleParker.db.models.Vehicle.Vehicle;
import com.azhar.VehicleParker.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class VehicleServiceTest {

    @Autowired
    VehicleService vehicleService;

    public void insertVehicleTest() throws VehicleException {

        Vehicle actual = vehicleService.insertVehicle(new Vehicle("jeep",29));
        assertEquals("jeep",actual.getName());
    }
}
