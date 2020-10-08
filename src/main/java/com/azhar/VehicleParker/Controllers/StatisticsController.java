package com.azhar.VehicleParker.Controllers;

import com.azhar.VehicleParker.Entities.ApiResponses.StatisticsResponse;
import com.azhar.VehicleParker.Entities.Statistics;
import com.azhar.VehicleParker.UnitTests.Services.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class StatisticsController {
    @Autowired
    StatisticsService statisticsService;

    @GetMapping("/getAmountByDate/{localDate}")
    public StatisticsResponse getAmountByDate(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate localDate) {

        return statisticsService.getAmountByDate(localDate);
    }

    @GetMapping("/countBydateAndType/{localDate}/{vehicleType}")
    public StatisticsResponse getCountByDateAndTime(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate localDate, @PathVariable String vehicleType) {
        return statisticsService.countOfParkedVehiclesByDateAndType(localDate, vehicleType);
    }

    @GetMapping("/statistics")
    public List<Statistics> getStatistics() {
        return statisticsService.getStatistics();
    }
}
