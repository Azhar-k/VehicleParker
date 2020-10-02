package com.azhar.VehicleParker.Controllers;

import com.azhar.VehicleParker.Entities.Building.LevelSpace;
import com.azhar.VehicleParker.Entities.LevelVehicle;
import com.azhar.VehicleParker.Entities.Responses.ParkResponse;
import com.azhar.VehicleParker.Entities.Vehicle.*;
import com.azhar.VehicleParker.Services.ParkingService;
import com.azhar.VehicleParker.Services.SpaceManager;
import com.azhar.VehicleParker.Services.UnParkingService;
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

    @GetMapping(path = "/getParkedVehicles")
    public List<LevelVehicle> getLevelVehicleSpace(){

        return spaceManager.getLevelVehicleList();
    }

    @PostMapping(path = "/park")
    public ParkResponse park(@RequestBody Vehicle vehicle){

        return parkingService.park(vehicle);
    }

    @PostMapping(path = "/unpark",consumes = "application/json",produces = "application/json")
    public ParkResponse unpark(@RequestBody LevelVehicle levelVehicleMap){

        return unParkingService.unPark(levelVehicleMap);
    }




}
