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
//This class is used to add some initial levels to the database

    @Autowired
    LevelRepository levelRepository;
    @Autowired
    VehicleRepository vehicleRepository;
    @Autowired
    AllowedVehicleRepository allowedVehicleRepository;

    public void loadData() {
        loadVehicles();
        loadLevels();
    }

    private void loadLevels() {
        //adding extra level where only truck can be parked
        Level level = new Level(1);
        levelRepository.save(level);
        List<AllowedVehicle> allowedVehicles = new ArrayList<AllowedVehicle>();
        addAllowedVehiclesToList(level,allowedVehicles, "bus", 15);//bus
        level.setAllowedVehicles(allowedVehicles);
        levelRepository.save(level);
        //adding extra level where bus and container can be parked
        level = new Level(0);
        levelRepository.save(level);
        allowedVehicles = new ArrayList<AllowedVehicle>();
        addAllowedVehiclesToList(level,allowedVehicles, "container", 3);//container
        addAllowedVehiclesToList(level,allowedVehicles, "truck", 10);//truck
        level.setAllowedVehicles(allowedVehicles);
        levelRepository.save(level);
        for (int i = 2; i < 8; i++) {
            //all level contains same list of vehicles and free slots
            level = new Level(i);
            levelRepository.save(level);
            allowedVehicles = new ArrayList<AllowedVehicle>();
            addAllowedVehiclesToList(level,allowedVehicles, "car", 10);//car is added
            addAllowedVehiclesToList(level,allowedVehicles, "van", 8);//van is addded
            addAllowedVehiclesToList(level,allowedVehicles, "bike", 15);//bike is added
            level.setAllowedVehicles(allowedVehicles);
            levelRepository.save(level);
        }


    }

    private void addAllowedVehiclesToList(Level level,List<AllowedVehicle> allowedVehicles, String name, int MAX_SLOT) {
        Vehicle vehicle = vehicleRepository.findVehicleByName(name);
        AllowedVehicle allowedVehicle = new AllowedVehicle(MAX_SLOT, 0, vehicle,level);
        allowedVehicleRepository.save(allowedVehicle);
        allowedVehicles.add(allowedVehicle);
    }


    private void loadVehicles() {
        vehicleRepository.save(new Vehicle("car",20));
        vehicleRepository.save(new Vehicle("bus",40));
        vehicleRepository.save(new Vehicle("van",20));
        vehicleRepository.save(new Vehicle("bike",10));
        vehicleRepository.save(new Vehicle("truck",70));
        vehicleRepository.save(new Vehicle("container",100));

    }

}


