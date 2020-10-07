package com.azhar.VehicleParker.Services;

import com.azhar.VehicleParker.Entities.ApiResponses.StatisticsResponse;
import com.azhar.VehicleParker.Entities.Statistics;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface StatisticsService {
    public StatisticsResponse getAmountByDate(LocalDate localDate);

    public StatisticsResponse countOfParkedVehiclesByDateAndType(LocalDate localDate, String vehicleType);

    public List<Statistics> getStatistics();
}
