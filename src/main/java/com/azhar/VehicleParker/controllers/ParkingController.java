package com.azhar.VehicleParker.controllers;

import com.azhar.VehicleParker.Entities.ApiRequests.ParkRequest;
import com.azhar.VehicleParker.Entities.Exceptions.ParkingException;
import com.azhar.VehicleParker.Entities.LevelSpace;
import com.azhar.VehicleParker.db.models.LevelParkedVehicle;
import com.azhar.VehicleParker.Entities.ApiResponses.ParkResponse;

import com.azhar.VehicleParker.services.ParkingService;
import com.azhar.VehicleParker.services.SpaceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ParkingController {
    @Autowired
    SpaceManager spaceManager;
    @Autowired
    ParkingService parkingService;

    @GetMapping(path = "/getAvailableSpace")
    public List<LevelSpace> getAvailableSpace() throws Exception {
        return spaceManager.getAvailableSpaceList();
    }

    @GetMapping(path = "/getParkedVehicles")
    public List<LevelParkedVehicle> getParkedVehicles() {

        return spaceManager.getLevelParkedVehicleList();
    }

    @PostMapping(path = "/park")
    public ParkResponse park(@Valid @RequestBody ParkRequest parkRequest) {
        ParkResponse parkResponse = null;
        try {
            LevelParkedVehicle levelParkedVehicle = parkingService.parkVehicle(parkRequest);
            parkResponse = new ParkResponse(true, "vehicle parked", levelParkedVehicle);
        } catch (ParkingException parkingException) {
            parkResponse = new ParkResponse(false, parkingException.getMessage(), null);
        }
        return parkResponse;
    }

    @PostMapping(path = "/unpark", consumes = "application/json", produces = "application/json")
    public ParkResponse unpark(@Valid @RequestBody LevelParkedVehicle levelVehicleMap) {

        ParkResponse parkResponse = null;
        try {
             LevelParkedVehicle levelParkedVehicleResponse = parkingService.unParkVehicle(levelVehicleMap);
            parkResponse = new ParkResponse(true, "vehicle unparked", levelParkedVehicleResponse);
        } catch (ParkingException parkingException) {
            parkResponse = new ParkResponse(false, parkingException.getMessage(), null);
        }
        return parkResponse;
    }


}
