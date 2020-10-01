package com.azhar.VehicleParker.Controllers;

import com.azhar.VehicleParker.Entities.Building.LevelSpace;
import com.azhar.VehicleParker.Entities.Building.Level;
import com.azhar.VehicleParker.Entities.LevelVehicleMap;
import com.azhar.VehicleParker.Entities.Requests.ParkRequest;
import com.azhar.VehicleParker.Entities.Responses.ParkResponse;
import com.azhar.VehicleParker.Entities.Vehicle.*;
import com.azhar.VehicleParker.Services.ParkingService;
import com.azhar.VehicleParker.Services.SpaceManager;
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

    @GetMapping(path = "/getAvailableSpace")
    public List<LevelSpace> getAvailableSpace(){
            return spaceManager.getLAvailableSpace();
    }

    @PostMapping(path = "/park")
    public ParkResponse park(@RequestBody ParkRequest parkRequest){
        Vehicle vehicle = getVehicle(parkRequest.getType());
        return parkingService.park(vehicle);
    }

    public Vehicle getVehicle(String type){
        Vehicle vehicle = null;

        switch (type){
            case "car":
                vehicle = new Car();
                break;
            case "bus":
                vehicle = new Bus();
                break;
            case "bike":
                vehicle = new Bike();
                break;
            case "van":
                vehicle = new Van();
                break;
        }
        return vehicle;
    }

}
