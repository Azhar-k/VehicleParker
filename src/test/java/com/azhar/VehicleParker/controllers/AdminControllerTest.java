package com.azhar.VehicleParker.controllers;


import com.azhar.VehicleParker.Dao.LevelParkedVehicleDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class AdminControllerTest {
    //public modifier for the class hasbeen removed to solve junit vintage error
    @Autowired
    MockMvc mockMvc;

    @Test
    public void testGetVehicles() throws Exception {

        mockMvc.perform(get("/vehicles"))
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[0].name").value("car"))
                .andExpect(jsonPath("$[0].parkingRate").value(20    ))
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

    }

    @Test
    public void testAddVehicleGivenInvalidParameter() throws Exception {
        mockMvc.perform(post("/vehicles")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"nae\": \"car\" , \"parkingRate\":20 }")
        ).andExpect(status().isBadRequest());
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
    public void testDeleteVehicleGivenExistingVehicle() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete("/vehicles")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content("{ \"name\": \"bike\" }")
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
                        "        \"number\": 100,\n" +
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

    }

    @Test
    public void testAddLevelGivenExistingLevel() throws Exception {
        mockMvc.perform(post("/levels")
                .contentType(MediaType.APPLICATION_JSON)
                .content(" {\n" +
                        "       \n" +
                        "        \"number\": 0,\n" +
                        "        \"allowedVehicles\": [\n" +
                        "            {\n" +
                        "                \n" +
                        "                \"vehicle\": {\n" +
                        "                    \n" +
                        "                    \"name\": \"van\"\n" +
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
    public void testAddLevelGivenInvalidVehicle() throws Exception {
        mockMvc.perform(post("/levels")
                .contentType(MediaType.APPLICATION_JSON)
                .content(" {\n" +
                        "       \n" +
                        "        \"number\": 300,\n" +
                        "        \"allowedVehicles\": [\n" +
                        "            {\n" +
                        "                \n" +
                        "                \"vehicle\": {\n" +
                        "                    \n" +
                        "                    \"name\": \"train\"\n" +
                        "                },\n" +
                        "                \"occupiedSlots\": 0,\n" +
                        "                \"max_SLOTS\": 15\n" +
                        "            }\n" +
                        "        ]\n" +
                        " \t\n" +
                        " }")
        ).andExpect(jsonPath("$.message").value("vehicle not valid"));

    }
    @Test
    public void testAddLevelGivenOccupiedSlotGreaterThanMaxSlot() throws Exception {
        mockMvc.perform(post("/levels")
                .contentType(MediaType.APPLICATION_JSON)
                .content(" {\n" +
                        "       \n" +
                        "        \"number\": 100,\n" +
                        "        \"allowedVehicles\": [\n" +
                        "            {\n" +
                        "                \n" +
                        "                \"vehicle\": {\n" +
                        "                    \n" +
                        "                    \"name\": \"bike\"\n" +
                        "                },\n" +
                        "                \"occupiedSlots\": 19,\n" +
                        "                \"max_SLOTS\": 15\n" +
                        "            }\n" +
                        "        ]\n" +
                        " \t\n" +
                        " }")
        ).andExpect(jsonPath("$.message").value("Occupied slot can not be greater than maximum slot"));

    }
    @Test
    public void testAddLevelGivenEmptyAllowedVehiclesList() throws Exception {
        mockMvc.perform(post("/levels")
                .contentType(MediaType.APPLICATION_JSON)
                .content(" {\n" +
                        "       \n" +
                        "        \"number\": 100,\n" +
                        "        \"allowedVehicles\": []\n" +
                        " \t\n" +
                        " }")
        ).andExpect(jsonPath("$.message").value("Level added"));

    }
    @Test
    @Disabled
    public void testAddLevelGivenLevelWithoutInputVehicle() throws Exception {
        mockMvc.perform(post("/levels")
                .contentType(MediaType.APPLICATION_JSON)
                .content(" {\n" +
                        "       \n" +
                        "        \"number\": 100,\n" +
                        "        \"allowedVehicles\": [\n" +
                        "            {\n" +
                        "                \"occupiedSlots\": 0,\n" +
                        "                \"max_SLOTS\": 15\n" +
                        "            }\n" +
                        "        ]\n" +
                        " \t\n" +
                        " }")
        ).andExpect(status().isBadRequest());

    }
    @Test
    @Disabled
    public void testAddLevelGivenLevelWithoutSlotAttribute() throws Exception {
        mockMvc.perform(post("/levels")
                .contentType(MediaType.APPLICATION_JSON)
                .content(" {\n" +
                        "       \n" +
                        "        \"number\": 100,\n" +
                        "        \"allowedVehicles\": [\n" +
                        "            {\n" +
                        "                \n" +
                        "                \"vehicle\": {\n" +
                        "                    \n" +
                        "                    \"name\": \"bike\"\n" +
                        "                },\n" +
                        "                \"jhvbhjgvbhjv: 0,\n" +
                        "                \"max_SLOTS\": 15\n" +
                        "            }\n" +
                        "        ]\n" +
                        " \t\n" +
                        " }")
        ).andExpect(status().isBadRequest());

    }

    @Test
    public void testEditLevelGivenExistingLevel() throws Exception {
        System.out.println();
        mockMvc.perform(put("/levels")
                .contentType(MediaType.APPLICATION_JSON)
                .content(" {\n" +
                        "       \n" +
                        "        \"number\": 5,\n" +
                        "        \"allowedVehicles\": [\n" +
                        "            {\n" +
                        "                \n" +
                        "                \"vehicle\": {\n" +
                        "                    \n" +
                        "                    \"name\": \"bike\"\n" +
                        "                },\n" +
                        "                \"occupiedSlots\": 0,\n" +
                        "                \"MAX_SLOTS\": 15\n" +
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
                        "        \"number\": 500,\n" +
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
    public void testDeleteLevelGivenExistingLevel() throws Exception {
        mockMvc.perform(delete("/levels")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"number\": 1}")
        ).andExpect(jsonPath("$.message").value("Level deleted"));

    }
    @Test
    public void testDeleteLevelGivenNewLevel() throws Exception {
        mockMvc.perform(delete("/levels")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"number\": 10}")
        ).andExpect(jsonPath("$.message").value("input Level does not exist"));

    }

    @Test
    public void testDeleteLevelGivenLevelContainsVehicle() throws Exception {
        //park a vehicle first.
        mockMvc.perform(post("/park")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"vehicleName\": \"car\" , \"vehicleNumber\":\"KL 11 BC 5978\" }")
        );//this vehicle is parked in level 2
        mockMvc.perform(delete("/levels")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"number\": 2}")//level 2 contain a vehicle which is parked in this method
        ).andExpect(jsonPath("$.message").value("level contains vehicle"));

    }



}
