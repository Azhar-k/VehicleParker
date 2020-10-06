package com.azhar.VehicleParker.Controllers;

import com.azhar.VehicleParker.Entities.ApiRequests.ParkRequest;
import com.azhar.VehicleParker.Entities.LevelSpace;
import com.azhar.VehicleParker.db.entities.LevelParkedVehicle;
import com.azhar.VehicleParker.Entities.ApiResponses.ParkResponse;

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
    public List<LevelSpace> getAvailableSpace() {
        return spaceManager.getLAvailableSpace();
    }

    @GetMapping(path = "/getParkedVehicles")
    public List<LevelParkedVehicle> getParkedVehicles() {

        return spaceManager.getLevelVehicleList();
    }

    @PostMapping(path = "/park")
    public ParkResponse park(@RequestBody ParkRequest parkRequest) {

        return parkingService.park(parkRequest);
    }

    @PostMapping(path = "/unpark", consumes = "application/json", produces = "application/json")
    public ParkResponse unpark(@RequestBody LevelParkedVehicle levelVehicleMap) {

        return unParkingService.unPark(levelVehicleMap);
    }


}
