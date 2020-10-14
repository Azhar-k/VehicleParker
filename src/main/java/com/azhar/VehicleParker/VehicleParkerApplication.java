package com.azhar.VehicleParker;


import com.azhar.VehicleParker.Dao.AllowedVehicleDao;
import com.azhar.VehicleParker.Dao.LevelAllowedVehicle;
import com.azhar.VehicleParker.Services.LevelService;
import com.azhar.VehicleParker.db.models.Building.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class VehicleParkerApplication implements CommandLineRunner {

    public static void main(String[] args) {

        ApplicationContext applicationContext = SpringApplication.run(VehicleParkerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
