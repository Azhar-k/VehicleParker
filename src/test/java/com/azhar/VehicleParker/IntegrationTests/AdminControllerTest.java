package com.azhar.VehicleParker.IntegrationTests;

import com.azhar.VehicleParker.Dao.implimentation.VehicleDao;
import com.azhar.VehicleParker.db.models.Vehicle.Vehicle;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

//This class is incomplete
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AdminControllerTest {

    @Autowired
    MockMvc mockMvc;



    @Before
    public void setup() {

    }


    @Test
    public void testGetVehicles() throws Exception {

        mockMvc.perform(get("/vehicles"))
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[0].name").value("car"))
                .andExpect(jsonPath("$[0].parkingRate").value(25))
                .andExpect(jsonPath("$[1].id").value("2"))
                .andExpect(jsonPath("$[1].name").value("bus"))
                .andExpect(jsonPath("$[1].parkingRate").value(40))
                .andExpect(jsonPath("$[2].id").value("3"))
                .andExpect(jsonPath("$[2].name").value("van"))
                .andExpect(jsonPath("$[2].parkingRate").value(20))
                .andExpect(jsonPath("$[3].id").value("4"))
                .andExpect(jsonPath("$[3].name").value("bike"))
                .andExpect(jsonPath("$[3].parkingRate").value(10))
                .andExpect(jsonPath("$[4].id").value("5"))
                .andExpect(jsonPath("$[4].name").value("truck"))
                .andExpect(jsonPath("$[4].parkingRate").value(70))
                .andExpect(jsonPath("$[5].id").value("6"))
                .andExpect(jsonPath("$[5].name").value("container"))
                .andExpect(jsonPath("$[5].parkingRate").value(100));
    }

    @Test
    public void testAddVehicleGivenExistingVehicle() throws Exception {
        mockMvc.perform(post("/vehicles")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"name\": \"car\" , \"parkingRate\":20 }")
        ).andExpect(jsonPath("$.message").value("vehicle already exist"));

    }

    @Test
    public void testAddVehicleGivenNewVehicle() throws Exception {

        mockMvc.perform(post("/vehicles")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"name\": \"jeep\" , \"parkingRate\":25 }")
        ).andExpect(jsonPath("$.message").value("vehicle added"));

        //delete the vehicle added for testing
        mockMvc.perform(MockMvcRequestBuilders.delete("/vehicles")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content("{ \"name\": \"jeep\" }"));

    }

    @Test
    public void testEditVehicleGivenExistingVehicle() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/vehicles")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content("{ \"name\": \"car\" , \"parkingRate\":25 }")
        ).andExpect(jsonPath("$.message").value("vehicle edited"));

    }
    @Test
    public void testEditVehicleGivenNewVehicle() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/vehicles")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content("{ \"name\": \"train\" , \"parkingRate\":25 }")
        ).andExpect(jsonPath("$.message").value("vehicle do not exist"));

    }

    @Test
    @Ignore
    public void testDeleteVehicleGivenExistingVehicle() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete("/vehicles")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content("{ \"name\": \"jeep\" }")
        ).andExpect(jsonPath("$.message").value("vehicle deleted"));

    }

    @Test
    public void testDeleteVehicleGivenNewVehicle() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/vehicles")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content("{ \"name\": \"train\" }")
        ).andExpect(jsonPath("$.message").value("vehicle do not exist"));

    }


    @Test
    public void testAddLevelGivenNewLevel() throws Exception {
        mockMvc.perform(post("/levels")
                .contentType(MediaType.APPLICATION_JSON)
                .content(" {\n" +
                        "       \n" +
                        "        \"levelNumber\": 100,\n" +
                        "        \"allowedVehicles\": [\n" +
                        "            {\n" +
                        "                \n" +
                        "                \"vehicle\": {\n" +
                        "                    \n" +
                        "                    \"name\": \"bike\"\n" +
                        "                },\n" +
                        "                \"occupiedSlots\": 0,\n" +
                        "                \"max_SLOTS\": 15\n" +
                        "            }\n" +
                        "        ]\n" +
                        " \t\n" +
                        " }")
        ).andExpect(jsonPath("$.message").value("Level added"));
        //delete the level added for testing
        mockMvc.perform(delete("/levels")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"levelNumber\": 100}")
        ).andExpect(jsonPath("$.message").value("Level deleted"));

    }

    @Test
    public void testAddLevelGivenExistingLevel() throws Exception {
        mockMvc.perform(post("/levels")
                .contentType(MediaType.APPLICATION_JSON)
                .content(" {\n" +
                        "       \n" +
                        "        \"levelNumber\": 1,\n" +
                        "        \"allowedVehicles\": [\n" +
                        "            {\n" +
                        "                \n" +
                        "                \"vehicle\": {\n" +
                        "                    \n" +
                        "                    \"name\": \"bike\"\n" +
                        "                },\n" +
                        "                \"occupiedSlots\": 0,\n" +
                        "                \"max_SLOTS\": 15\n" +
                        "            }\n" +
                        "        ]\n" +
                        " \t\n" +
                        " }")
        ).andExpect(jsonPath("$.message").value("level already exist"));

    }

    @Test
    @Ignore
    public void testEditLevelGivenExistingLevel() throws Exception {
        mockMvc.perform(put("/levels")
                .contentType(MediaType.APPLICATION_JSON)
                .content(" {\n" +
                        "       \n" +
                        "        \"levelNumber\": 100,\n" +
                        "        \"allowedVehicles\": [\n" +
                        "            {\n" +
                        "                \n" +
                        "                \"vehicle\": {\n" +
                        "                    \n" +
                        "                    \"name\": \"bike\"\n" +
                        "                },\n" +
                        "                \"occupiedSlots\": 0,\n" +
                        "                \"max_SLOTS\": 15\n" +
                        "            }\n" +
                        "        ]\n" +
                        " \t\n" +
                        " }")
        ).andExpect(jsonPath("$.message").value("Level edited"));

    }
    @Test
    public void testEditLevelGivenNewLevel() throws Exception {
        mockMvc.perform(put("/levels")
                .contentType(MediaType.APPLICATION_JSON)
                .content(" {\n" +
                        "       \n" +
                        "        \"levelNumber\": 1000,\n" +
                        "        \"allowedVehicles\": [\n" +
                        "            {\n" +
                        "                \n" +
                        "                \"vehicle\": {\n" +
                        "                    \n" +
                        "                    \"name\": \"bike\"\n" +
                        "                },\n" +
                        "                \"occupiedSlots\": 0,\n" +
                        "                \"max_SLOTS\": 15\n" +
                        "            }\n" +
                        "        ]\n" +
                        " \t\n" +
                        " }")
        ).andExpect(jsonPath("$.message").value("input Level does not exist"));

    }

    @Test
    @Ignore
    public void testDeleteLevelGivenExistingLevel() throws Exception {
        mockMvc.perform(delete("/levels")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"levelNumber\": 100}")
        ).andExpect(jsonPath("$.message").value("Level deleted"));

    }
    @Test
    public void testDeleteLevelGivenNewLevel() throws Exception {
        mockMvc.perform(delete("/levels")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"levelNumber\": 100}")
        ).andExpect(jsonPath("$.message").value("input Level does not exist"));

    }

}
