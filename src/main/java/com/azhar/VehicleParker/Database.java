package com.azhar.VehicleParker;


import com.azhar.VehicleParker.dbclient.AllowedVehicleRepository;
import com.azhar.VehicleParker.dbclient.LevelRepository;
import com.azhar.VehicleParker.dbclient.VehicleRepository;
import com.azhar.VehicleParker.Entities.Building.Level;
import com.azhar.VehicleParker.Entities.Building.AllowedVehicle;
import com.azhar.VehicleParker.Entities.Vehicle.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class Database {
//    @Autowired
//    LevelDao levelDao;
//    @Autowired
//    VehicleDao vehicleDao;
//    @Autowired
//    AllowedVehicleDao allowedVehicleDao;

    @Autowired
    LevelRepository levelRepository;
    @Autowired
    VehicleRepository vehicleRepository;
    @Autowired
    AllowedVehicleRepository allowedVehicleRepository;
    public void loadData() {
        //loadVehicles();
        loadLevels();
    }

    private void loadLevels() {
        //adding extra level where only truck can be parked
        Level level = new Level(1);
        List<AllowedVehicle> allowedVehicles = new ArrayList<AllowedVehicle>();
        addLevel(allowedVehicles,1,"bus",15);
        level.setAllowedVehicles(allowedVehicles);
        levelRepository.save(level);
        //adding extra level where bus and container can be parked
        level = new Level(0);
        allowedVehicles = new ArrayList<AllowedVehicle>();
        addLevel(allowedVehicles,5,"container",3);
        addLevel(allowedVehicles,4,"truck",10);
        level.setAllowedVehicles(allowedVehicles);
        levelRepository.save(level);
        for (int i = 2; i < 8; i++) {
            //all level contains same list of vehicles and free slots
            level = new Level(i);
            allowedVehicles = new ArrayList<AllowedVehicle>();
            addLevel(allowedVehicles, 0,"car", 10);
            addLevel(allowedVehicles, 2,"van", 8);
            addLevel(allowedVehicles, 3, "bike",15);
            level.setAllowedVehicles(allowedVehicles);
            levelRepository.save(level);
        }



    }
    private void addLevel(List<AllowedVehicle> allowedVehicles, int type, String name, int MAX_SLOT) {
        Vehicle vehicle = vehicleRepository.getOne(type);
        AllowedVehicle allowedVehicle = new AllowedVehicle(MAX_SLOT,0,vehicle);
        allowedVehicleRepository.save(allowedVehicle);
        allowedVehicles.add(allowedVehicle);
    }


    private void loadVehicles() {
        vehicleRepository.save(new Vehicle( "car"));
        vehicleRepository.save(new Vehicle( "bus"));
        vehicleRepository.save(new Vehicle( "van"));
        vehicleRepository.save(new Vehicle( "bike"));
        vehicleRepository.save(new Vehicle( "truck"));
    }

}


