package com.azhar.VehicleParker;


import com.azhar.VehicleParker.Entities.Building.Level;
import com.azhar.VehicleParker.Entities.LevelVehicle;
import com.azhar.VehicleParker.Entities.Vehicle.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class Database {
    private static List<Level> levelList = new ArrayList<Level>();
    private static List<Vehicle> vehicleList = new ArrayList<Vehicle>();
    private static List<LevelVehicle> levelVehicleList = new ArrayList<LevelVehicle>();

    public List<LevelVehicle> getLevelVehicleList() {
        return levelVehicleList;
    }
    public List<Level> getLevelList() {

        return levelList;
    }
    public List<Vehicle> getVehicleList() {
        return vehicleList;
    }

    public void loadData() {
        loadVehicles();
        loadLevels();
    }

    private void loadLevels() {
        for (int i = 0; i < 6; i++) {
            Level level = new Level(i);
            addLevel(level, "car", 3);
            addLevel(level, "bus", 3);
            addLevel(level, "van", 3);
            addLevel(level, "bike", 15);
            getLevelList().add(level);
        }
        Level level = new Level(6);
        addLevel(level,"car",2);
        addLevel(level, "truck", 5);
        getLevelList().add(level);
    }
    private void addLevel(Level level, String name, int MAX_SLOT) {
        int type = getVehicleTypeByName(name);
        Vehicle vehicle = new Vehicle(type, name, MAX_SLOT);
        level.getAllowedVehicles().put(vehicle.getType(),vehicle);
    }
    public int getVehicleTypeByName(String name) {
        for (Vehicle vehicle : getVehicleList()) {
            if (vehicle.getName().equals(name)) {
                return vehicle.getType();
            }
        }
        return -1;
    }

    private void loadVehicles() {
        vehicleList.add(new Vehicle(0, "car"));
        vehicleList.add(new Vehicle(1, "bus"));
        vehicleList.add(new Vehicle(2, "van"));
        vehicleList.add(new Vehicle(3, "bike"));
        vehicleList.add(new Vehicle(4, "truck"));
    }

    public LevelVehicle fillSlot(int levelNumber, int vehicleType) {

        LevelVehicle levelVehicle = addLevelVehicleMap(levelNumber, vehicleType);
        if (levelVehicle != null) {
            Level level = getLevelList().get(levelNumber);

            Vehicle vehicle = level.getAllowedVehicles().get(vehicleType);

            int currentOccupiedSlot = vehicle.getOccupiedSlots();
            int updatedOccupiedSlot = currentOccupiedSlot + 1;
            vehicle.setOccupiedSlots(updatedOccupiedSlot);

        }
        return levelVehicle;

    }
    public LevelVehicle addLevelVehicleMap(int levelNumber, int vehicleType) {
        LevelVehicle levelVehicle = null;

        try {
            int id = getUniquieVehicleMapId();
            levelVehicle = new LevelVehicle(levelNumber, vehicleType);
            levelVehicle.setId(id);
            levelVehicleList.add(levelVehicle);
        } catch (Exception e) {
            levelVehicle = null;
        }

        return levelVehicle;
    }

    public Boolean emptySlot(LevelVehicle levelVehicle) {
        boolean isLevelVehicleRemoved = removeLevelVehicle(levelVehicle);
        if (isLevelVehicleRemoved) {
            int levelNumber = levelVehicle.getLevelNumber();
            int vehicleType = levelVehicle.getVehicleType();

            Level level = getLevelList().get(levelNumber);
            Vehicle vehicle = level.getAllowedVehicles().get(vehicleType);

            int currentOccupiedSlot = vehicle.getOccupiedSlots();
            int updatedOccupiedSlot = currentOccupiedSlot - 1;

            vehicle.setOccupiedSlots(updatedOccupiedSlot);
        }
        return true;

    }
    private boolean removeLevelVehicle(LevelVehicle levelVehicle) {
        getLevelVehicleList().remove(levelVehicle);
        return true;
    }

    private int getUniquieVehicleMapId() {
        Random random = new Random();

        while (true) {
            int x = random.nextInt(900) + 100;
            if (!isLevelVehicleMapIdExist(x)) {
                return x;
            }
        }

    }
    private boolean isLevelVehicleMapIdExist(int id) {

        for (LevelVehicle levelVehicle : getLevelVehicleList()) {
            if (levelVehicle.getId() == id) {
                return true;
            }
        }
        return false;
    }




}


