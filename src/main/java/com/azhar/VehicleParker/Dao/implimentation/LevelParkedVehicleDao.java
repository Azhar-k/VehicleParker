package com.azhar.VehicleParker.Dao.implimentation;

import com.azhar.VehicleParker.db.models.LevelParkedVehicle;
import com.azhar.VehicleParker.db.repositories.LevelParkedVehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
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

    @Override
    public List<LocalDate> getDistinctDate() {
        return levelParkedVehicleRepository.findDistinctDate();
    }

    @Override
    public int findCountByDateAndVehicleName(String vehicleName, LocalDate localDate) {
        return levelParkedVehicleRepository.findCountByDateAndVehicleName(vehicleName, localDate);
    }
}
