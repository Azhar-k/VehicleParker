package com.azhar.VehicleParker.Controllers;

import com.azhar.VehicleParker.Entities.ApiResponses.LevelResponse;
import com.azhar.VehicleParker.Entities.ApiResponses.VehicleResponse;
import com.azhar.VehicleParker.db.models.Building.Level;
import com.azhar.VehicleParker.db.models.Vehicle.Vehicle;
import com.azhar.VehicleParker.UnitTests.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdminController {
    @Autowired
    SpaceManager spaceManager;
    @Autowired
    LevelService levelService;
    @Autowired
    VehicleService vehicleService;


    //end points for level
    @GetMapping(path = "/levels")
    public List<Level> getLevelList() {

        return spaceManager.getLevelList();
    }

    @PostMapping(path = "/levels")
    public LevelResponse addLevel(@RequestBody Level level) {
        return levelService.insertLevel(level);
    }

    @PutMapping(path = "/levels")
    public LevelResponse editLevel(@RequestBody Level level) {
        return levelService.editLevel(level);
    }

    @DeleteMapping(path = "/levels")
    public LevelResponse deleteLevel(@RequestBody Level level) {

        return levelService.deleteLevel(level);
    }




    //end points for vehicle
    @GetMapping(path = "/vehicles")
    public List<Vehicle> getVehicleList() {

        return spaceManager.getVehicles();
    }

    @PostMapping(path = "/vehicles")
    public VehicleResponse addVehicle(@RequestBody Vehicle inputVehicle) {
        return vehicleService.insertVehicle(inputVehicle);
    }
    @PutMapping(path = "/vehicles")
    public VehicleResponse editVehicle(@RequestBody Vehicle inputVehicle) {
        return vehicleService.editVehicle(inputVehicle);
    }

    @DeleteMapping(path = "/vehicles")
    public VehicleResponse deleteVehicle(@RequestBody Vehicle inputVehicle) {

        return vehicleService.deleteVehicle(inputVehicle);
    }




}
