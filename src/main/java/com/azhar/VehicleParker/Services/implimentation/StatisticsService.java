package com.azhar.VehicleParker.Services.implimentation;

import com.azhar.VehicleParker.Dao.LevelParkedVehicleDao;
import com.azhar.VehicleParker.Dao.VehicleDao;
import com.azhar.VehicleParker.Entities.ApiResponses.StatisticsResponse;
import com.azhar.VehicleParker.db.entities.LevelParkedVehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class StatisticsService implements com.azhar.VehicleParker.Services.StatisticsService {
    @Autowired
    LevelParkedVehicleDao levelParkedVehicleDao;
    @Autowired
    VehicleDao vehicleDao;

    @Override
    public StatisticsResponse getAmountByDate(LocalDate localDate) {
        int amount = 0;
        for (LevelParkedVehicle levelParkedVehicle : levelParkedVehicleDao.getLevelParkedVehicleList()) {
            if (levelParkedVehicle.getDate().equals(localDate)) {
                String vehicleName = levelParkedVehicle.getVehicleName();
                int vehiclerate = vehicleDao.getVehicleByName(vehicleName).getParkingRate();
                amount += vehiclerate;
            }
        }
        return new StatisticsResponse(true, "Total amount collected for the date is : " + amount);
    }

    @Override
    public StatisticsResponse countOfParkedVehiclesByDateAndType(LocalDate localDate, String vehicleType) {
        StatisticsResponse statisticsResponse = null;
        if (isVehicleValid(vehicleType)) {
            int count = 0;
            for (LevelParkedVehicle levelParkedVehicle : levelParkedVehicleDao.getLevelParkedVehicleList()) {
                if (levelParkedVehicle.getDate().equals(localDate) && levelParkedVehicle.getVehicleName().equals(vehicleType)) {
                    count += 1;
                }
            }
            String resposeMessage = "" + count + " " + vehicleType + "s" + " are parked on " + localDate;
            statisticsResponse = new StatisticsResponse(true, resposeMessage);
        } else {
            String resposeMessage = "vehicle not found";
            statisticsResponse = new StatisticsResponse(true, resposeMessage);
        }

        return statisticsResponse;
    }

    public boolean isVehicleValid(String vehicleName) {
        boolean isVehicleValid = true;
        if (vehicleDao.getVehicleByName(vehicleName) == null) {
            isVehicleValid = false;
        }
        return isVehicleValid;
    }
}
