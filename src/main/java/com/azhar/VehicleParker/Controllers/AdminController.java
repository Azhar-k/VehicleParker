package com.azhar.VehicleParker.Controllers;

import com.azhar.VehicleParker.Entities.ApiRequests.ParkRequest;
import com.azhar.VehicleParker.Entities.ApiResponses.EditLevelResponse;
import com.azhar.VehicleParker.Entities.ApiResponses.EditVehicleResponse;
import com.azhar.VehicleParker.Entities.ApiResponses.ParkResponse;
import com.azhar.VehicleParker.Entities.Building.Level;
import com.azhar.VehicleParker.Entities.Building.LevelSpace;
import com.azhar.VehicleParker.Entities.LevelParkedVehicle;
import com.azhar.VehicleParker.Entities.Vehicle.Vehicle;
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
    EditLevelService editLevelService;
    @Autowired
    EditVehicleService editVehicleService;


    //end points for level
    @GetMapping(path = "/getLevels")
    public List<Level> getLevelList(){

        return spaceManager.getLevelList();
    }
    @PostMapping(path = "/addLevel")
    public EditLevelResponse addLevel(@RequestBody Level level){
        return editLevelService.insertLevel(level);
    }

    @PostMapping(path = "/deleteLevel")
    public EditLevelResponse deleteLevel(@RequestBody Level level){

        return editLevelService.deleteLevel(level);
    }
    @PostMapping(path = "/editLevel")
    public EditLevelResponse editLevel(@RequestBody Level level){
        return editLevelService.editLevel(level);
    }


    //end points for vehicle
    @GetMapping(path = "/getVehicles")
    public List<Vehicle> getVehicleList(){

        return spaceManager.getVehicles();
    }
    @PostMapping(path = "/addVehicle")
    public EditVehicleResponse addVehicle(@RequestBody Vehicle inputVehicle){
        return editVehicleService.insertVehicle(inputVehicle);
    }

    @PostMapping(path = "/deleteVehicle")
    public EditVehicleResponse deleteVehicle(@RequestBody Vehicle inputVehicle){

        return editVehicleService.deleteVehicle(inputVehicle);
    }
    @PostMapping(path = "/editVehicle")
    public EditVehicleResponse editVehicle(@RequestBody Vehicle inputVehicle){
        return editVehicleService.editVehicle(inputVehicle);
    }


}
