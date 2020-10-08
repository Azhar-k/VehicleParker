package com.azhar.VehicleParker;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

//This class is incomplete
@SpringBootTest
@AutoConfigureMockMvc
public class AdminControllerTest extends  VehicleParkerApplicationTests{

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setup() {

    }

    @Test
    public void testGetVehicles() throws Exception  {
        mockMvc.perform(get("/getVehicles"))
                .andExpect(jsonPath("$[0].id").value("0"))
                .andExpect(jsonPath("$[0].name").value("car"))
                .andExpect(jsonPath("$[0].parkingrate").value(20));
    }
}
