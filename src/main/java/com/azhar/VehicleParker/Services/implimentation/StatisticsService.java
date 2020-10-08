package com.azhar.VehicleParker.Services.implimentation;

import com.azhar.VehicleParker.Dao.LevelParkedVehicleDao;
import com.azhar.VehicleParker.Dao.VehicleDao;
import com.azhar.VehicleParker.Entities.ApiResponses.StatisticsResponse;
import com.azhar.VehicleParker.Entities.Statistics;
import com.azhar.VehicleParker.Entities.VehicleStatByType;
import com.azhar.VehicleParker.db.models.LevelParkedVehicle;
import com.azhar.VehicleParker.db.models.Vehicle.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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
            if (levelParkedVehicle.getDate().compareTo(localDate)==0) {
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
                if (levelParkedVehicle.getDate().compareTo(localDate)==0 && levelParkedVehicle.getVehicleName().equals(vehicleType)) {
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

    @Override
    public List<Statistics> getStatistics() {
        List<Statistics> statisticsList=new ArrayList<Statistics>();
        for(LocalDate localDate:levelParkedVehicleDao.getDistinctDate()){
            //Statistics is created for each unique localDate in levelParkedVehicle table
            Statistics statistics=new Statistics(localDate);
            for(Vehicle vehicle:vehicleDao.getVehicleList()){
                //details for each vehicle in database for a given date is calculated
                String vehicleName=vehicle.getName();
                int countOfVehicle=levelParkedVehicleDao.findCountByDateAndVehicleName(vehicleName,localDate);
                VehicleStatByType vehicleStatByType=new VehicleStatByType(countOfVehicle,vehicle);//total amount is setup implicitely
                statistics.addVehicleStatByType(vehicleStatByType);
            }
            statistics.getVehicleStatByTypeList().sort(new sortByAmount());
            statisticsList.add(statistics);
        }

        return statisticsList;
    }

    public boolean isVehicleValid(String vehicleName) {
        boolean isVehicleValid = true;
        if (vehicleDao.getVehicleByName(vehicleName) == null) {
            isVehicleValid = false;
        }
        return isVehicleValid;
    }

    public class sortByAmount implements Comparator<VehicleStatByType>{

        @Override
        public int compare(VehicleStatByType o1, VehicleStatByType o2) {
            return o2.getTotalAmount()-o1.getTotalAmount();
        }
    }
}
