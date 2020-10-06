package com.azhar.VehicleParker.Controllers;

import com.azhar.VehicleParker.Entities.ApiRequests.ParkRequest;
import com.azhar.VehicleParker.Entities.ApiResponses.EditLevelResponse;
import com.azhar.VehicleParker.Entities.ApiResponses.ParkResponse;
import com.azhar.VehicleParker.Entities.Building.Level;
import com.azhar.VehicleParker.Entities.Building.LevelSpace;
import com.azhar.VehicleParker.Entities.LevelParkedVehicle;
import com.azhar.VehicleParker.Services.EditLevelService;
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
public class AdminController {
    @Autowired
    SpaceManager spaceManager;
    @Autowired
    EditLevelService editLevelService;


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


}
