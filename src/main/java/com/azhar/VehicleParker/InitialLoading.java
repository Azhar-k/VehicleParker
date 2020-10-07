package com.azhar.VehicleParker;


import com.azhar.VehicleParker.db.repositories.AllowedVehicleRepository;
import com.azhar.VehicleParker.db.repositories.LevelRepository;
import com.azhar.VehicleParker.db.repositories.VehicleRepository;
import com.azhar.VehicleParker.db.models.Building.Level;
import com.azhar.VehicleParker.db.models.Building.AllowedVehicle;
import com.azhar.VehicleParker.db.models.Vehicle.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class InitialLoading {
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
        addLevel(allowedVehicles, 1, 15);//bus
        level.setAllowedVehicles(allowedVehicles);
        levelRepository.save(level);
        //adding extra level where bus and container can be parked
        level = new Level(0);
        allowedVehicles = new ArrayList<AllowedVehicle>();
        addLevel(allowedVehicles, 5, 3);//container
        addLevel(allowedVehicles, 4, 10);//truck
        level.setAllowedVehicles(allowedVehicles);
        levelRepository.save(level);
        for (int i = 2; i < 8; i++) {
            //all level contains same list of vehicles and free slots
            level = new Level(i);
            allowedVehicles = new ArrayList<AllowedVehicle>();
            addLevel(allowedVehicles, 0, 10);//car is added
            addLevel(allowedVehicles, 2, 8);//van is addded
            addLevel(allowedVehicles, 3, 15);//bike is added
            level.setAllowedVehicles(allowedVehicles);
            levelRepository.save(level);
        }


    }

    private void addLevel(List<AllowedVehicle> allowedVehicles, int type, int MAX_SLOT) {
        Vehicle vehicle = vehicleRepository.getOne(type);
        AllowedVehicle allowedVehicle = new AllowedVehicle(MAX_SLOT, 0, vehicle);
        allowedVehicleRepository.save(allowedVehicle);
        allowedVehicles.add(allowedVehicle);
    }


    private void loadVehicles() {
        vehicleRepository.save(new Vehicle("car"));
        vehicleRepository.save(new Vehicle("bus"));
        vehicleRepository.save(new Vehicle("van"));
        vehicleRepository.save(new Vehicle("bike"));
        vehicleRepository.save(new Vehicle("truck"));
    }

}


