package com.azhar.VehicleParker;

import com.azhar.VehicleParker.Entities.LevelSpace;
import com.azhar.VehicleParker.db.models.Building.AllowedVehicle;
import com.azhar.VehicleParker.db.models.Building.Level;
import com.azhar.VehicleParker.db.models.Vehicle.Vehicle;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


public class MockData {

    //Loading mockdata for testing
    //Loading mockdata for testing
    public List<Level> loadLevels() {

        List<Level> levelList = new ArrayList<Level>();
        for (int i = 0; i < 6; i++) {
            //all level contains same list of vehicles and free slots
            Level level = new Level(i);
            List<AllowedVehicle> allowedVehicles = new ArrayList<AllowedVehicle>();
            addLevel(allowedVehicles, level, 0, "car", 5);
            addLevel(allowedVehicles, level, 1, "bus", 3);
            addLevel(allowedVehicles, level, 2, "van", 4);
            addLevel(allowedVehicles, level, 3, "bike", 15);
            level.setAllowedVehicles(allowedVehicles);
            levelList.add(level);
        }
        //adding extra level where only truck can be parked
        Level level = new Level(6);
        List<AllowedVehicle> allowedVehicles = new ArrayList<AllowedVehicle>();
        addLevel(allowedVehicles, level, 4, "truck", 4);
        level.setAllowedVehicles(allowedVehicles);
        levelList.add(level);

        //adding extra level where bus and container can be parked
        level = new Level(7);
        allowedVehicles = new ArrayList<AllowedVehicle>();
        addLevel(allowedVehicles, level, 1, "bus", 4);
        addLevel(allowedVehicles, level, 5, "container", 3);
        level.setAllowedVehicles(allowedVehicles);
        levelList.add(level);

        return levelList;
    }

    public void addLevel(List<AllowedVehicle> allowedVehicles, Level level, int type, String name, int MAX_SLOT) {
        Vehicle vehicle = findVehicleById(type);
        AllowedVehicle allowedVehicle = new AllowedVehicle(MAX_SLOT, 0, vehicle, level);
        allowedVehicles.add(allowedVehicle);
    }

    public List<Vehicle> loadVehicles() {
        List<Vehicle> vehicleList = new ArrayList<>();
        vehicleList.add(new Vehicle(0,"car", 20));
        vehicleList.add(new Vehicle(1,"bus", 40));
        vehicleList.add(new Vehicle(2,"van", 20));
        vehicleList.add(new Vehicle(3,"bike", 10));
        vehicleList.add(new Vehicle(4,"truck", 70));
        vehicleList.add(new Vehicle(5,"container", 100));
        return vehicleList;
    }

    public Vehicle findVehicleById(int type) {
        List<Vehicle> vehicleList = loadVehicles();
        for (Vehicle vehicle : vehicleList) {
            if (vehicle.getId() == type) {
                return vehicle;
            }
        }
        return null;
    }

    public Vehicle findVehicleByName(String name) {
        List<Vehicle> vehicleList = loadVehicles();
        for (Vehicle vehicle : vehicleList) {
            if (vehicle.getName().equals(name)) {
                return vehicle;
            }
        }
        return null;
    }

    public List<LevelSpace> getLAvailableSpace() {
        List<LevelSpace> availableSpace = new ArrayList<LevelSpace>();
        for (Level level : loadLevels()) {
            LevelSpace levelSpace = new LevelSpace(level.getNumber());
            for (AllowedVehicle allowedVehicle : level.getAllowedVehicles()) {
                int freeSlot = allowedVehicle.getFreeSlots();
                levelSpace.getAvailabeSlots().put(allowedVehicle.getVehicle().getName(), freeSlot);

            }
            availableSpace.add(levelSpace);

        }
        return availableSpace;
    }
}
