package com.azhar.VehicleParker.servicesintegration;

import com.azhar.VehicleParker.Dao.AllowedVehicleDao;
import com.azhar.VehicleParker.Dao.LevelDao;
import com.azhar.VehicleParker.Dao.VehicleDao;
import com.azhar.VehicleParker.Entities.ApiResponses.VehicleResponse;
import com.azhar.VehicleParker.db.models.Vehicle.Vehicle;
import com.azhar.VehicleParker.services.VehicleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class VehicleServiceTest {

    @Autowired
    VehicleService vehicleService;

    @Test
    public void insertVehicleTest(){

        VehicleResponse actual = vehicleService.insertVehicle(new Vehicle("jeep",29));
        assertEquals("vehicle added",actual.getMessage());
    }
}
