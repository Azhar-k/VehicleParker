package com.azhar.VehicleParker;


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
