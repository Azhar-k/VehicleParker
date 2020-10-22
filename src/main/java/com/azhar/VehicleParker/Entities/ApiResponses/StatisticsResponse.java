package com.azhar.VehicleParker.Entities.ApiResponses;

import com.azhar.VehicleParker.Entities.Statistics;

import java.util.List;

//Used to provide responses for all services of StatisticsService
public class StatisticsResponse extends Response {
    private List<Statistics> statisticsList;
    public StatisticsResponse(boolean isSucces, String message) {
        super(isSucces, message);
    }

    public StatisticsResponse(boolean isSucces, String message,List<Statistics> statisticsList) {
        super(isSucces,message);
        this.statisticsList = statisticsList;

    }
}
