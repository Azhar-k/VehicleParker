package com.azhar.VehicleParker;

import com.azhar.VehicleParker.controllers.ParkingController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class VehicleParkerApplicationTests {

    @Autowired
    ParkingController parkingController;


    @Test
    void contextLoads() {
        assertThat(parkingController).isNotNull();
    }

}
