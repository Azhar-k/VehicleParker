package com.azhar.VehicleParker.Dao;


import com.azhar.VehicleParker.Entities.Building.LevelSpace;
import com.azhar.VehicleParker.Entities.LevelParkedVehicle;
import com.azhar.VehicleParker.Entities.Vehicle.Vehicle;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface LevelDao {
    public List<LevelSpace> getAvailableSpace();
    public List<Vehicle> getVehicleList();
    public LevelParkedVehicle fillSlot(int availableLevelNumber, int type);
    public boolean emptySlot(LevelParkedVehicle levelVehicleMap);
    public  List<LevelParkedVehicle> getLevelParkedVehicleList();
}
