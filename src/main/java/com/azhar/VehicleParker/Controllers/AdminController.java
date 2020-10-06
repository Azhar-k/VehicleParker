package com.azhar.VehicleParker.Controllers;

import com.azhar.VehicleParker.Entities.ApiResponses.EditLevelResponse;
import com.azhar.VehicleParker.Entities.ApiResponses.EditVehicleResponse;
import com.azhar.VehicleParker.db.entities.Building.Level;
import com.azhar.VehicleParker.db.entities.Vehicle.Vehicle;
import com.azhar.VehicleParker.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping(path = "/getLevels")
    public List<Level> getLevelList() {

        return spaceManager.getLevelList();
    }

    @PostMapping(path = "/addLevel")
    public EditLevelResponse addLevel(@RequestBody Level level) {
        return levelService.insertLevel(level);
    }

    @PostMapping(path = "/deleteLevel")
    public EditLevelResponse deleteLevel(@RequestBody Level level) {

        return levelService.deleteLevel(level);
    }

    @PostMapping(path = "/editLevel")
    public EditLevelResponse editLevel(@RequestBody Level level) {
        return levelService.editLevel(level);
    }


    //end points for vehicle
    @GetMapping(path = "/getVehicles")
    public List<Vehicle> getVehicleList() {

        return spaceManager.getVehicles();
    }

    @PostMapping(path = "/addVehicle")
    public EditVehicleResponse addVehicle(@RequestBody Vehicle inputVehicle) {
        return vehicleService.insertVehicle(inputVehicle);
    }

    @PostMapping(path = "/deleteVehicle")
    public EditVehicleResponse deleteVehicle(@RequestBody Vehicle inputVehicle) {

        return vehicleService.deleteVehicle(inputVehicle);
    }

    @PostMapping(path = "/editVehicle")
    public EditVehicleResponse editVehicle(@RequestBody Vehicle inputVehicle) {
        return vehicleService.editVehicle(inputVehicle);
    }


}
