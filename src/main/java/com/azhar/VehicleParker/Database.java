package com.azhar.VehicleParker;


import com.azhar.VehicleParker.Entities.Level.Level;
import com.azhar.VehicleParker.Entities.Level.LevelCapacity;
import com.azhar.VehicleParker.Entities.LevelVehicle;
import com.azhar.VehicleParker.Entities.Vehicle.Vehicle;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class Database {
    private static List<Level> levelList = new ArrayList<Level>();
    private static List<Vehicle> vehicleList = new ArrayList<Vehicle>();
    private static List<LevelVehicle> levelVehicleMapList = new ArrayList<LevelVehicle>();

    public Database() {

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
        levelList.add(new Level(6, new LevelCapacity(1, 5, 30, 10)));
    }

    private void loadVehicles() {
        vehicleList.add(new Vehicle(1, "car"));
        vehicleList.add(new Vehicle(2, "bus"));
        vehicleList.add(new Vehicle(3, "van"));
        vehicleList.add(new Vehicle(4, "bike"));
    }



    public int fillSlot(LevelVehicle levelVehicleMap){

        //A vehicle is to be parked.

        int levelNumber = levelVehicleMap.getLevelNumber();
        String vehicleType = levelVehicleMap.getVehicleType();

        int currentOccupiedSlot;
        int updatedOccupiedSlot;
        int levelVehicleMapid = -1;

        for(Level level:getLevelList()){
            if (level.getLevelNumber()==levelNumber){
                switch (vehicleType){
                    case "car":
                        //occupiedslot is incremented
                        currentOccupiedSlot = level.getLevelCapacity().getOccupied_car_slots();
                        updatedOccupiedSlot = currentOccupiedSlot+1;
                        level.getLevelCapacity().setOccupied_car_slots(updatedOccupiedSlot);
                        break;
                    case "bus":
                        currentOccupiedSlot=level.getLevelCapacity().getOccupied_bus_slots();
                        updatedOccupiedSlot = currentOccupiedSlot+1;
                        level.getLevelCapacity().setOccupied_bus_slots(updatedOccupiedSlot);

                        break;
                    case "bike":
                        currentOccupiedSlot = level.getLevelCapacity().getOccupied_bike_slots();
                        updatedOccupiedSlot = currentOccupiedSlot+1;
                        level.getLevelCapacity().setOccupied_bike_slots(updatedOccupiedSlot);

                        break;
                    case "van":
                        currentOccupiedSlot=level.getLevelCapacity().getOccupied_van_slots();
                        updatedOccupiedSlot = currentOccupiedSlot+1;
                        level.getLevelCapacity().setOccupied_van_slots(updatedOccupiedSlot);
                        break;
                    default:
                        break;
                }
                // levelVehicle instance corresponding to the parking is added to database and \
                // a unique id for the parking is retrieved
                levelVehicleMapid = addLevelVehicleMap(levelVehicleMap);
                break;
            }
        }

        return levelVehicleMapid;

    }

    public int addLevelVehicleMap(LevelVehicle levelVehicleMap){
        int id = getUniquieVehicleMapId();
        levelVehicleMap.setId(id);
        levelVehicleMapList.add(levelVehicleMap);
        return id;
    }
    //vehicle is unparked by using the unique id of levelVehicle instance
    public boolean emptySlot(LevelVehicle levelVehicleMap){

        levelVehicleMap=getLevelVehicleMap(levelVehicleMap.getId());

        int levelNumber = levelVehicleMap.getLevelNumber();
        String vehicleType = levelVehicleMap.getVehicleType();

        int updatedOccupiedSlot;
        boolean isSlotEmptied=false;

        for(Level level:getLevelList()){
            if (level.getLevelNumber()==levelNumber){
                switch (vehicleType){

                    case "car":
                        updatedOccupiedSlot = level.getLevelCapacity().getOccupied_car_slots()-1;
                        level.getLevelCapacity().setOccupied_car_slots(updatedOccupiedSlot);
                        isSlotEmptied=true;
                        break;
                    case "bus":
                        updatedOccupiedSlot = level.getLevelCapacity().getOccupied_bus_slots()-1;
                        level.getLevelCapacity().setOccupied_bus_slots(updatedOccupiedSlot);
                        isSlotEmptied=true;
                        System.out.println("inside database switch");
                        break;
                    case "bike":
                        updatedOccupiedSlot = level.getLevelCapacity().getOccupied_bike_slots()-1;
                        level.getLevelCapacity().setOccupied_bike_slots(updatedOccupiedSlot);
                        isSlotEmptied=true;
                        break;
                    case "van":
                        updatedOccupiedSlot = level.getLevelCapacity().getOccupied_van_slots()-1;
                        level.getLevelCapacity().setOccupied_van_slots(updatedOccupiedSlot);
                        isSlotEmptied=true;
                        break;
                    default:
                        break;
                }

                //levelVehicle instance is removed from database.
                removeLevelVehicle(levelVehicleMap);
                break;
            }
        }

        return isSlotEmptied;
    }
    public void removeLevelVehicle(LevelVehicle levelVehicle){
        for(LevelVehicle lvm:getLevelVehicleMapList()){
            if(lvm.getId()==levelVehicle.getId()){
                getLevelVehicleMapList().remove(lvm);
                break;
            }
        }
    }

    private LevelVehicle getLevelVehicleMap(int id) {
        //retrieve a levelVehicle instance by using its id.
        for (LevelVehicle levelVehicleMap : getLevelVehicleMapList()){
            if(levelVehicleMap.getId()==id){
                return levelVehicleMap;
            }
        }
        return null;
    }
    private int getUniquieVehicleMapId(){
        Random random = new Random();

        while(true){
            int x = random.nextInt(900) + 100;
            if(!isLevelVehicleMapIdExist(x)){
                return x;
            }
        }

    }
    public boolean isLevelVehicleMapIdExist(int id){

        for (LevelVehicle levelVehicleMap : getLevelVehicleMapList()){
            if(levelVehicleMap.getId()==id){
                return true;
            }
        }
        return false;
    }


    public List<Level> getLevelList() {

        return levelList;
    }

    public List<Vehicle> getVehicleList() {
        return vehicleList;
    }

    public static List<LevelVehicle> getLevelVehicleMapList() {
        return levelVehicleMapList;
    }


}


