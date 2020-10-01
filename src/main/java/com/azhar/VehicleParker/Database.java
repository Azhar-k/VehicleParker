package com.azhar.VehicleParker;


import com.azhar.VehicleParker.Entities.Building.Level;
import com.azhar.VehicleParker.Entities.Building.LevelCapacity;
import com.azhar.VehicleParker.Entities.Building.LevelSpace;
import com.azhar.VehicleParker.Entities.Vehicle.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Database {
    private static List<Level> levelList = new ArrayList<Level>();
    private static List<Vehicle> vehicleList = new ArrayList<Vehicle>();


    public void loadData() {
        loadLevels();
        //loadVehicles();
    }

    private void loadLevels() {
        levelList.add(new Level(0, new LevelCapacity(new Car(1, 15), new Bus(2, 15), new Bike(3, 15), new Van(4, 15))));
        levelList.add(new Level(1, new LevelCapacity(new Car(1, 15), new Bus(2, 15), new Bike(3, 15), new Van(4, 15))));
        levelList.add(new Level(2, new LevelCapacity(new Car(1, 15), new Bus(2, 15), new Bike(3,  15), new Van(4, 15))));
        levelList.add(new Level(3, new LevelCapacity(new Car(1, 15), new Bus(2, 15), new Bike(3,  15), new Van(4, 15))));
        levelList.add(new Level(4, new LevelCapacity(new Car(1, 15), new Bus(2, 15), new Bike(3,  15), new Van(4, 15))));
    }

//    private void loadVehicles() {
//        vehicleList.add(new Car(1, "car"));
//        vehicleList.add(new Bus(2, "bus"));
//        vehicleList.add(new Van(3, "van"));
//        vehicleList.add(new Bike(4, "bike"));
//    }

    public List<LevelSpace> getAvailableSpace() {
        List<LevelSpace> availableSpace = new ArrayList<LevelSpace>();
        for(Level level: getLevelList()){
            int levelNumber=level.getLevelNumber();
            int availableCarSpace = level.getLevelCapacity().getCarCapacity().getMAX_SLOTS()-level.getLevelCapacity().getCarCapacity().getOccupiedSlots();
            int availableBusSpace = level.getLevelCapacity().getBusCapacity().getMAX_SLOTS()-level.getLevelCapacity().getBusCapacity().getOccupiedSlots();
            int availableBikeSpace = level.getLevelCapacity().getBikeCapacity().getMAX_SLOTS()-level.getLevelCapacity().getBikeCapacity().getOccupiedSlots();
            int availableVanSpace = level.getLevelCapacity().getVanCapacity().getMAX_SLOTS()-level.getLevelCapacity().getVanCapacity().getOccupiedSlots();
            availableSpace.add(new LevelSpace(levelNumber,availableCarSpace,availableBusSpace,availableVanSpace,availableBikeSpace));

        }
        return availableSpace;
    }


    public List<Level> getLevelList() {

        return levelList;
    }

    public List<Vehicle> getVehicleList() {
        return vehicleList;
    }

}


