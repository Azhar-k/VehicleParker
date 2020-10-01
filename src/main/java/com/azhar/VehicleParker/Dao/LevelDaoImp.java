package com.azhar.VehicleParker.Dao;

import com.azhar.VehicleParker.Database;
import com.azhar.VehicleParker.Entities.Building.LevelSpace;
import com.azhar.VehicleParker.Entities.Building.Level;
import com.azhar.VehicleParker.Entities.LevelVehicleMap;
import com.azhar.VehicleParker.Entities.Vehicle.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class LevelDaoImp implements LevelDao{

    @Autowired
    Database database ;



    public List<Level> getLevelList(){
        return database.getLevelList();
    }

    public List<LevelSpace> getAvailableSpace() {
        List<LevelSpace> availableSpace = new ArrayList<LevelSpace>();
        for(Level level: getLevelList()){
            int levelNumber=level.getLevelNumber();
            int availableCarSpace = level.getLevelCapacity().getMAX_NUMBER_OF_CAR()-level.getLevelCapacity().getOccupied_car_slots();
            int availableBusSpace = level.getLevelCapacity().getMAX_NUMBER_OF_BUS()-level.getLevelCapacity().getOccupied_bus_slots();
            int availableBikeSpace = level.getLevelCapacity().getMAX_NUMBER_OF_BIKE()-level.getLevelCapacity().getOccupied_bike_slots();
            int availableVanSpace = level.getLevelCapacity().getMAX_NUMBER_OF_VAN()-level.getLevelCapacity().getOccupied_van_slots();

            availableSpace.add(new LevelSpace(levelNumber,availableCarSpace,availableBusSpace,availableVanSpace,availableBikeSpace));

        }
        return availableSpace;
    }


    public List<Vehicle> getVehicleList() {
        return database.getVehicleList();
    }

    public int fillSlot(LevelVehicleMap levelVehicleMap) {
        return database.fillSlot(levelVehicleMap);
    }

    public boolean isLevelVehiclMapIdExist(int id){
        return database.isLevelVehicleMapIdExist(id);
    }

    public boolean emptySlot(LevelVehicleMap levelVehicleMap) {
        return database.emptySlot(levelVehicleMap);
    }


}
