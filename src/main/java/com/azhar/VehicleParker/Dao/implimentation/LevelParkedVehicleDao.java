package com.azhar.VehicleParker.Dao.implimentation;

import com.azhar.VehicleParker.Entities.LevelParkedVehicle;
import com.azhar.VehicleParker.dbclient.LevelParkedVehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LevelParkedVehicleDao implements com.azhar.VehicleParker.Dao.LevelParkedVehicleDao {
    @Autowired
    LevelParkedVehicleRepository levelParkedVehicleRepository;

    @Override
    public LevelParkedVehicle insert(LevelParkedVehicle levelParkedVehicle) {
       return levelParkedVehicleRepository.save(levelParkedVehicle);
    }

    @Override
    public List<LevelParkedVehicle> getLevelParkedVehicleList() {

        return levelParkedVehicleRepository.findAll();
    }

    @Override
    public void delete(LevelParkedVehicle levelParkedVehicle) {
        levelParkedVehicleRepository.delete(levelParkedVehicle);
    }

    @Override
    public LevelParkedVehicle getLevelParkedVehicleById(int levelParkedVehicleId) {
        return levelParkedVehicleRepository.findLevelParkedVehicleById(levelParkedVehicleId);
    }
}
