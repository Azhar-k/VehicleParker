package com.azhar.VehicleParker.controllers;

import com.azhar.VehicleParker.Entities.ApiResponses.LevelResponse;
import com.azhar.VehicleParker.Entities.ApiResponses.VehicleResponse;
import com.azhar.VehicleParker.Entities.Exceptions.LevelException;
import com.azhar.VehicleParker.services.LevelService;
import com.azhar.VehicleParker.services.SpaceManager;
import com.azhar.VehicleParker.services.VehicleService;
import com.azhar.VehicleParker.db.models.Building.Level;
import com.azhar.VehicleParker.db.models.Vehicle.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

        return levelService.getSortedLevels();
    }
    @PostMapping(path = "/levels")
    public LevelResponse addLevel(@Valid @RequestBody Level level) {
        LevelResponse levelResponse ;
        try {
            Level insertedLevel = levelService.insertLevel(level);
            levelResponse = new LevelResponse(true, "Level added", insertedLevel);
        } catch (LevelException e) {
            levelResponse = new LevelResponse(false, e.getMessage(), null);
        }
        return levelResponse;
    }

    @PutMapping(path = "/levels")
    public LevelResponse editLevel(@Valid @RequestBody Level level) {
        LevelResponse levelResponse=null;
        try{
            Level editedLevel = levelService.editLevel(level);
            levelResponse = new LevelResponse(true, "Level edited", editedLevel);
        } catch (LevelException e) {
            levelResponse = new LevelResponse(false, e.getMessage(), null);
        }
        return levelResponse;
    }

    @DeleteMapping(path = "/levels")
    public LevelResponse deleteLevel(@Valid @RequestBody Level level) {
        LevelResponse levelResponse = null;
        try{
            levelService.deleteLevel(level);
            levelResponse = new LevelResponse(true, "Level deleted", null);
        } catch (LevelException e) {
            levelResponse = new LevelResponse(false, e.getMessage(), null);
        }
        return levelResponse;
    }




    //end points for vehicle
    @GetMapping(path = "/vehicles")
    public List<Vehicle> getVehicleList() {

        return vehicleService.getVehicles();
    }
    @PostMapping(path = "/vehicles")
    public VehicleResponse addVehicle(@Valid @RequestBody Vehicle inputVehicle) {
        return vehicleService.insertVehicle(inputVehicle);
    }
    @PutMapping(path = "/vehicles")
    public VehicleResponse editVehicle(@Valid @RequestBody Vehicle inputVehicle) {
        return vehicleService.editVehicle(inputVehicle);
    }

    @DeleteMapping(path = "/vehicles")
    public VehicleResponse deleteVehicle(@Valid @RequestBody Vehicle inputVehicle) {

        return vehicleService.deleteVehicle(inputVehicle);
    }




}
