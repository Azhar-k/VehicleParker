package com.azhar.VehicleParker.Controllers;

import com.azhar.VehicleParker.Entities.Building.LevelSpace;
import com.azhar.VehicleParker.Entities.LevelParkedVehicle;
import com.azhar.VehicleParker.Entities.ApiResponses.ParkResponse;
import com.azhar.VehicleParker.Entities.Vehicle.*;

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

    @GetMapping(path = "/getParkedVehicles")
    public List<LevelParkedVehicle> getLevelVehicleSpace(){

        return spaceManager.getLevelVehicleList();
    }

    @PostMapping(path = "/park")
    public ParkResponse park(@RequestBody Vehicle vehicle){

        return parkingService.park(vehicle);
    }

    @PostMapping(path = "/unpark",consumes = "application/json",produces = "application/json")
    public ParkResponse unpark(@RequestBody LevelParkedVehicle levelVehicleMap){

        return unParkingService.unPark(levelVehicleMap);
    }




}
