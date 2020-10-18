package com.azhar.VehicleParker.controllers;

import com.azhar.VehicleParker.Entities.ApiResponses.StatisticsResponse;
import com.azhar.VehicleParker.Entities.Statistics;
import com.azhar.VehicleParker.services.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.time.LocalDate;
import java.util.List;

@RestController
public class StatisticsController {
    @Autowired
    StatisticsService statisticsService;

    //to make constraint violation excption to return a HTTP status 400(Bad request). Refer note
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException e) {
        return new ResponseEntity<>("not valid due to validation error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }

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
