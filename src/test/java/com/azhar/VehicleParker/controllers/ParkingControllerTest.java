package com.azhar.VehicleParker.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class ParkingControllerTest {
    //public modifier for the class hasbeen removed to solve junit vintage error
    @Autowired
    MockMvc mockMvc;



    @BeforeEach
    public void initEach() throws Exception {
        //park a single vehicle for testing some use cases.
        mockMvc.perform(post("/park")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"vehicleName\": \"car\" , \"vehicleNumber\":\"KL 11 BC 5978\" }")
        );//this vehicle is parked in level 2 always.
    }

    @Test
    public void parkGivenValidVEhicle() throws Exception {
        mockMvc.perform(post("/park")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"vehicleName\": \"car\" , \"vehicleNumber\":\"KL 10 BC 1234\" }")
        ).andExpect(jsonPath("$.message").value("vehicle parked"));
    }

    @Test
    public void parkGivenInValidVEhicle() throws Exception {
        mockMvc.perform(post("/park")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"vehicleName\": \"train\" , \"vehicleNumber\":\"KL 10 BC 1234\" }")
        ).andExpect(jsonPath("$.message").value("This vehicle can not be parked here"));
    }

    @Test
    public void parkGivenAlreadyParkedVEhicle() throws Exception {
        //we have already parked the vehicle KL 11 BC 5978 using beforeEach method.
        mockMvc.perform(post("/park")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"vehicleName\": \"car\" , \"vehicleNumber\":\"KL 11 BC 5978\" }")
        ).andExpect(jsonPath("$.message").value("This vehicle is already parked"));
    }

    @Test
    public void parkVehicleUntillSpaceIsFull() throws Exception {
        //only 5 slots are available for container
        for(int i=0;i<5;i++){
            mockMvc.perform(post("/park")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{ \"vehicleName\": \"container\" , \"vehicleNumber\":\"KL 10 AC 697"+i+"\" }")
            );
        }//5 containers have been parked

        mockMvc.perform(post("/park")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"vehicleName\": \"container\" , \"vehicleNumber\":\"KL 10 AC 6978\" }")
        ).andExpect(jsonPath("$.message").value("Parking Space is Full for container"));
    }

    //add tests for unparking

}
