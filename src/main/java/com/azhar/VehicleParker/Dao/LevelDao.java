package com.azhar.VehicleParker.Dao;

import com.azhar.VehicleParker.Entities.Level.Level;
import com.azhar.VehicleParker.Entities.Level.LevelSpace;
import com.azhar.VehicleParker.Entities.LevelVehicle;
import com.azhar.VehicleParker.Entities.Vehicle.Vehicle;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface LevelDao {
    public List<Level> getLevelList();
    public List<LevelSpace> getAvailableSpace();
    public List<Vehicle> getVehicleList();
    public int fillSlot(LevelVehicle levelVehicleMap) throws Exception;
    public boolean isLevelVehiclMapIdExist(int id);
    public boolean emptySlot(LevelVehicle levelVehicleMap);
    public  List<LevelVehicle> getLevelVehicleList();
}
