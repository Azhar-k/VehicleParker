package com.azhar.VehicleParker.Entities.ApiResponses;

//Used to provide responses for all services of StatisticsService
public class StatisticsResponse extends Response {

    public StatisticsResponse(boolean isSucces, String message) {
        super(isSucces, message);
    }
}
