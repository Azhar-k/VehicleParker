package com.azhar.VehicleParker.IntegrationTests.Services.Services;

import com.azhar.VehicleParker.Dao.AllowedVehicleDao;
import com.azhar.VehicleParker.Dao.LevelDao;
import com.azhar.VehicleParker.Dao.VehicleDao;
import com.azhar.VehicleParker.UnitTests.Services.implimentation.LevelService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

//Not implimented.
@SpringBootTest
public class LevelServiceTest {
    @InjectMocks
    LevelService levelService;

    @Mock
    LevelDao levelDao;
    @Mock
    AllowedVehicleDao allowedVehicleDao;
    @Mock
    VehicleDao vehicleDao;


}
