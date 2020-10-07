package com.azhar.VehicleParker;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class VehicleParkerApplication implements CommandLineRunner {

    static ApplicationContext applicationContext;

    public static void main(String[] args) {

        applicationContext = SpringApplication.run(VehicleParkerApplication.class, args);
        InitialLoading initialLoading = applicationContext.getBean(InitialLoading.class);
        //initialLoading.loadData();//call when initial loading is required
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
