package com.azhar.VehicleParker.Services;

import com.azhar.VehicleParker.Database;
import com.azhar.VehicleParker.Entities.Vehicle.Vehicle;
import com.azhar.VehicleParker.Services.Interfaces.ParkingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ParkingServiceTest {



    @Autowired
    ParkingService parkingService;
    @Autowired
    Database database;


    @Test
    public void parkCar(){
        Vehicle vehicle = new Vehicle("bus");
        database.loadData();
        //ParkResponse parkResponse = new ParkResponse(true,"vehicle parked",levelVehicleMap);
        System.out.println(parkingService.park(vehicle));
    }


}
