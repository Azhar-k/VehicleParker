package com.azhar.VehicleParker.Services;

import com.azhar.VehicleParker.Dao.LevelDao;
import com.azhar.VehicleParker.Entities.LevelVehicleMap;
import com.azhar.VehicleParker.Entities.Responses.ParkResponse;
import com.azhar.VehicleParker.Entities.Vehicle.Vehicle;
import com.azhar.VehicleParker.Services.Interfaces.ParkingService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.when;

@SpringBootTest
public class ParkingServiceTest {

    @Mock
    LevelDao levelDao;

    @InjectMocks
    ParkingService parkingService;


    @Test
    public void parkCar(){
        Vehicle vehicle = new Vehicle("bus");

        //ParkResponse parkResponse = new ParkResponse(true,"vehicle parked",levelVehicleMap);
        System.out.println(parkingService.park(vehicle));
    }


}
