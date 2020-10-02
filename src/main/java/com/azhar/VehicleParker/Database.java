package com.azhar.VehicleParker;


import com.azhar.VehicleParker.Entities.Building.Level;
import com.azhar.VehicleParker.Entities.Building.LevelSpace;
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


    public void loadData() {
        loadVehicles();
        loadLevels();

    }

    private void loadLevels() {
        for (int i = 0; i < 6; i++) {
            Level level = new Level(i);

            int carType = getVehicleTypeByName("car");
            Vehicle car = new Vehicle(carType,"car",15);
            System.out.println(car);
            level.getAllowedVehicles().add(car);

            int busType = getVehicleTypeByName("bus");
            Vehicle bus = new Vehicle(busType,"bus",15);
            level.getAllowedVehicles().add(bus);

            levelList.add(level);
        }

    }

    private void loadVehicles() {
        vehicleList.add(new Vehicle(0, "car"));
        vehicleList.add(new Vehicle(1, "bus"));
        vehicleList.add(new Vehicle(2, "van"));
        vehicleList.add(new Vehicle(3, "bike"));
    }

    public int getVehicleTypeByName(String name) {
        for (Vehicle vehicle : getVehicleList()) {
            if (vehicle.getName().equals(name)) {
                return vehicle.getType();
            }
        }
        return -1;
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
        boolean isLevelVehicleRemoved = removeLevelVehicleList(levelVehicle);
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

    private boolean removeLevelVehicleList(LevelVehicle levelVehicle) {
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

    public List<LevelSpace> getAvailableSpace() {
        List<LevelSpace> availableSpace = new ArrayList<LevelSpace>();
        for (Level level : getLevelList()) {
            LevelSpace levelSpace = new LevelSpace(level.getLevelNumber());
            for (Vehicle vehicle : level.getAllowedVehicles()) {
                int freeSlot = vehicle.getFreeSlots();
                levelSpace.getAvailabeSpace().put(vehicle.getName(), freeSlot);

            }
            availableSpace.add(levelSpace);

        }
        return availableSpace;
    }

    public List<LevelVehicle> getLevelVehicleList() {
        return levelVehicleList;
    }

    public List<Level> getLevelList() {

        return levelList;
    }

    public List<Vehicle> getVehicleList() {
        return vehicleList;
    }



}


