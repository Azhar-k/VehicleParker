package com.azhar.VehicleParker.Dao;

import com.azhar.VehicleParker.Entities.Building.Level;
import com.azhar.VehicleParker.Entities.Building.LevelSpace;
import com.azhar.VehicleParker.Entities.LevelVehicleMap;
import com.azhar.VehicleParker.Entities.Vehicle.Vehicle;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface LevelDao {
    public List<Level> getLevelList();
    public List<LevelSpace> getAvailableSpace();
    public List<Vehicle> getVehicleList();
    public int fillSlot(LevelVehicleMap levelVehicleMap);
    public boolean isLevelVehiclMapIdExist(int id);
    public boolean emptySlot(LevelVehicleMap levelVehicleMap);
}
