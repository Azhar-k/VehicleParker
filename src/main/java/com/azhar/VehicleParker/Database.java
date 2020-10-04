package com.azhar.VehicleParker;


import com.azhar.VehicleParker.dbclient.AllowedVehicleRepository;
import com.azhar.VehicleParker.dbclient.LevelRepository;
import com.azhar.VehicleParker.dbclient.LevelParkedVehicleRepository;
import com.azhar.VehicleParker.dbclient.VehicleRepository;
import com.azhar.VehicleParker.Entities.Building.Level;
import com.azhar.VehicleParker.Entities.Building.AllowedVehicle;
import com.azhar.VehicleParker.Entities.LevelParkedVehicle;
import com.azhar.VehicleParker.Entities.Vehicle.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class Database {
    private static List<Level> levelList = new ArrayList<Level>();
    private static List<Vehicle> vehicleList = new ArrayList<Vehicle>();
    private static List<LevelParkedVehicle> levelAllowedVehicleList = new ArrayList<LevelParkedVehicle>();

    @Autowired
    LevelRepository levelRepository;
    @Autowired
    LevelParkedVehicleRepository levelParkedVehicleRepository;
    @Autowired
    VehicleRepository vehicleRepository;
    @Autowired
    AllowedVehicleRepository allowedVehicleRepository;

    public List<LevelParkedVehicle> getLevelAllowedVehicleList() {
        return levelAllowedVehicleList;
    }
    public List<Level> getLevelList() {

        return levelRepository.findAll();
    }
    public List<Vehicle> getVehicleList() {
        return vehicleRepository.findAll();
    }

    public void loadData() {
        loadVehicles();
        loadLevels();
    }

    private void loadLevels() {
        for (int i = 0; i < 6; i++) {
            //all level contains same list of vehicles and free slots
            Level level = new Level(i);
            List<AllowedVehicle> allowedVehicles = new ArrayList<AllowedVehicle>();
            addLevel(allowedVehicles, 0,"car", 3);
            addLevel(allowedVehicles, 1,"bus", 3);
            addLevel(allowedVehicles, 2,"van", 3);
            addLevel(allowedVehicles, 3, "bike",15);
            level.setAllowedVehicles(allowedVehicles);
            levelRepository.save(level);
        }
        //adding extra level where only truck can be parked
        Level level = new Level(6);
        List<AllowedVehicle> allowedVehicles = new ArrayList<AllowedVehicle>();
        addLevel(allowedVehicles,4,"truck",2);
        level.setAllowedVehicles(allowedVehicles);
        levelRepository.save(level);
    }
    private void addLevel(List<AllowedVehicle> allowedVehicles, int type, String name, int MAX_SLOT) {
        Vehicle vehicle = vehicleRepository.getOne(type);
        AllowedVehicle allowedVehicle = new AllowedVehicle(MAX_SLOT,0,vehicle);
        allowedVehicleRepository.save(allowedVehicle);
        allowedVehicles.add(allowedVehicle);
    }


    private void loadVehicles() {
        vehicleList.add(new Vehicle(0, "car"));
        vehicleList.add(new Vehicle(1, "bus"));
        vehicleList.add(new Vehicle(2, "van"));
        vehicleList.add(new Vehicle(3, "bike"));
        vehicleList.add(new Vehicle(4, "truck"));
    }



    public Boolean emptySlot(LevelParkedVehicle levelAllowedVehicle) {
        boolean isLevelAllowedVehicleRemoved = removeLevelAllowedVehicle(levelAllowedVehicle);
        if (isLevelAllowedVehicleRemoved) {
            int levelNumber = levelAllowedVehicle.getLevelNumber();
            int allowedVehicleid = levelAllowedVehicle.getVehicleType();

            Level level = getLevelList().get(levelNumber);
            for(AllowedVehicle allowedVehicle:level.getAllowedVehicles()){
                if(allowedVehicle.getVehicle().getId()==allowedVehicleid){
                    int currentOccupiedSlot = allowedVehicle.getOccupiedSlots();
                    int updatedOccupiedSlot = currentOccupiedSlot - 1;
                    allowedVehicle.setOccupiedSlots(updatedOccupiedSlot);
                }
                allowedVehicleRepository.save(allowedVehicle);
            }
            levelRepository.save(level);

        }
        return true;

    }
    private boolean removeLevelAllowedVehicle(LevelParkedVehicle levelAllowedVehicle) {
        levelParkedVehicleRepository.delete(levelAllowedVehicle);
        return true;
    }

    private int getUniquieVehicleMapId() {
        Random random = new Random();

        while (true) {
            int x = random.nextInt(900) + 100;
            if (!isLevelAllowedVehicleMapIdExist(x)) {
                return x;
            }
        }

    }
    private boolean isLevelAllowedVehicleMapIdExist(int id) {

        for (LevelParkedVehicle levelAllowedVehicle : getLevelAllowedVehicleList()) {
            if (levelAllowedVehicle.getId() == id) {
                return true;
            }
        }
        return false;
    }




}


