package com.azhar.VehicleParker.Services;

import com.azhar.VehicleParker.Database;
import com.azhar.VehicleParker.Entities.ApiResponses.ParkResponse;
import com.azhar.VehicleParker.Entities.Vehicle.Vehicle;
import com.azhar.VehicleParker.Services.Interfaces.ParkingService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ParkingServiceTest {




    @Mock
    ParkingService parkingService;
    @Autowired
    Database database;


    @Test
    public void ValidateParkCarResponseWithValidVehicleType() throws Exception {
        Vehicle vehicle = new Vehicle("bus");
        when(parkingService.parkVehicle(vehicle)).thenReturn(null);
        database.loadData();
        ParkResponse expected = new ParkResponse(true,"vehicle parked",null);
        ParkResponse actual = parkingService.park(vehicle);
        assertEquals(expected,actual);
    }


}
