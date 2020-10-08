package com.azhar.VehicleParker.UnitTests.Services;

import com.azhar.VehicleParker.Entities.ApiResponses.StatisticsResponse;
import com.azhar.VehicleParker.Entities.Statistics;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface StatisticsService {
    StatisticsResponse getAmountByDate(LocalDate localDate);

    StatisticsResponse countOfParkedVehiclesByDateAndType(LocalDate localDate, String vehicleType);

    List<Statistics> getStatistics();
}
