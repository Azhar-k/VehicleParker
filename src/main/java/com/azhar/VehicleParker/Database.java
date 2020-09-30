package com.azhar.VehicleParker;


import com.azhar.VehicleParker.Entities.Building.Level;
import com.azhar.VehicleParker.Entities.Building.LevelCapacity;
import com.azhar.VehicleParker.Entities.Building.LevelSpace;
import com.azhar.VehicleParker.Entities.Vehicle.Vehicle;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Database {
    private List<Level> levelList = new ArrayList<Level>();
    private List<Vehicle> vehicleList = new ArrayList<Vehicle>();
    private List<LevelSpace> availableSpace = new ArrayList<LevelSpace>();

    public Database() {
        loadData();
    }

    public void loadData() {
        loadLevels();
        loadVehicles();
    }

    private void loadLevels() {
        levelList.add(new Level(0, new LevelCapacity(15, 5, 30, 10)));
        levelList.add(new Level(1, new LevelCapacity(10, 3, 40, 10)));
        levelList.add(new Level(2, new LevelCapacity(20, 4, 20, 10)));
        levelList.add(new Level(3, new LevelCapacity(30, 2, 50, 10)));
        levelList.add(new Level(4, new LevelCapacity(10, 5, 10, 10)));
        levelList.add(new Level(5, new LevelCapacity(10, 2, 30, 10)));
        levelList.add(new Level(6, new LevelCapacity(5, 5, 30, 10)));
    }

    private void loadVehicles() {
        vehicleList.add(new Vehicle(1, "car"));
        vehicleList.add(new Vehicle(2, "bus"));
        vehicleList.add(new Vehicle(3, "van"));
        vehicleList.add(new Vehicle(4, "bike"));
    }

    public List<LevelSpace> getAvailableSpace() {
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


    public List<Level> getLevelList() {

        return levelList;
    }

    public List<Vehicle> getVehicleList() {
        return vehicleList;
    }

}


