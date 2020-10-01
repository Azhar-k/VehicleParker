package com.azhar.VehicleParker.Controllers;

import com.azhar.VehicleParker.Entities.Building.LevelSpace;
import com.azhar.VehicleParker.Entities.LevelVehicleMap;
import com.azhar.VehicleParker.Entities.Responses.ParkResponse;
import com.azhar.VehicleParker.Entities.Vehicle.Vehicle;

import com.azhar.VehicleParker.Services.Interfaces.ParkingService;
import com.azhar.VehicleParker.Services.Interfaces.SpaceManager;
import com.azhar.VehicleParker.Services.Interfaces.UnParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ParkingController {
    @Autowired
    SpaceManager spaceManager;
    @Autowired
    ParkingService parkingService;
    @Autowired
    UnParkingService unParkingService;

    @GetMapping(path = "/getAvailableSpace")
    public List<LevelSpace> getAvailableSpace(){

        return spaceManager.getLAvailableSpace();
    }

    @PostMapping(path = "/park",consumes = "application/json",produces = "application/json")
    public ParkResponse park(@RequestBody Vehicle vehicle){

        return parkingService.park(vehicle);
    }

    @PostMapping(path = "/unpark",consumes = "application/json",produces = "application/json")
    public ParkResponse unpark(@RequestBody LevelVehicleMap levelVehicleMap){

        return unParkingService.unpark(levelVehicleMap);
    }

}
