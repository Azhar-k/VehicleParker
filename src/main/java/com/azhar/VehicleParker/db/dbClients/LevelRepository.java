package com.azhar.VehicleParker.db.dbClients;


import com.azhar.VehicleParker.db.entities.Building.Level;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface LevelRepository extends JpaRepository<Level, Integer> {
    public Level findLevelByLevelNumber(int levelNumber);

    public List<Level> findByOrderByLevelNumberAsc();
}
