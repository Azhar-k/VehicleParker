package com.azhar.VehicleParker;


import com.azhar.VehicleParker.Dao.LevelAllowedVehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class VehicleParkerApplication implements CommandLineRunner {

    static ApplicationContext applicationContext;
//    @Autowired
//    LevelAllowedVehicle levelAllowedVehicle;

    public static void main(String[] args) {

        applicationContext = SpringApplication.run(VehicleParkerApplication.class, args);
        InitialLoading initialLoading = applicationContext.getBean(InitialLoading.class);
        //initialLoading.loadData();//call when initial loading is required
    }

    @Override
    public void run(String... args) throws Exception {
        //levelAllowedVehicle.getLevelAllowedVehicle();
    }
}
