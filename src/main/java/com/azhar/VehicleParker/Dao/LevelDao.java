package com.azhar.VehicleParker.Dao;

import com.azhar.VehicleParker.Database;
import com.azhar.VehicleParker.Entities.Building.LevelSpace;
import com.azhar.VehicleParker.Entities.Building.Level;
import com.azhar.VehicleParker.Entities.LevelVehicle;
import com.azhar.VehicleParker.Entities.Vehicle.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LevelDao {

    @Autowired
    Database database;

    public List<Level> getLevelList(){
        return database.getLevelList();
    }

    public List<LevelSpace> getAvailableSpace(){
        return database.getAvailableSpace();
    }


    public List<Vehicle> getVehicleList() {
        return database.getVehicleList();
    }
    
    public LevelVehicle fillSlot(int availableLevelNumber, int type) {
        return database.fillSlot(availableLevelNumber,type);
    }

    public List<LevelVehicle> getLevelVehicleList() {
        return database.getLevelVehicleList();
    }

    public Boolean emptySlot(LevelVehicle levelVehicle) {
        return database.emptySlot(levelVehicle);
    }
}
