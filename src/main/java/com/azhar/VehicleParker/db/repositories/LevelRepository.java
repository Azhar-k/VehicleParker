package com.azhar.VehicleParker.db.repositories;


import com.azhar.VehicleParker.db.models.Building.Level;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface LevelRepository extends JpaRepository<Level, Integer> {
    Level findLevelByLevelNumber(int levelNumber);

    List<Level> findByOrderByLevelNumberAsc();
}
