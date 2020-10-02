package com.azhar.VehicleParker.Dao;

import com.azhar.VehicleParker.Database;
import com.azhar.VehicleParker.Entities.Level.LevelSpace;
import com.azhar.VehicleParker.Entities.Level.Level;
import com.azhar.VehicleParker.Entities.LevelVehicle;
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

    public int fillSlot(LevelVehicle levelVehicleMap) throws Exception {

        int levelVehicleMapId = database.fillSlot(levelVehicleMap);
        if(levelVehicleMapId==-1){
            //parking rejected by databse
            throw new Exception("database error occured");
        }
        //vehicle parked and an id for the parking is returned by databse
        return levelVehicleMapId;
    }

    public boolean isLevelVehiclMapIdExist(int id){
        return database.isLevelVehicleMapIdExist(id);
    }

    public boolean emptySlot(LevelVehicle levelVehicleMap) {
        return database.emptySlot(levelVehicleMap);
    }

    @Override
    public List<LevelVehicle> getLevelVehicleList() {
        return database.getLevelVehicleList();
    }


}
