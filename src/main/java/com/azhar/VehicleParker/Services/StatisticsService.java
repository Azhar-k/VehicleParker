package com.azhar.VehicleParker.Services;

import com.azhar.VehicleParker.Entities.ApiResponses.StatisticsResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public interface StatisticsService {
    public StatisticsResponse getAmountByDate(LocalDate localDate);
    public StatisticsResponse countOfParkedVehiclesByDateAndType(LocalDate localDate,String vehicleType);
}
