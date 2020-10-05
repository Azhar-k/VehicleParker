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
        for (int i = 0; i < 6; i++) {
            //all level contains same list of vehicles and free slots
            Level level = new Level(i);
            List<AllowedVehicle> allowedVehicles = new ArrayList<AllowedVehicle>();
            addLevel(allowedVehicles, 0,"car", 5);
            addLevel(allowedVehicles, 1,"bus", 3);
            addLevel(allowedVehicles, 2,"van", 4);
            addLevel(allowedVehicles, 3, "bike",15);
            level.setAllowedVehicles(allowedVehicles);
            levelRepository.save(level);
        }
        //adding extra level where only truck can be parked
        Level level = new Level(6);
        List<AllowedVehicle> allowedVehicles = new ArrayList<AllowedVehicle>();
        addLevel(allowedVehicles,4,"truck",4);
        level.setAllowedVehicles(allowedVehicles);
        levelRepository.save(level);

        //adding extra level where bus and container can be parked
        level = new Level(7);
        allowedVehicles = new ArrayList<AllowedVehicle>();
        addLevel(allowedVehicles,1,"bus",4);
        addLevel(allowedVehicles,5,"container",3);
        level.setAllowedVehicles(allowedVehicles);
        levelRepository.save(level);
    }
    private void addLevel(List<AllowedVehicle> allowedVehicles, int type, String name, int MAX_SLOT) {
        Vehicle vehicle = vehicleRepository.getOne(type);
        AllowedVehicle allowedVehicle = new AllowedVehicle(MAX_SLOT,0,vehicle);
        allowedVehicleRepository.save(allowedVehicle);
        allowedVehicles.add(allowedVehicle);
    }


//    private void loadVehicles() {
//        vehicleList.add(new Vehicle(0, "car"));
//        vehicleList.add(new Vehicle(1, "bus"));
//        vehicleList.add(new Vehicle(2, "van"));
//        vehicleList.add(new Vehicle(3, "bike"));
//        vehicleList.add(new Vehicle(4, "truck"));
//    }

}


